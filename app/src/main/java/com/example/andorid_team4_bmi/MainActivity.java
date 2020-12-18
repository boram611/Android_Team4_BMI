package com.example.andorid_team4_bmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    ImageView questionButton;
    Button progressButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionButton = findViewById(R.id.questionButton);
        progressButton = findViewById(R.id.progressButton);
        questionButton.setOnClickListener(onClickListener);
        progressButton.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()){
                case R.id.questionButton:
                    final LinearLayout linearLayout = (LinearLayout) View.inflate(MainActivity.this,R.layout.whatisbmi,null);
                        //레이아웃 가져오기
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("BMI 란?")
                            .setIcon(R.mipmap.ic_launcher)
                            .setCancelable(false)
                            .setView(linearLayout)
                            .setPositiveButton("확인",null)
                            .show();
                    break;

                case R.id.progressButton:
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                    break;

            }

        }
    };
}