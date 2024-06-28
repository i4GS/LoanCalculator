package com.example.loancalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private ImageView backIcon;
    private TextView loanAmountResultTextView;
    private TextView loanTermResultTextView;
    private TextView interestRateResultTextView;
    private TextView loanTypeResultTextView;
    private TextView monthlyResultTextView;
    private TextView totalResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        backIcon = findViewById(R.id.backIcon);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Initialize TextViews
        loanAmountResultTextView = findViewById(R.id.loanAmountResultTextView);
        loanTermResultTextView = findViewById(R.id.loanTermResultTextView);
        interestRateResultTextView = findViewById(R.id.interestRateResultTextView);
        loanTypeResultTextView = findViewById(R.id.loanTypeResultTextView);
        totalResultTextView = findViewById(R.id.totalResultTextView);
        monthlyResultTextView = findViewById(R.id.monthlyResultTextView);

        // get data from intent
        double loanAmount = getIntent().getDoubleExtra("loanAmount", 0);
        int loanTerm = getIntent().getIntExtra("loanTerm", 0);
        double interestRate = getIntent().getDoubleExtra("interestRate", 0);
        String loanType = getIntent().getStringExtra("loanType");
        double monthlyPayable = getIntent().getDoubleExtra("monthlyPayable", 0);
        double totalPayable = getIntent().getDoubleExtra("totalPayable", 0);

        // replace new data with original text view
        loanAmountResultTextView.setText("Loan Amount : RM " + String.format("%.2f",loanAmount));
        loanTermResultTextView.setText("Loan Term : " + loanTerm + " years");
        interestRateResultTextView.setText("Interest Rate : " + interestRate + " %");
        loanTypeResultTextView.setText(loanType);
        totalResultTextView.setText("RM " + String.format("%.2f", totalPayable));
        monthlyResultTextView.setText("RM " + String.format("%.2f", monthlyPayable));
    }
}
