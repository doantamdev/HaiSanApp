package huflit.edu.haisanapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import huflit.edu.haisanapp.DBHelper;
import huflit.edu.haisanapp.MainActivity;
import huflit.edu.haisanapp.R;
import huflit.edu.haisanapp.USER.Cus;
import huflit.edu.haisanapp.USER.Customer;

public class RegisterActivity extends AppCompatActivity {
        EditText edNameRe,edUsernameRe,edPasswordRe,edAddressRe,edPhoneRe,edEmailRe;
        TextView chuyenAdm;

        ImageView imBack;
        Button btDangky;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DBHelper dbHelper = new DBHelper(this);
        dbHelper.open();

        edNameRe =findViewById(R.id.edNameRegister);
        edUsernameRe= findViewById(R.id.edUserNameRegister);
        edPasswordRe = findViewById(R.id.edPasswordRegister);
        edAddressRe = findViewById(R.id.edAddressRe);
        edPhoneRe = findViewById(R.id.edPhoneRe);
        edEmailRe = findViewById(R.id.edMailRe);
        chuyenAdm = findViewById(R.id.chuyenAdm);
        btDangky = findViewById(R.id.btRegisterRe);
        imBack = findViewById(R.id.imbBack);
        Cus cus = new Cus(this);

        btDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =edNameRe.getText().toString().trim();
                String userName = edUsernameRe.getText().toString().trim();
                String password = edPasswordRe.getText().toString().trim();
                String address = edAddressRe.getText().toString().trim();
                String email = edEmailRe.getText().toString().trim();
                String phone = edPhoneRe.getText().toString().trim();
                Customer customer = new Customer(name,userName,password,address,email,phone);
                boolean isValid = checkUsername(userName) && checkPassword(password);
                if(isValid) {
                    cus.ThemCus(customer);
                    Toast.makeText(RegisterActivity.this, "Dang ki thanh cong", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        chuyenAdm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, ADMLoginActivity.class);
                startActivity(i);
            }
        });
        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    private boolean checkUsername(String userName){
        if(userName.isEmpty()){
            edUsernameRe.setError("Vui long nhap ten dang nhap");
            return false;
        }
        if(userName.length() <= 5){
            edUsernameRe.setError("Ten dang nhap phai co it nhat 5 ki tu");
            return false;
        }
        return true;
    }
    private boolean checkPassword(String password){
        if(password.isEmpty())
        {
            edPasswordRe.setError("Vui long nhap mat khau");
            return false;
        }
        if(password.length() < 8){
            edPasswordRe.setError("Mat khau phai co it nhat 8 ki tu");
            return false;
        }
        return true;
    }
}