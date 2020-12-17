package com.example.andorid_team4_bmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity{

    EditText editText;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText = findViewById(R.id.userName_login);

        editText.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                //한글 영어로 문자 제한
                Pattern ps = Pattern.compile("^[a-zA-Z-가-힣ㄱ-ㅎㅏ-ㅣ\\u318D\\u119E\\u11A2\\u2022\\u2025a\\u00B7\\uFE55]+$");
                //source.equals("")백스페이스 허용 처리
                if (source.equals("") || ps.matcher(source).matches()) {
                    return source;
                }
                new AlertDialog.Builder(LoginActivity.this)
                        .setTitle("알림")
                        .setMessage("한글, 영문만 입력 가능합니다.")
                        .show();
                return "";
            }
            //글자수 제한
        },new InputFilter.LengthFilter(5)});


        findViewById(R.id.gobtn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                editText = findViewById(R.id.userName_login);
                userName = editText.getText().toString();
                new AlertDialog.Builder(LoginActivity.this)

                        .setTitle("이름 확인")
                        .setMessage("당신의 이름은 " + userName + "이 맞습니다까?")
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