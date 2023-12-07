package huflit.edu.haisanapp.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import huflit.edu.haisanapp.DBHelper;
import huflit.edu.haisanapp.product.Cart;
import huflit.edu.haisanapp.product.Checkout;

public class CheckOutDataQuey {
    public static long insert(Context context, Checkout checkout) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.TB_CHECKOUT_CUSNAME,checkout.getNameCusCO());
        values.put(DBHelper.TB_CHECKOUT_SDT,checkout.getSdtCusCO());
        values.put(DBHelper.TB_CHECKOUT_ADDRESS,checkout.getDiachiCO());
        long rs = sqLiteDatabase.insert(DBHelper.TB_CHECKOUT, null, values);
        return (rs);
    }
}
