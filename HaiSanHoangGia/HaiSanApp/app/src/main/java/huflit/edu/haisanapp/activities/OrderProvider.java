package huflit.edu.haisanapp.activities;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import huflit.edu.haisanapp.DBHelper;

public class OrderProvider extends ContentProvider {

    public static final int ORDER = 100;
    public static UriMatcher sUriMatcher =new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sUriMatcher.addURI("huflit.edu.haisanapp","ORDERCART",ORDER);
    }
    DBHelper dbHelper;
    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return false;
    }


    public Cursor query(Uri uri,String[] projection,String selection,String[] selectionArgs,String sortOrder) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match){
            case ORDER:
                cursor = database.query(DBHelper.TB_ORDER,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("can't query");
        }
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }


    public String getType(Uri uri) {
        return null;
    }


    public Uri insert(Uri uri,ContentValues values) {
        int match = sUriMatcher.match(uri);
        switch (match){
            case ORDER:
                return insertCart(uri,values);
            default:
                throw new IllegalArgumentException("can't query");
        }

    }
    private Uri insertCart(Uri uri,ContentValues values)
    {
        String name = values.getAsString(DBHelper.TB_ORDER_NAME);
        if (name==null){
            throw new IllegalArgumentException("can't query");
        }
        String soluong = values.getAsString(DBHelper.TB_ORDER_SOLUONG);
        if (name==null){
            throw new IllegalArgumentException("can't query");
        }
        String gia = values.getAsString(DBHelper.TB_ORDER_PRICE);
        if (name==null){
            throw new IllegalArgumentException("can't query");
        }
        SQLiteDatabase database =dbHelper.getWritableDatabase();
        long id = database.insert(DBHelper.TB_ORDER,null,values);
        if(id == -1){
            return null;
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int delete(Uri uri,String selection,String[] selectionArgs) {
        int rowsDeleted;
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        switch (match){
            case ORDER:
                rowsDeleted = database.delete(DBHelper.TB_ORDER,selection,null);
                break;
            default:
                throw new IllegalArgumentException("can't delete");
        }
        if(rowsDeleted!=0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri,ContentValues values,String selection,String[] selectionArgs) {
        return 0;
    }
}
