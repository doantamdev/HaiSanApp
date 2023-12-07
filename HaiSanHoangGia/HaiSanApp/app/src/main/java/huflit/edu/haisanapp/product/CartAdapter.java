package huflit.edu.haisanapp.product;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import huflit.edu.haisanapp.DBHelper;
import huflit.edu.haisanapp.R;

public class CartAdapter extends CursorAdapter {

    public CartAdapter(Context context,Cursor cursor){
        super(context,cursor,0);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.cartlist,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameCart,priceCart,soluongCart;

        nameCart =view.findViewById(R.id.orderName);
        soluongCart=view.findViewById(R.id.orderSoluong);
        priceCart=view.findViewById(R.id.orderPrice);

        int name = cursor.getColumnIndex(DBHelper.TB_ORDER_NAME);
        int price = cursor.getColumnIndex(DBHelper.TB_ORDER_PRICE);
        int soluong = cursor.getColumnIndex(DBHelper.TB_ORDER_SOLUONG);

        String nameofCart = cursor.getString(name);
        String priceofCart = cursor.getString(price);
        String soluongofCart = cursor.getString(soluong);

        nameCart.setText(nameofCart);
        priceCart.setText(priceofCart);
        soluongCart.setText(soluongofCart);
    }
}
