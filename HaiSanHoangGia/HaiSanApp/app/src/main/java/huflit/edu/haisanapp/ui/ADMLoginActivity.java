package huflit.edu.haisanapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import huflit.edu.haisanapp.DBHelper;
import huflit.edu.haisanapp.MainActivity;
import huflit.edu.haisanapp.R;
import huflit.edu.haisanapp.USER.Ad;
import huflit.edu.haisanapp.product.ProductActivity;
import huflit.edu.haisanapp.product.ThemSanPhamActivity;

public class ADMLoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btDangNhap,btDangKy;
    EditText edUsername,edPassword;
    TextView backFromLogin;
    Ad ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admlogin);
        btDangNhap = findViewById(R.id.btDangnhap);
        btDangKy = findViewById(R.id.btRegister);
        edUsername = findViewById(R.id.edUserNameLG);
        edPassword = findViewById(R.id.edPasswordLG);
        backFromLogin = findViewById(R.id.BackfromLogin);
        btDangNhap.setOnClickListener(this);
        btDangKy.setOnClickListener(this);
        backFromLogin.setOnClickListener(this);
        ad = new Ad(this);
    }
    private void btDangnhap() {
        String sTenDangNhap = edUsername.getText().toString();
        String sMatkhau = edPassword.getText().toString();
        boolean kiemtra = ad.KiemTraDangNhap(sTenDangNhap,sMatkhau);
        if(kiemtra)
        {
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ADMLoginActivity.this, ProductActivity.class);
            startActivity(i);
        }
        else {
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    private void btDangky() {
        Intent i = new Intent(ADMLoginActivity.this,RegisterADMActivity.class);
        startActivity(i);
    }
    private void veMain() {
        Intent i = new Intent(ADMLoginActivity.this,MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btDangnhap:
                btDangnhap();
                break;
            case  R.id.btRegister:
                btDangky();
                break;
            case R.id.BackfromLogin:
                veMain();
                break;
        }
    }
}