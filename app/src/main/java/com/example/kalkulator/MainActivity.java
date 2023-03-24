package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCalc1 = findViewById(R.id.button);
        Button buttonCalc2 = findViewById(R.id.button2);
        Button buttonInfo = findViewById(R.id.button3);
        Button buttonExit = findViewById(R.id.button4);

        buttonCalc1.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, Calculator.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });


        buttonCalc2.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, CalculatorScientific.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        buttonInfo.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, Info.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        buttonExit.setOnClickListener(view ->
        {
            finishAffinity();
        });
    }
}