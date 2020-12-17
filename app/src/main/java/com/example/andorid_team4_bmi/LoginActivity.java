package com.example.andorid_team4_bmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity{

    EditText editText;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        findViewById(R.id.gobtn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = findViewById(R.id.userName_login);
                userName = editText.getText().toString();
                new AlertDialog.Builder(LoginActivity.this)

                        .setTitle("이름 확인")
                        .setMessage("당신의 이름은 " + userName + "이 맞습니다.")
//                    .setIcon(R.mipmap.ic_BMI)
                        .setCancelable(false)
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(LoginActivity.this, InsertActivity.class);
                                intent.putExtra("userName", userName);
                                startActivity(intent);

                            }
                        })

                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TextView text = findViewById(R.id.userName_login);
                                text.setSelectAllOnFocus(true);
                                text.setText("이름을 다시 입력해주세요!");


                            }
                        })

                        .show();


            }
        });
    }
}