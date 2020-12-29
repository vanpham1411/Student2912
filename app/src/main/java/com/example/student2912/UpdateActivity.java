package com.example.student2912;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class UpdateActivity extends AppCompatActivity {
    LinearLayout update_layout;

    EditText update_hoten;
    EditText update_mssv;
    EditText update_ngaysinh;
    EditText update_diachi;
    EditText update_email;
    Intent myCallerIntent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

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

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle res = new Bundle();

                res.putString("mssv", update_mssv.getText().toString());
                res.putString("hoten",update_hoten.getText().toString());
                res.putString("ngaysinh", update_ngaysinh.getText().toString());
                res.putString("email",update_email.getText().toString());
                res.putString("diachi", update_diachi.getText().toString());
                myCallerIntent.putExtras(res);
                setResult(Activity.RESULT_OK,myCallerIntent);
                finish();
            }
        });



    }
}