package com.example.andorid_team4_bmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

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
        }, new InputFilter.LengthFilter(5)});


        //엔터키 안되게 막기
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == event.KEYCODE_ENTER)
                    return true;

                return false;
            }
        });


        findViewById(R.id.gobtn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText = findViewById(R.id.userName_login);
                userName = editText.getText().toString();
                if (userName.length() != 0) {
                    new AlertDialog.Builder(LoginActivity.this)

                            .setTitle("이름 확인")
                            .setMessage("당신의 이름은 " + userName + "이 맞습니까?")
//                      .setIcon(R.mipmap.ic_BMI)
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
                                    text.setText("");
                                    text.setHint("이름을 다시 입력해주세요!");


                                }
                            })

                            .show();
                } else {
                    new AlertDialog.Builder(LoginActivity.this)
                            .setMessage("이름을 입력해 주세요!")
                            .setCancelable(false)
                            .setPositiveButton("확인", null)
                            .show();

                }

            }
        });
    }

    //배경 터치 시 키보드 사라지게
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        InputMethodManager imm;
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
}//-------------------------------