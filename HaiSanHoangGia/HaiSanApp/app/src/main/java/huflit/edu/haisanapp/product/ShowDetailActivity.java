package huflit.edu.haisanapp.product;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import huflit.edu.haisanapp.DBHelper;
import huflit.edu.haisanapp.MainActivity;
import huflit.edu.haisanapp.R;
import huflit.edu.haisanapp.activities.CartActivity;
import huflit.edu.haisanapp.activities.CartDataQuery;
import huflit.edu.haisanapp.activities.SupportActivity;

public class ShowDetailActivity extends AppCompatActivity {
    TextView ShowNamePro,ShowPricePro,showMotaPro,numberOrderTxt,addToCart,totalPriceShow;
    ImageView ShowImagePro,plusBtn,minusBtn;

    int numberOrder = 1;
    //ShowDetailActivity showDetailActivity;
    //Context context;
    ArrayList<Cart> manggiohang;
    ArrayList<Product> sanphammoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        ShowNamePro = findViewById(R.id.showNamePro);
        ShowImagePro = findViewById(R.id.ShowAnhPro);
        ShowPricePro = findViewById(R.id.ShowPricePro);
        showMotaPro = findViewById(R.id.ShowMotaPro);
        totalPriceShow = findViewById(R.id.totalPriceShow);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn =findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        addToCart = findViewById(R.id.addToCartDetail);
        numberOrderTxt.setText(String.valueOf(numberOrder));
        if(manggiohang == null)
        {
            manggiohang= new ArrayList<>();
        }
        String namepro = getIntent().getStringExtra("proName");
        ShowNamePro.setText(namepro);
        String motaPro = getIntent().getStringExtra("proMota");
        showMotaPro.setText(motaPro);
        String giaPro = getIntent().getStringExtra("proPrice");
        ShowPricePro.setText(giaPro);
        String anhPro = getIntent().getStringExtra("imagePro");
        ShowImagePro.setImageBitmap(Utils.convertProToBitmapFromAssets(this, anhPro));

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowDetailActivity.this, CartActivity.class);
                startActivity(i);
                String name = ShowNamePro.getText().toString();
                String price = totalPriceShow.getText().toString();
                String soluong =numberOrderTxt.getText().toString();
                Cart cart = new Cart(0,name,price,soluong);

                long id = CartDataQuery.insert(ShowDetailActivity.this, cart);
                if (id > 0) {
                    Toast.makeText(ShowDetailActivity.this, "Thêm vào giỏ hàng thành công.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(ShowDetailActivity.this, "Không thể bỏ vào giỏ hàng", Toast.LENGTH_LONG).show();
                }
            }
        });
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basePrice = Integer.parseInt(giaPro);
                numberOrder=numberOrder+1;
                numberOrderTxt.setText(String.valueOf(numberOrder));
                int soluongOrder = numberOrder;
                int totalPrice = basePrice*soluongOrder;
                String setNewPrice = String.valueOf(totalPrice);
                totalPriceShow.setText(setNewPrice);
            }
        });
        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int basePrice = Integer.parseInt(giaPro);
                if(numberOrder >1){
                    numberOrder=numberOrder-1;
                }
                numberOrderTxt.setText(String.valueOf(numberOrder));
                int soluongOrder = numberOrder;
                int totalPrice = basePrice*soluongOrder;
                String setNewPrice = String.valueOf(totalPrice);
                totalPriceShow.setText(setNewPrice);
            }
        });
    }
}