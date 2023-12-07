package huflit.edu.haisanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Start1Activity extends AppCompatActivity {
        TextView tvBatdau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start1);
        tvBatdau = findViewById(R.id.tvBatdau);
        tvBatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Start1Activity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}