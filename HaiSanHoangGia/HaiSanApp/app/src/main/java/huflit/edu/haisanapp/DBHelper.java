package huflit.edu.haisanapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    //CUSTOMER
    public static final String TB_CUSTOMER ="CUSTOMER";
    public static final String TB_CUSTOMER_ID ="ID";
    public static final String TB_CUSTOMER_NAME="NAME";
    public static final String TB_CUSTOMER_USERNAME ="USERNAME";
    public static final String TB_CUSTOMER_PASSWORD ="PASSWORD";
    public static final String TB_CUSTOMER_ADDRESS ="ADDRESS";
    public static final String TB_CUSTOMER_PHONE ="PHONE";
    public static final String TB_CUSTOMER_EMAIL ="EMAIL";
    //ADMIN
    public static final String TB_ADMIN ="ADMIN";
    public static final String TB_ADMIN_ID ="ID";
    public static final String TB_ADMIN_NAME="NAME";
    public static final String TB_ADMIN_ROLE="ROLE";
    public static final String TB_ADMIN_USERNAME ="USERNAME";
    public static final String TB_ADMIN_PASSWORD ="PASSWORD";
    public static final String TB_ADMIN_ADDRESS ="ADDRESS";
    public static final String TB_ADMIN_PHONE ="PHONE";
    public static final String TB_ADMIN_EMAIL ="EMAIL";
    //Role
    public static final String TB_ROLE ="ROLE";
    public static final String TB_ROLE_ID ="ID";
    public static final String TB_ROLE_NAME ="NAME";
    //CATEGORY
    public static final String TB_CATEGORY ="CATEGORY";
    public static final String TB_CATEGORY_ID ="ID";
    public static final String TB_CATEGORY_NAME ="NAME";
    public static final String TB_CATEGORY_IMAGES ="IMAGES";
    //PRODUCT
    public static final String TB_PRODUCT ="PRODUCT";
    public static final String TB_PRODUCT_ID ="ID";
    public static final String TB_PRODUCT_NAME ="NAME";
    public static final String TB_PRODUCT_IMAGES ="IMAGES";
    public static final String TB_PRODUCT_SOLUONG ="SOLUONG";

    public static final String TB_PRODUCT_MOTA ="MOTA";
    public static final String TB_PRODUCT_PRICE ="PRICE";
    public static final String TB_PRODUCT_CATEID ="CATEID";

    //ORDERCART
    public static final String TB_ORDER ="ORDERCART";
    public static final String TB_ORDER_ID ="ID";
    public static final String TB_ORDER_NAME ="NAME";
    public static final String TB_ORDER_SOLUONG ="SOLUONG";

    public static final String TB_ORDER_PRICE ="PRICE";

    public static final String TB_CHECKOUT ="CHECKOUT";
    public static final String TB_CHECKOUT_ID ="ID";
    public static final String TB_CHECKOUT_CUSNAME ="NAME";
    public static final String TB_CHECKOUT_ADDRESS ="DIACHI";
    public static final String TB_CHECKOUT_SDT ="SDT";


    public DBHelper(Context context) {
        super(context, "HaiSanApp",null,16);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CUS_TABLE = "CREATE TABLE " + TB_CUSTOMER + "("
                + TB_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_CUSTOMER_NAME + " TEXT, "
                + TB_CUSTOMER_USERNAME + " TEXT, "
                + TB_CUSTOMER_PASSWORD + " TEXT, "
                + TB_CUSTOMER_ADDRESS + " TEXT, "
                + TB_CUSTOMER_PHONE + " TEXT, "
                + TB_CUSTOMER_EMAIL + " TEXT "
                +")";
        String CREATE_AD_TABLE = "CREATE TABLE " + TB_ADMIN + "("
                + TB_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ADMIN_NAME + " TEXT, "
                + TB_ADMIN_USERNAME + " TEXT, "
                + TB_ADMIN_PASSWORD + " TEXT, "
                + TB_ADMIN_ROLE + " TEXT, "
                + TB_ADMIN_ADDRESS + " TEXT, "
                + TB_ADMIN_PHONE + " TEXT, "
                + TB_ADMIN_EMAIL + " TEXT "
                +")";
        String CREATE_CATE_TABLE = "CREATE TABLE " + TB_CATEGORY + "("
                + TB_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_CATEGORY_NAME + " TEXT, "
                + TB_CATEGORY_IMAGES + " TEXT "
                +")";
        String CREATE_ROLE_TABLE = "CREATE TABLE " + TB_ROLE + "("
                + TB_ROLE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ROLE_NAME + " TEXT "
                +")";
        String CREATE_PRO_TABLE = "CREATE TABLE " + TB_PRODUCT + "("
                + TB_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_PRODUCT_NAME + " TEXT, "
                + TB_PRODUCT_IMAGES + " TEXT, "
                + TB_PRODUCT_SOLUONG + " TEXT, "
                + TB_PRODUCT_MOTA + " TEXT, "
                + TB_PRODUCT_PRICE + " TEXT, "
                + TB_PRODUCT_CATEID + " TEXT "
                +")";
        String CREATE_ORDER_TABLE = "CREATE TABLE " + TB_ORDER + "("
                + TB_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_ORDER_NAME + " TEXT, "
                + TB_ORDER_SOLUONG + " TEXT, "
                + TB_ORDER_PRICE + " TEXT "
                +")";
        String CREATE_CHECKOUT_TABLE = "CREATE TABLE " + TB_CHECKOUT + "("
                + TB_CHECKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TB_CHECKOUT_CUSNAME + " TEXT, "
                + TB_CHECKOUT_SDT + " TEXT, "
                + TB_CHECKOUT_ADDRESS + " TEXT "
                +")";
        sqLiteDatabase.execSQL(CREATE_CUS_TABLE);
        sqLiteDatabase.execSQL(CREATE_AD_TABLE);
        sqLiteDatabase.execSQL(CREATE_CATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_ROLE_TABLE);
        sqLiteDatabase.execSQL(CREATE_PRO_TABLE);
        sqLiteDatabase.execSQL(CREATE_ORDER_TABLE);
        sqLiteDatabase.execSQL(CREATE_CHECKOUT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_CUSTOMER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_ADMIN);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_CATEGORY);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_ROLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_PRODUCT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_ORDER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_CHECKOUT);
        onCreate(sqLiteDatabase);
    }
    public SQLiteDatabase open()
    {
        return this.getWritableDatabase();
    }
}
