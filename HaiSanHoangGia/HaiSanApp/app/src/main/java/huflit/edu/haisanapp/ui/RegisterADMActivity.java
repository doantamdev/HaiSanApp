package huflit.edu.haisanapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import huflit.edu.haisanapp.DBHelper;
import huflit.edu.haisanapp.MainActivity;
import huflit.edu.haisanapp.R;
import huflit.edu.haisanapp.USER.Ad;
import huflit.edu.haisanapp.USER.Admin;
import huflit.edu.haisanapp.USER.Customer;
import huflit.edu.haisanapp.USER.Role;
import huflit.edu.haisanapp.USER.RoleActivity;
import huflit.edu.haisanapp.USER.RoleDataQuery;

public class RegisterADMActivity extends AppCompatActivity   {
    EditText edNameReAd,edUsernameReAd,edPasswordReAd,edAddressReAd,edPhoneReAd,edEmailReAd;
    TextView chuyenAd;
    ImageView imBack;
    Button btDangKyAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_admactivity);
        DBHelper dbHelper = new DBHelper(this);
        dbHelper.open();
        //anh xa
        edNameReAd =findViewById(R.id.edNameRegisterPQ);
        edUsernameReAd= findViewById(R.id.edUserNameRegisterPQ);
        edPasswordReAd = findViewById(R.id.edPasswordRegisterPQ);
        edAddressReAd = findViewById(R.id.edAddressRePQ);
        edPhoneReAd = findViewById(R.id.edPhonePQ);
        edEmailReAd = findViewById(R.id.edMailPQ);
        chuyenAd = findViewById(R.id.chuyenAdm);
        btDangKyAd = findViewById(R.id.btRegisterPQ);
        imBack = findViewById(R.id.imbBack);
        Ad ad = new Ad(this);
        chuyenAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterADMActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterADMActivity.this, ADMLoginActivity.class);
                startActivity(i);
            }
        });
        //role
        Spinner snRole = findViewById(R.id.spinner_phanquyen);
        ArrayList<Role> lstRole = RoleDataQuery.getAll(this);
        lstRole.add(0, new Role(0,"Chọn chức năng"));
        ArrayAdapter<Role> roleArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,lstRole);
        roleArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        snRole.setAdapter(roleArrayAdapter);
        //dangkyad
        btDangKyAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =edNameReAd.getText().toString().trim();
                String userName = edUsernameReAd.getText().toString().trim();
                String password = edPasswordReAd.getText().toString().trim();
                String address = edAddressReAd.getText().toString().trim();
                String email = edEmailReAd.getText().toString().trim();
                String phone = edPhoneReAd.getText().toString().trim();
                boolean isValid = checkUsername(userName) && checkPassword(password);
                if(isValid) {
                    Toast.makeText(RegisterADMActivity.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                    Admin admin = new Admin(name,userName,password,address,email,phone);
                    ad.ThemAd(admin);
                    finish();
                }
            }
        });

    }

    private boolean checkUsername(String userName){
        if(userName.isEmpty()){
            edUsernameReAd.setError("Vui long nhap ten dang nhap");
            return false;
        }
        if(userName.length() <= 5){
            edUsernameReAd.setError("Ten dang nhap phai co it nhat 5 ki tu");
            return false;
        }
        return true;
    }
    private boolean checkPassword(String password){
        if(password.isEmpty())
        {
            edPasswordReAd.setError("Vui long nhap mat khau");
            return false;
        }
        if(password.length() < 8){
            edPasswordReAd.setError("Mat khau phai co it nhat 8 ki tu");
            return false;
        }
        return true;
    }
}