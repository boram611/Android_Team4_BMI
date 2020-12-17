package com.example.andorid_team4_bmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ResultActivity extends AppCompatActivity {

    TextView textView;
    ImageView iv;
    Button home,tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView = findViewById(R.id.tvBmi);
        iv = findViewById(R.id.iv);
        home = findViewById(R.id.homeBtn);


        home.setOnClickListener(homeClick);


        Intent intent = getIntent();

       // Double Bmi = Double.parseDouble(intent.getStringExtra("BMI"));
        Double Bmi = 26.33;

        if (Bmi >= 30) {
            iv.setImageResource(R.drawable.be);

           textView.setText(Bmi.toString().substring(0,4));
        } else if (Bmi >= 25) {
            iv.setImageResource(R.drawable.gwa);
            textView.setText(Bmi.toString().substring(0,4));
        } else if (Bmi >= 18.5) {
            iv.setImageResource(R.drawable.jung);
            textView.setText(Bmi.toString().substring(0,4));
        } else {
            iv.setImageResource(R.drawable.low);
            textView.setText(Bmi.toString().substring(0,4));

        }

     findViewById(R.id.tipBtn).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String[] random = getResources().getStringArray(R.array.random);
             Random random1 =new Random(random.length);


             String msg = String.valueOf(random1);


             new AlertDialog.Builder(ResultActivity.this)
                     .setTitle("당신을 위한 Tip")
                     .setMessage(msg)
                     .show();

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


}