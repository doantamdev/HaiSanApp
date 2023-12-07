package huflit.edu.haisanapp.product;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import huflit.edu.haisanapp.DBHelper;

public class ProductDataQuery {
    public static long insert(Context context, Product product)
    {
        DBHelper dbHelper= new DBHelper(context);
        SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(DBHelper.TB_PRODUCT_CATEID,product.cateid);
        values.put(DBHelper.TB_PRODUCT_NAME,product.name);
        values.put(DBHelper.TB_PRODUCT_IMAGES,product.imagepro);
        values.put(DBHelper.TB_PRODUCT_SOLUONG,product.soluong);
        values.put(DBHelper.TB_PRODUCT_PRICE,product.gia);
        values.put(DBHelper.TB_PRODUCT_MOTA,product.mota);
        long rs= sqLiteDatabase.insert(DBHelper.TB_PRODUCT,null,values);
        return (rs);
    }
    // lay danh sach
    public static ArrayList<Product> getAll(Context context)
    {
        ArrayList<Product> lstPro= new ArrayList<>();
        DBHelper dbHelper= new DBHelper(context);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String strReadUser= "Select pro.*, cate.NAME as catename from PRODUCT pro left join CATEGORY cate on pro.cateid= cate.id" ;
        //Cursor cs= db.rawQuery("Select * from "+DBHelper.TB_PRODUCT,null);
        Cursor cs= db.rawQuery(strReadUser,null);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            int id= cs.getInt(0);
            String name= cs.getString(1);
            String images= cs.getString(2);
            String soluong = cs.getString(3);
            String mota = cs.getString(4);
            String gia = cs.getString(5);
            Product item = new Product(id,name,images,soluong,gia,mota);
            item.cateid= cs.getInt(6);
            item.catename= cs.getString(7);
            lstPro.add(item);
            cs.moveToNext();
        }
        cs.close();
        db.close();
        return lstPro;

    }
    // xoa item
    public static boolean delete (Context context, int id)
    {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int rs = sqLiteDatabase.delete(DBHelper.TB_PRODUCT, DBHelper.TB_PRODUCT_ID +"=?", new String[]{Integer.toString(id)});
        System.out.println(rs);
        return (rs > 0);
    }
    // cập nhật
    public static int update(Context context, Product product)
    {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBHelper.TB_PRODUCT_NAME,product.getName());
        values.put(DBHelper.TB_PRODUCT_IMAGES,product.getImagepro());
        values.put(DBHelper.TB_PRODUCT_SOLUONG,product.getSoluong());
        values.put(DBHelper.TB_PRODUCT_PRICE,product.getGia());
        values.put(DBHelper.TB_PRODUCT_MOTA,product.getMota());
        values.put(DBHelper.TB_PRODUCT_CATEID,product.getCateid());
        int rs = sqLiteDatabase.update(DBHelper.TB_PRODUCT, values, DBHelper.TB_PRODUCT_ID + "=?", new String[]{String.valueOf(product.id)});
        return (rs);
    }
}
