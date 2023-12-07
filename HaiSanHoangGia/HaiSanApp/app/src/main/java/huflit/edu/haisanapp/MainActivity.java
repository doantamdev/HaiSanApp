package huflit.edu.haisanapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileOutputStream;
import java.util.ArrayList;

import huflit.edu.haisanapp.USER.Admin;
import huflit.edu.haisanapp.USER.Cus;
import huflit.edu.haisanapp.USER.Customer;
import huflit.edu.haisanapp.USER.RoleDataQuery;
import huflit.edu.haisanapp.activities.CartActivity;
import huflit.edu.haisanapp.activities.SettingActivity;
import huflit.edu.haisanapp.activities.SupportActivity;
import huflit.edu.haisanapp.product.Cate;
import huflit.edu.haisanapp.product.CateAdapter;
import huflit.edu.haisanapp.product.CateDataQuery;
import huflit.edu.haisanapp.product.CategoryAdapter;
import huflit.edu.haisanapp.product.Categorys;
import huflit.edu.haisanapp.product.Product;
import huflit.edu.haisanapp.product.ProductActivity;
import huflit.edu.haisanapp.product.ProductDataQuery;
import huflit.edu.haisanapp.product.SearchActivity;
import huflit.edu.haisanapp.product.ShowDetailActivity;
import huflit.edu.haisanapp.product.ShowProductAdapter;
import huflit.edu.haisanapp.ui.ADMLoginActivity;
import huflit.edu.haisanapp.ui.LoginActivity;

public class MainActivity extends AppCompatActivity implements ShowProductAdapter.ShowCallback {
        //menubottom
        ImageView imHome,imAccount,imSupport,imSetting;
        FloatingActionButton cart;
        //slide
        private ImageSlider imageSlider;

        TextView searchSp;

        EditText tenCs;

        //cate
        Customer customer;
        private RecyclerView.Adapter adapter;
        private RecyclerView rvCate;

        //showpro
        RecyclerView rvShowPro;
        ArrayList<Product> lstPro;
        ShowProductAdapter showProductAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart=findViewById(R.id.cartMain);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CartActivity.class);
                startActivity(i);
            }
        });

        //timSP
        searchSp = findViewById(R.id.tvTimSp);
        searchSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

        //anh xa va list slide
        imageSlider = findViewById(R.id.imageSlide);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://i.imgur.com/FUHwgs8.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.imgur.com/L1TBxGw.png", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.imgur.com/2tiLQqb.png", ScaleTypes.FIT));
        imageSlider.setImageList(slideModels,ScaleTypes.FIT);
        //cate
        rvCategory();

        //showpro
        rvShowPro = findViewById(R.id.rvShowPro);
        //lay du lieu
        lstPro = ProductDataQuery.getAll(this);
        showProductAdapter = new ShowProductAdapter(lstPro);
        showProductAdapter.setShowCallback(this);
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvShowPro.setAdapter(showProductAdapter);
        rvShowPro.setLayoutManager(linearLayoutManager);
        //menuBottom
        anhXaMenuBottom();
    }

    private void rvCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rvCate = findViewById(R.id.rvCate);
        rvCate.setLayoutManager(linearLayoutManager);
        ArrayList<Categorys> categorys = new ArrayList<>();
        categorys.add(new Categorys("Tom","tom"));
        categorys.add(new Categorys("Cua","cua"));
        categorys.add(new Categorys("Ca","ca"));
        categorys.add(new Categorys("So","so"));
        categorys.add(new Categorys("Oc","oc"));
        adapter = new CategoryAdapter(categorys);
        rvCate.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Product product, int position) {
        Intent i = new Intent(this, ShowDetailActivity.class);
        i.putExtra("proId",product.getId());
        i.putExtra("proName",product.getName());
        i.putExtra("imagePro",product.getImagepro());
        i.putExtra("proPrice",product.getGia());
        i.putExtra("proMota",product.getMota());
        startActivity(i);
    }
    void anhXaMenuBottom() {
        imHome = findViewById(R.id.imHome);
        imAccount = findViewById(R.id.imAccount);
        imSupport = findViewById(R.id.imSupport);
        imSetting = findViewById(R.id.imSetting);

        imHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        imAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        imSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SupportActivity.class);
                startActivity(i);
            }
        });
        imSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
    }

}