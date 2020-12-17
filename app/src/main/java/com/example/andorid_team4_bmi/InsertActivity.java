package com.example.andorid_team4_bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class InsertActivity extends AppCompatActivity {


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
        gender = findViewById(R.id.gender);
        user_height = findViewById(R.id.user_height);
        user_weight = findViewById(R.id.user_Weight);


        //결과 값 넘기기
        findViewById(R.id.gotoResult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;


                int weight = Integer.parseInt(user_weight.getText().toString());
                int height = Integer.parseInt(user_height.getText().toString());

                //BMI공식
                double Bmi = 10000 * weight / (height * height);


                switch (v.getId()) {
                    case R.id.gotoResult:
                        intent = new Intent(InsertActivity.this, ResultActivity.class);
                        intent.putExtra("Bmi", Double.toString(Bmi));
                        startActivity(intent);
                        break;

                }
            }


        });


        // 성별 넘기기
        gender.setOnCheckedChangeListener(checkListener);
    }

    RadioGroup.OnCheckedChangeListener checkListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            Intent intent = null;
            RadioButton male = findViewById(R.id.man);
            RadioButton female = findViewById(R.id.woman);

            String man = male.getText().toString();
            String woman = female.getText().toString();

            switch (checkedId) {

                case R.id.man:
                    intent = new Intent(InsertActivity.this, ResultActivity.class);
                    intent.putExtra("man", man);
                    startActivity(intent);
                    break;
                case R.id.woman:
                    intent = new Intent(InsertActivity.this, ResultActivity.class);
                    intent.putExtra("woman", woman);
                    startActivity(intent);
                    break;
            }
        }
    };
}

