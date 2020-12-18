package com.example.andorid_team4_bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class InsertActivity extends AppCompatActivity {

    // field
    TextView userName;
    EditText user_height; // 키
    EditText user_weight; // 몸무게
    RadioGroup gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        // username 받아오기
        Intent intent = getIntent();
        String user = intent.getStringExtra("userName");


        // 받아와서 띄워주기
        userName = findViewById(R.id.userName);
        userName.setText(user + "님 환영합니다.");


        // id들 선언
//        gender = findViewById(R.id.gender);
        user_height = findViewById(R.id.user_height);
        user_weight = findViewById(R.id.user_Weight);


        //결과 값 넘기기
        findViewById(R.id.gotoResult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;

                if (user_weight.getText().length() == 0 || user_height.getText().length() == 0) {
                    Log.v("왜안돼", "2");

                    new AlertDialog.Builder(InsertActivity.this)
                            .setTitle("경고!")
                            .setIcon(R.mipmap.ic_bmi)
                            .setMessage("키나 몸무게를 입력해주세요!")
                            .setCancelable(false)//아무데나 눌렀을때 안꺼지게 하는거 (버튼을 통해서만 닫게)
                            .setPositiveButton("닫기", null)
                            .show();
                } else {

                    //키, 몸무게 받아오기
                    int weight = Integer.parseInt(user_weight.getText().toString());
                    int height = Integer.parseInt(user_height.getText().toString());


                    //BMI공식
                    double Bmi = 10000 * weight / (height * height);


                    //Bmi 값 넘기기
                    switch (v.getId()) {
                        case R.id.gotoResult:
                            intent = new Intent(InsertActivity.this, ResultActivity.class);
                            intent.putExtra("Bmi", Double.toString(Bmi));
                            startActivity(intent);
                            break;

                    }
                }
            }

        });


    }
//        // 성별 넘기기
//        gender.setOnCheckedChangeListener(checkListener);
//
//    RadioGroup.OnCheckedChangeListener checkListener = new RadioGroup.OnCheckedChangeListener() {
//        @Override
//        public void onCheckedChanged(RadioGroup group, int checkedId) {

//            Intent intent = null;
//            RadioButton male = findViewById(R.id.man);
//            RadioButton female = findViewById(R.id.woman);
//
//            String man = male.getText().toString();
//            String woman = female.getText().toString();

//            switch (checkedId) {
//
//                case R.id.man:
//                    intent = new Intent(InsertActivity.this, ResultActivity.class);
//                    intent.putExtra("man", man);
//                    startActivity(intent);
//                    break;
//                case R.id.woman:
//                    intent = new Intent(InsertActivity.this, ResultActivity.class);
//                    intent.putExtra("woman", woman);
//                    startActivity(intent);
//                    break;
//            }
//        }
//    };


    //메뉴바 생성 & 처음으로 돌아가기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout:
                Intent intent = new Intent(InsertActivity.this, MainActivity.class);
                startActivity(intent);
                break;


        }
        return true;
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
}//--------------------------

