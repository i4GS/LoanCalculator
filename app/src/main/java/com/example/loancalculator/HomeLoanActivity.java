package com.example.loancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeLoanActivity extends AppCompatActivity {

    private EditText loanAmountEditText;
    private EditText loanTermEditText;
    private EditText interestRateEditText;
    private Button calculateButton;
    private ImageView backIcon;
    private TextView loanAmountErrorTextView;
    private TextView loanTermErrorTextView;
    private TextView interestRateErrorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_loan);

        loanAmountEditText = findViewById(R.id.loanAmountEditText);
        loanTermEditText = findViewById(R.id.loanTermEditText);
        interestRateEditText = findViewById(R.id.interestRateEditText);
        calculateButton = findViewById(R.id.homeCalculateButton);
        backIcon = findViewById(R.id.backIcon);
        loanAmountErrorTextView = findViewById(R.id.loanAmountErrorTextView);
        loanTermErrorTextView = findViewById(R.id.loanTermErrorTextView);
        interestRateErrorTextView = findViewById(R.id.interestRateErrorTextView);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            } // Navigate back to previous activity
        });

        // listen on the input text field
        loanAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateLoanAmount();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        // listen on the input text field
        loanTermEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateLoanTerm();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        // listen on the input text field
        interestRateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                validateInterestRate();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateLoanAmount() & validateLoanTerm() & validateInterestRate()) {

                    // get input from text field and change type
                    double loanAmount = Double.parseDouble(loanAmountEditText.getText().toString());
                    int loanTerm = Integer.parseInt(loanTermEditText.getText().toString());
                    double interestRate = Double.parseDouble(interestRateEditText.getText().toString());

                    // calculate monthly and total amount
                    double monthlyInterestRate = interestRate / 100 / 12;
                    int totalMonths = loanTerm * 12;
                    double monthlyPayable = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalMonths));
                    double totalPayable = monthlyPayable * totalMonths;

                    String loanType = "HOME LOAN";

                    // to show data in the result page
                    Intent intent = new Intent(HomeLoanActivity.this, ResultActivity.class);
                    intent.putExtra("loanAmount", loanAmount);
                    intent.putExtra("loanTerm", loanTerm);
                    intent.putExtra("interestRate", interestRate);
                    intent.putExtra("loanType", loanType);
                    intent.putExtra("monthlyPayable", monthlyPayable);
                    intent.putExtra("totalPayable", totalPayable);
                    startActivity(intent);
                } else {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_layout,
                            (ViewGroup) findViewById(R.id.toast_layout));

                    TextView text = layout.findViewById(R.id.toastTextView);
                    text.setText(" Error: Make sure no empty input there ");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 100);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                }
            }
        });
    }

    private boolean validateLoanAmount() {
        String loanAmountStr = loanAmountEditText.getText().toString();
        if (!loanAmountStr.isEmpty()) {
            double loanAmount = Double.parseDouble(loanAmountStr);
            if (loanAmount >= 150000) {
                // if more than 150000, hide the error text view
                loanAmountErrorTextView.setVisibility(View.GONE);
                return true;
            } else {
                // else show when wrong input
                loanAmountErrorTextView.setVisibility(View.VISIBLE);
                loanAmountErrorTextView.setText("Loan amount must be more than RM 150,000");
                return false;
            }
        } else {
            loanAmountErrorTextView.setVisibility(View.GONE);
            return false;
        }
    }

    private boolean validateLoanTerm() {
        String loanTermStr = loanTermEditText.getText().toString();
        if (!loanTermStr.isEmpty()) {
            int loanTerm = Integer.parseInt(loanTermStr);
            // if input between 20 - 35, hide the error text view
            if (loanTerm >= 20 && loanTerm <= 35) {
                loanTermErrorTextView.setVisibility(View.GONE);
                return true;
            } else {
                loanTermErrorTextView.setVisibility(View.VISIBLE);
                loanTermErrorTextView.setText("Loan term must be between 20 - 35 years");
                return false;
            }
        } else {
            loanTermErrorTextView.setVisibility(View.GONE);
            return false;
        }
    }

    private boolean validateInterestRate() {
        String interestRateStr = interestRateEditText.getText().toString();
        if (!interestRateStr.isEmpty()) {
            double interestRate = Double.parseDouble(interestRateStr);
            // if input between 3 - 5%, hide the error text view
            if (interestRate >= 3 && interestRate <= 5) {
                interestRateErrorTextView.setVisibility(View.GONE);
                return true;
            } else {
                interestRateErrorTextView.setVisibility(View.VISIBLE);
                interestRateErrorTextView.setText("Interest rate must be between 3 - 5 %");
                return false;
            }
        } else {
            interestRateErrorTextView.setVisibility(View.GONE);
            return false;
        }
    }
}