package huflit.edu.haisanapp.product;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import huflit.edu.haisanapp.MainActivity;
import huflit.edu.haisanapp.R;
import huflit.edu.haisanapp.ui.ADMLoginActivity;

public class CateActivity extends AppCompatActivity implements CateAdapter.CateCallback {
    RecyclerView rvListCode;
    ArrayList<Cate> lstCate;
    CateAdapter cateAdapter;

    Button chuyenAddSP;
    FloatingActionButton fbAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cate);
        rvListCode = findViewById(R.id.rvListDe);
        fbAdd = findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(view -> addUserDialog());
        chuyenAddSP = findViewById(R.id.btChuyenAddSP);
        chuyenAddSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CateActivity.this, ProductActivity.class);
                startActivity(i);
            }
        });
        //lay du lieu
        lstCate = CateDataQuery.getAll(this);
        cateAdapter = new CateAdapter(lstCate);
        cateAdapter.setCallback(this);
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListCode.setAdapter(cateAdapter);
        rvListCode.setLayoutManager(linearLayoutManager);
    }
    void addUserDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CateActivity.this);
        alertDialog.setTitle("Them moi");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_cate,null);
        alertDialog.setView(dialogView);
        EditText edName =(EditText) dialogView.findViewById(R.id.edNameCate);
        EditText edImage =(EditText) dialogView.findViewById(R.id.edImageCate);
//
        alertDialog.setPositiveButton("Dong y", (dialog,which) -> {
            String name = edName.getText().toString();
            String image = edImage.getText().toString();
            if(name.isEmpty())
            {
                Toast.makeText(CateActivity.this, "Nhap du lieu khong hop le", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Cate cate = new Cate(0,name,image);
                long id = CateDataQuery.insert(CateActivity.this,cate);
                if(id>0)
                {
                    Toast.makeText(CateActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
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

    void updateUserDialog(Cate cate){
        // khởi tạo dialog để cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CateActivity.this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_cate, null);
        alertDialog.setView(dialogView);
        EditText edName =(EditText) dialogView.findViewById(R.id.edNameCate);
        EditText edImage =(EditText) dialogView.findViewById(R.id.edImageCate);
        // gán dữ liệu
        edName.setText(cate.getNameCate());
        edImage.setText(cate.getImageCate());
        //
        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {

            cate.setNameCate(edName.getText().toString());
            cate.setImageCate(edImage.getText().toString());
            if (cate.nameCate.isEmpty()) {
                Toast.makeText(CateActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {

                int id = CateDataQuery.update(CateActivity.this, cate);
                if (id > 0) {
                    Toast.makeText(CateActivity.this, "Cập nhật Category thành công.", Toast.LENGTH_LONG).show();
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
        lstCate.clear();
        lstCate.addAll(CateDataQuery.getAll(CateActivity.this));
        cateAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemDeleteClicked(Cate cate, int position) {
        boolean rs = CateDataQuery.delete(CateActivity.this, cate.id);
        if(rs){
            Toast.makeText(CateActivity.this, "xoa thanh cong", Toast.LENGTH_SHORT).show();
            resetData();
        } else {
            Toast.makeText(CateActivity.this, "Xoa that bai", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemEditClicked(Cate cate, int position) {
        updateUserDialog(cate);
    }
}