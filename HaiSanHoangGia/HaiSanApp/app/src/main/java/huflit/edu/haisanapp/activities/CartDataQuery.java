package huflit.edu.haisanapp.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huflit.edu.haisanapp.DBHelper;
import huflit.edu.haisanapp.USER.Role;
import huflit.edu.haisanapp.product.Cart;

public class CartDataQuery {
    public static ArrayList<Cart> getAll(Context context) {
        ArrayList<Cart> lstCart = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cs = db.rawQuery("Select * from " + DBHelper.TB_ORDER, null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id = cs.getInt(0);
            String name = cs.getString(1);
            String soluong = cs.getString(2);
            String price = cs.getString(3);
            lstCart.add(new Cart(id, name,soluong,price));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstCart;
    }
    public static long insert(Context context, Cart cart) {

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.TB_ORDER_NAME,cart.getTensp());
        values.put(DBHelper.TB_ORDER_PRICE,cart.getGiasp());
        values.put(DBHelper.TB_ORDER_SOLUONG,cart.getSoluong());
        long rs = sqLiteDatabase.insert(DBHelper.TB_ORDER, null, values);
        return (rs);
    }
    public static boolean delete(Context context, int id) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(DBHelper.TB_ORDER, DBHelper.TB_ORDER_ID + "=?", new String[]{String.valueOf(id)});
        return (rs > 0);
    }

}
