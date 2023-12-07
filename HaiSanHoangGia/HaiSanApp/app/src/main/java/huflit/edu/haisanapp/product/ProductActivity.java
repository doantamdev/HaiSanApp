package huflit.edu.haisanapp.product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import huflit.edu.haisanapp.R;

public class ProductActivity extends AppCompatActivity implements ProductAdapter.ProCallback {
    RecyclerView rvListCode;
    ArrayList<Product> lstPro;
    ProductAdapter  productAdapter;
    Button chuyenaddCate;
    FloatingActionButton fbAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        rvListCode = findViewById(R.id.rvListPro);
        fbAdd = findViewById(R.id.fbAdd);
        chuyenaddCate = findViewById(R.id.btChuyenAddCate);
        chuyenaddCate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductActivity.this, CateActivity.class);
                startActivity(i);
            }
        });
        fbAdd.setOnClickListener(view -> addProDialog());
        // lấy dữ liệu
        lstPro = ProductDataQuery.getAll(this);
        productAdapter = new ProductAdapter(lstPro);
        productAdapter.setProCallback(this);

        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListCode.setAdapter(productAdapter);
        rvListCode.setLayoutManager(linearLayoutManager);

    }
    void addProDialog() {
        // khởi tạo dialog để thêm người dùng.
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProductActivity.this);
        alertDialog.setTitle("Thêm mới");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_pro, null);
        alertDialog.setView(dialogView);
        EditText edNamePro = (EditText) dialogView.findViewById(R.id.edNamePro);
        EditText edImage= (EditText) dialogView.findViewById(R.id.edImagePro);
        EditText edSoluong= (EditText) dialogView.findViewById(R.id.edSoluongPro);
        EditText edGia= (EditText) dialogView.findViewById(R.id.edGia);
        EditText edMota= (EditText) dialogView.findViewById(R.id.edMota);
        // load phòng ban
        Spinner snCate= dialogView.findViewById(R.id.snCate);
        ArrayList<Cate> lstCate= CateDataQuery.getAll(this);
        lstCate.add(0,new Cate(0,"Chọn Category",null));
        ArrayAdapter<Cate> cateArrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,lstCate);
        cateArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snCate.setAdapter(cateArrayAdapter);
        //
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            String name = edNamePro.getText().toString();
            String anh = edImage.getText().toString();
            String soluong = edSoluong.getText().toString();
            String gia = edGia.getText().toString();
            String mota = edMota.getText().toString();
            Cate itemca= (Cate) snCate.getSelectedItem();
            if (itemca.id==0)
            {
                Toast.makeText(ProductActivity.this, "Vui lòng chon category", Toast.LENGTH_LONG).show();

            } else
            if (name.isEmpty()) {
                Toast.makeText(ProductActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {
                Product product = new Product(0, name,anh,soluong,gia,mota);
                product.cateid= itemca.id;
                long id = ProductDataQuery.insert(ProductActivity.this, product);
                if (id > 0) {
                    Toast.makeText(ProductActivity.this, "Thêm sản phẩm thành công.", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Huỷ", (dialog, which) -> {
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }
    void updateProDialog(Product product) {
        // khởi tạo dialog để cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProductActivity.this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_pro, null);
        alertDialog.setView(dialogView);
        EditText edNamePro = (EditText) dialogView.findViewById(R.id.edNamePro);
        EditText edImage= (EditText) dialogView.findViewById(R.id.edImagePro);
        EditText edSoluong= (EditText) dialogView.findViewById(R.id.edSoluongPro);
        EditText edGia= (EditText) dialogView.findViewById(R.id.edGia);
        EditText edMota= (EditText) dialogView.findViewById(R.id.edMota);
        // load phòng ban
        Spinner snCate= dialogView.findViewById(R.id.snCate);
        ArrayList<Cate> lstCate= CateDataQuery.getAll(this);
        lstCate.add(0,new Cate(0,"Chọn Category",null));
        ArrayAdapter<Cate> cateArrayAdapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,lstCate);
        cateArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snCate.setAdapter(cateArrayAdapter);
        //

        // gán dữ liệu
        edNamePro.setText(product.getName());
        edImage.setText(product.getImagepro());
        edSoluong.setText(product.getSoluong());
        edGia.setText(product.getGia());
        edMota.setText(product.getMota());
        snCate.setSelection(product.cateid);
        //System.out.println(product.getCateid());
        //
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {
            product.setName(edNamePro.getText().toString());
            product.setImagepro(edImage.getText().toString());
            product.setSoluong(edSoluong.getText().toString());
            product.setGia(edGia.getText().toString());
            product.setMota(edMota.getText().toString());
            Cate itemca =(Cate) snCate.getSelectedItem();
            if (itemca.id==0)
            {
                Toast.makeText(ProductActivity.this, "Vui lòng chon category", Toast.LENGTH_LONG).show();

            } else
            if (product.name.isEmpty()) {
                Toast.makeText(ProductActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {
                product.cateid= itemca.id;
                int id = ProductDataQuery.update(ProductActivity.this, product);
                if (id > 0) {
                    Toast.makeText(ProductActivity.this, "Cập nhật san pham thành công.", Toast.LENGTH_LONG).show();
                    resetData();
                    dialog.dismiss();
                }
            }
        });
        alertDialog.setNegativeButton("Hủy", (dialog, which) -> {
            dialog.dismiss();
        });
        alertDialog.create();
        alertDialog.show();
    }
    void resetData() {
        lstPro.clear();
        lstPro.addAll(ProductDataQuery.getAll(ProductActivity.this));
        productAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteClicked(Product product, int position) {
        boolean rs = ProductDataQuery.delete(ProductActivity.this, product.getId());
        if (rs) {
            Toast.makeText(this, "Xoá thành công", Toast.LENGTH_LONG).show();
            resetData();
        } else {
            Toast.makeText(this, "Xoá thất bại", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onItemEditClicked(Product product, int position) {
        updateProDialog(product);
    }
}