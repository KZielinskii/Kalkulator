package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Button backToMenu = findViewById(R.id.button);
        backToMenu.setOnClickListener(view ->
        {
            Intent intent = new Intent(Info.this, MainActivity.class);
            startActivity(intent);
        });
    }
}