package huflit.edu.haisanapp.product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huflit.edu.haisanapp.DBHelper;

public class CateDataQuery {
    // thêm mới 1 user
    public static long insert(Context context, Cate cate) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.TB_CATEGORY_NAME, cate.getNameCate());
        values.put(DBHelper.TB_CATEGORY_IMAGES,cate.getImageCate());
        long rs = sqLiteDatabase.insert("CATEGORY", null, values);
        return (rs);
    }

    // lay danh sach
    public static ArrayList<Cate> getAll(Context context) {
        ArrayList<Cate> lstCate = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + DBHelper.TB_CATEGORY, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String image = cs.getString(2);
            Cate cate = new Cate(id,name,image);
            lstCate.add(cate);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstCate;

    }

    // xoa item
    public static boolean delete(Context context, int id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(DBHelper.TB_CATEGORY, DBHelper.TB_CATEGORY_ID + "=?", new String[]{String.valueOf(id)});
        return (rs > 0);
    }

    // cập nhật
    public static int update(Context context, Cate cate) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.TB_CATEGORY_NAME, cate.getNameCate());
        values.put(DBHelper.TB_CATEGORY_IMAGES,cate.getImageCate());
        int rs = sqLiteDatabase.update(DBHelper.TB_CATEGORY, values, DBHelper.TB_CATEGORY_ID + "=?", new String[]{String.valueOf(cate.id)});
        return (rs);
    }
}
