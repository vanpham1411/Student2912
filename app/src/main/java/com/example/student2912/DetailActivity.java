package com.example.student2912;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView mssv;
    TextView hoten;
    TextView ngaysinh;
    TextView diachi;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent mylocalIntent = getIntent();

        Bundle mybundle = mylocalIntent.getExtras();


        mssv = (TextView)findViewById(R.id.text_mssv);
        hoten = (TextView)findViewById(R.id.text_hoten);
        diachi = (TextView)findViewById(R.id.text_diachi);
        ngaysinh = (TextView)findViewById(R.id.text_ngaysinh);
        email = (TextView)findViewById(R.id.text_email);

        mssv.setText(mybundle.getString("mssv"));
        hoten.setText(mybundle.getString("hoten"));
       ngaysinh.setText(mybundle.getString("ngaysinh"));
       diachi.setText(mybundle.getString("email"));
        email.setText(mybundle.getString("diachi"));

        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}