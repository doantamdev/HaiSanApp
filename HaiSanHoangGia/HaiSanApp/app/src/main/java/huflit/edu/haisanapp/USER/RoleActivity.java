package huflit.edu.haisanapp.USER;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import huflit.edu.haisanapp.R;
import huflit.edu.haisanapp.product.Cate;
import huflit.edu.haisanapp.product.CateActivity;
import huflit.edu.haisanapp.product.CateDataQuery;

public class RoleActivity extends AppCompatActivity implements RoleAdapter.RoleCallback {
    RecyclerView rvListCode;
    ArrayList<Role> lstRole;
    RoleAdapter roleAdapter;
    FloatingActionButton fbAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        rvListCode = findViewById(R.id.rvListDe);
        fbAdd = findViewById(R.id.fbAdd);
        fbAdd.setOnClickListener(view -> addUserDialog());
        //lay du lieu
        lstRole = RoleDataQuery.getAll(this);
        roleAdapter = new RoleAdapter(lstRole);
        roleAdapter.setCallback(this);
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvListCode.setAdapter(roleAdapter);
        rvListCode.setLayoutManager(linearLayoutManager);
    }
    void addUserDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RoleActivity.this);
        alertDialog.setTitle("Them moi");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_role,null);
        alertDialog.setView(dialogView);
        EditText edName =(EditText) dialogView.findViewById(R.id.edNameDe);

        alertDialog.setPositiveButton("Dong y", (dialog,which) -> {
            String name = edName.getText().toString();
            if(name.isEmpty())
            {
                Toast.makeText(RoleActivity.this, "Nhap du lieu khong hop le", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Role role = new Role(0,name);
                long id = RoleDataQuery.insert(RoleActivity.this,role);
                if(id>0)
                {
                    Toast.makeText(RoleActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
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
    void updateUserDialog(Role role){
        // khởi tạo dialog để cập nhật người dùng
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(RoleActivity.this);
        alertDialog.setTitle("Cập nhật");
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_role, null);
        alertDialog.setView(dialogView);
        EditText edName =(EditText) dialogView.findViewById(R.id.edNameDe);

        edName.setText(role.getName());

        alertDialog.setPositiveButton("Đồng ý", (dialog, which) -> {

            String name = edName.getText().toString();
            if (role.name.isEmpty()) {
                Toast.makeText(RoleActivity.this, "Nhập dữ liệu không hợp lệ", Toast.LENGTH_LONG).show();
            } else {

                int id = RoleDataQuery.update(RoleActivity.this, role);
                if (id > 0) {
                    Toast.makeText(RoleActivity.this, "Cập nhật Category thành công.", Toast.LENGTH_LONG).show();
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
        lstRole.clear();
        lstRole.addAll(RoleDataQuery.getAll(RoleActivity.this));
        roleAdapter.notifyDataSetChanged();
    }
    @Override
    public void onItemDeleteClicked(Role role, int position) {
        boolean rs = RoleDataQuery.delete(RoleActivity.this,role.id);
        if(rs){
            Toast.makeText(RoleActivity.this, "xoa thanh cong", Toast.LENGTH_SHORT).show();
            resetData();
        } else {
            Toast.makeText(RoleActivity.this, "Xoa that bai", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemEditClicked(Role role, int position) {
        updateUserDialog(role);
    }
}