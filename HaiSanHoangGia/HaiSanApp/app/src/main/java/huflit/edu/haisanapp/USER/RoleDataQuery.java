package huflit.edu.haisanapp.USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huflit.edu.haisanapp.DBHelper;

public class RoleDataQuery {
    public static long insert(Context context, Role role) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.TB_ROLE_NAME,role.name);
        long rs = sqLiteDatabase.insert(DBHelper.TB_ROLE, null, values);
        return (rs);
    }

    // lay danh sach
    public static ArrayList<Role> getAll(Context context) {
        ArrayList<Role> lstRole = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + DBHelper.TB_ROLE, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            lstRole.add(new Role(id, name));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstRole;

    }

    // xoa item
    public static boolean delete(Context context, int id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(DBHelper.TB_ROLE, DBHelper.TB_ROLE_ID + "=?", new String[]{String.valueOf(id)});
        return (rs > 0);
    }

    // cập nhật
    public static int update(Context context, Role role) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.TB_ROLE_NAME, role.getName());
        int rs = sqLiteDatabase.update(DBHelper.TB_ROLE, values,DBHelper.TB_ROLE_ID + "=?", new String[]{String.valueOf(role.id)});
        return (rs);
    }
}
