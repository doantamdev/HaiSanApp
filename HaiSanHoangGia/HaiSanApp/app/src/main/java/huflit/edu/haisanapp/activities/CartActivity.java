package huflit.edu.haisanapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import huflit.edu.haisanapp.DBHelper;
import huflit.edu.haisanapp.MainActivity;
import huflit.edu.haisanapp.R;
import huflit.edu.haisanapp.product.Cart;
import huflit.edu.haisanapp.product.CartAdapter;
import huflit.edu.haisanapp.product.Cate;
import huflit.edu.haisanapp.product.CateActivity;
import huflit.edu.haisanapp.product.CateDataQuery;
import huflit.edu.haisanapp.product.Checkout;
import huflit.edu.haisanapp.product.Product;
import huflit.edu.haisanapp.product.ProductActivity;
import huflit.edu.haisanapp.product.ProductAdapter;
import huflit.edu.haisanapp.product.ProductDataQuery;

public class CartActivity extends AppCompatActivity implements OrderAdapter.OrderCallback {

    RecyclerView rvCart;
    ArrayList<Cart> lstCart;
    OrderAdapter orderAdapter;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        rvCart = findViewById(R.id.rvListOrder);
        lstCart = CartDataQuery.getAll(this);
        orderAdapter = new OrderAdapter(lstCart);
        orderAdapter.setOrderCallback(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvCart.setAdapter(orderAdapter);
        rvCart.setLayoutManager(linearLayoutManager);

        checkout = findViewById(R.id.checkout);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thanhtoan();
            }
        });
    }

    void resetData() {
        lstCart.clear();
        lstCart.addAll(CartDataQuery.getAll(CartActivity.this));
        orderAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteClicked(Cart cart, int position) {
        boolean rs = CartDataQuery.delete(CartActivity.this, cart.getIdsp());
        if (rs) {
            Toast.makeText(this, "Xoá thành công", Toast.LENGTH_LONG).show();
            resetData();
        } else {
            Toast.makeText(this, "Xoá thất bại", Toast.LENGTH_LONG).show();
        }
    }
    void Thanhtoan() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CartActivity.this);
        alertDialog.setTitle("Thanh toan");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.checkout_dialog,null);
        alertDialog.setView(dialogView);
        EditText edName =(EditText) dialogView.findViewById(R.id.edNameCus);
        EditText edSdt =(EditText) dialogView.findViewById(R.id.edSdtCus);
        EditText edDc =(EditText) dialogView.findViewById(R.id.edDCcus);
//
        alertDialog.setPositiveButton("Dong y", (dialog,which) -> {
            String name = edName.getText().toString();
            String sdt = edSdt.getText().toString();
            String dc = edDc.getText().toString();
            if(name.isEmpty())
            {
                Toast.makeText(CartActivity.this, "Nhap du lieu khong hop le", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Checkout checkout = new Checkout(0,name,sdt,dc);
                long id = CheckOutDataQuey.insert(CartActivity.this,checkout);
                if(id>0)
                {
                    Intent i = new Intent(CartActivity.this, COThanhCongActivity.class);
                    startActivity(i);
                    lstCart.clear();
                    orderAdapter.notifyDataSetChanged();
                    Toast.makeText(CartActivity.this, "Them dia chi thanh toan thanh cong", Toast.LENGTH_SHORT).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Huy", (dialog,which) ->{
            dialog.dismiss();
        } );
        alertDialog.create();
        alertDialog.show();
    }
}