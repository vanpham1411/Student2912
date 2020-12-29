package com.example.student2912;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditStudent extends AppCompatActivity {
    EditText edit_hoten;
    EditText edit_mssv;
    EditText edit_ngaysinh;
    EditText edit_diachi;
    EditText edit_email;
    Button btn_update;

    Intent myCallerIntentHander = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);
        edit_hoten = (EditText) findViewById(R.id.edit_hoten);
        edit_mssv = (EditText) findViewById(R.id.edit_mssv);
        edit_ngaysinh = (EditText) findViewById(R.id.edit_ngaysinh);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_diachi= (EditText) findViewById(R.id.edit_diachi);
        btn_update = (Button) findViewById(R.id.btn_register);

        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check(edit_hoten,"Please enter your name");
                check(edit_mssv,"Enter your StudentID");
                check(edit_ngaysinh,"Enter your Birthday");
                check(edit_email,"Enter your email");
                check(edit_diachi,"Enter your address");
                Log.v("TAG",edit_mssv.getText().toString());

                Bundle res = new Bundle();


                res.putString("mssv", edit_mssv.getText().toString());
                res.putString("hoten",edit_hoten.getText().toString());
                res.putString("ngaysinh", edit_ngaysinh.getText().toString());
                res.putString("email",edit_email.getText().toString());
                res.putString("diachi", edit_diachi.getText().toString());
                myCallerIntentHander.putExtras(res);
                setResult(Activity.RESULT_OK,myCallerIntentHander);
                finish();

            }
        });


    }
    private void check(EditText editText,String message){
        String seditText = editText.getText().toString();
        if(seditText.equals(""))
            editText.setError(message);
    }
}