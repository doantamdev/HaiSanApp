package huflit.edu.haisanapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import huflit.edu.haisanapp.MainActivity;
import huflit.edu.haisanapp.R;

public class COThanhCongActivity extends AppCompatActivity {
    Button veMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cothanh_cong);
        veMain= findViewById(R.id.coveMain);
        veMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(COThanhCongActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}