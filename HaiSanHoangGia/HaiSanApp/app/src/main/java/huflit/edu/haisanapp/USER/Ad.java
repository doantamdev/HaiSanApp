package huflit.edu.haisanapp.USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huflit.edu.haisanapp.DBHelper;

public class Ad {
    SQLiteDatabase database;

    public Ad(Context context)
    {
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.open();
    }
    public long ThemAd(Admin admin)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TB_ADMIN_NAME,admin.getNameAd());
        contentValues.put(DBHelper.TB_ADMIN_USERNAME,admin.getUsernameAd());
        contentValues.put(DBHelper.TB_ADMIN_ADDRESS,admin.getAddressAd());
        contentValues.put(DBHelper.TB_ADMIN_PASSWORD,admin.getPassAd());
        contentValues.put(DBHelper.TB_ADMIN_PHONE,admin.getPhoneAd());
        contentValues.put(DBHelper.TB_ADMIN_EMAIL,admin.getEmailAd());
        long kiemtra = database.insert(DBHelper.TB_ADMIN,null,contentValues);
        return kiemtra;
    }
    public boolean KiemTraDangNhap(String username, String pass) {
        String truyvan = "SELECT * FROM " +DBHelper.TB_ADMIN+ " WHERE " + DBHelper.TB_ADMIN_USERNAME + " = '" + username
                + "' AND " +DBHelper.TB_ADMIN_PASSWORD+ " = '" +pass +"'";
        Cursor cursor = database.rawQuery(truyvan,null);
        if(cursor.getCount() != 0)
        {
            return true;
        }else {
            return false;
        }
    }

}
