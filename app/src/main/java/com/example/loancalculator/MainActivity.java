package com.example.loancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button homeLoanButton, carLoanButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carLoanButton = findViewById(R.id.carLoanButton);
        homeLoanButton = findViewById(R.id.homeLoanButton);

        carLoanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CarLoanActivity.class);
                startActivity(intent);
            }
        });

        homeLoanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeLoanActivity.class);
                startActivity(intent);
            }
        });

    }
}
