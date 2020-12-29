package com.example.student2912;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    EditText update_hoten;
    EditText update_mssv;
    EditText update_ngaysinh;
    EditText update_diachi;
    EditText update_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent mylocalIntent = getIntent();

        Bundle mybundle = mylocalIntent.getExtras();


        update_hoten = (EditText) findViewById(R.id.edit_hoten);
        update_mssv = (EditText) findViewById(R.id.edit_mssv);
        update_ngaysinh = (EditText) findViewById(R.id.edit_ngaysinh);
        update_email = (EditText) findViewById(R.id.edit_email);
        update_diachi= (EditText) findViewById(R.id.edit_diachi);

        update_mssv.setText(mybundle.getString("mssv"));
        update_hoten.setText(mybundle.getString("hoten"));
        update_ngaysinh.setText(mybundle.getString("ngaysinh"));
        update_diachi.setText(mybundle.getString("email"));
        update_email.setText(mybundle.getString("diachi"));





    }
}