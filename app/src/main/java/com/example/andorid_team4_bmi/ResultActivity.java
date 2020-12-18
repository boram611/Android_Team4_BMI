package com.example.andorid_team4_bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    TextView textView;
    ImageView iv;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView = findViewById(R.id.tvBmi);
        iv = findViewById(R.id.iv);
        home = findViewById(R.id.homeBtn);


        home.setOnClickListener(homeClick);


        Intent intent = getIntent();

        String Bmistr = intent.getStringExtra("Bmi");
        double Bmi = Double.parseDouble(Bmistr);
        Log.v("고돌", String.valueOf(Bmi));


        if (Bmi >= 30) {
            iv.setImageResource(R.drawable.be);
            textView.setText(Double.toString(Bmi));
        } else if (Bmi >= 25) {
            iv.setImageResource(R.drawable.gwa);
            textView.setText(Double.toString(Bmi));
        } else if (Bmi >= 18.5) {
            iv.setImageResource(R.drawable.jung);
            textView.setText(Double.toString(Bmi));

            //마른사람들
        } else {
            iv.setImageResource(R.drawable.low);
            textView.setText(Double.toString(Bmi));
        }

        findViewById(R.id.tipBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] random = getResources().getStringArray(R.array.big);
                int randomNum = (int) (Math.random() * random.length);
                String msg = random[randomNum];


                String[] randomthin = getResources().getStringArray(R.array.thin);
                int randomthinint = (int) (Math.random() * randomthin.length);
                String msg1 = randomthin[randomthinint];




                if(Bmi >= 18.5){
                    new AlertDialog.Builder(ResultActivity.this)
                            .setTitle("당신을 위한 Tip")
                            .setMessage(msg)
                            .setNegativeButton("확인",null)
                            .setCancelable(false)
                            .show();
                }else{
                    new AlertDialog.Builder(ResultActivity.this)
                            .setTitle("당신을 위한 Tip")
                            .setMessage(msg1)
                            .setNegativeButton("확인",null)
                            .setCancelable(false)
                            .show();
                }


            }
        });

    }

    View.OnClickListener homeClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_logout:
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                break;


        }
        return true;
    }


}//----------------------------------