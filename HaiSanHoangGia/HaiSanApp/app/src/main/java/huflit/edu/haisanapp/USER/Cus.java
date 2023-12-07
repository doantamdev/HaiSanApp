package huflit.edu.haisanapp.USER;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import huflit.edu.haisanapp.DBHelper;

public class Cus {
    SQLiteDatabase database;

    public Cus(Context context)
    {
        DBHelper dbHelper = new DBHelper(context);
        database = dbHelper.open();
    }
    public long ThemCus(Customer customer)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.TB_CUSTOMER_NAME,customer.getNameCus());
        contentValues.put(DBHelper.TB_CUSTOMER_USERNAME,customer.getUsernameCus());
        contentValues.put(DBHelper.TB_CUSTOMER_PASSWORD,customer.getPassCus());
        contentValues.put(DBHelper.TB_CUSTOMER_ADDRESS,customer.getAddressCus());
        contentValues.put(DBHelper.TB_CUSTOMER_PHONE,customer.getPhoneCus());
        contentValues.put(DBHelper.TB_CUSTOMER_EMAIL,customer.getEmailCus());

        long kiemtra = database.insert(DBHelper.TB_CUSTOMER,null,contentValues);
        return kiemtra;
    }
    public boolean KiemTraCustomer() {
        String truyvan = "SELECT * FROM " +DBHelper.TB_CUSTOMER;
        Cursor cursor = database.rawQuery(truyvan,null);
        if(cursor.getCount() != 0)
        {
            return true;
        }else {
            return false;
        }
    }
    public boolean KiemTraDangNhap(String tendangnhap, String matkhau) {
        String truyvan = "SELECT * FROM " +DBHelper.TB_CUSTOMER+ " WHERE " + DBHelper.TB_CUSTOMER_USERNAME + " = '" + tendangnhap
                + "' AND " +DBHelper.TB_CUSTOMER_PASSWORD+ " = '" +matkhau +"'";
        Cursor cursor = database.rawQuery(truyvan,null);
        if(cursor.getCount() != 0)
        {
            return true;
        }else {
            return false;
        }
    }
}
