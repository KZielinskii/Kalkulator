package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButtonCalc1 = findViewById(R.id.button);
        mButtonCalc1.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, Calculator.class);
            startActivity(intent);
        });
    }
}