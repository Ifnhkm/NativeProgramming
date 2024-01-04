package com.example.mindsharpener;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textview4, textview5, textview6, textview7, textview8;
    private RadioGroup radioGroup;
    private RadioButton radiobtn1, radiobtn2, radiobtn3;
    private EditText editText;
    private int points = 0;

    @SuppressLint({"WrongViewCast", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ActionBar setup
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("MindSharpener");
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.black));
        }

        radioGroup = findViewById(R.id.radioGroup);
        radiobtn1 = findViewById(R.id.radiobtn1);
        radiobtn2 = findViewById(R.id.radiobtn2);
        radiobtn3 = findViewById(R.id.radiobtn3);

        textview4 = findViewById(R.id.textview4);
        textview5 = findViewById(R.id.textview5);
        textview6 = findViewById(R.id.textview6);
        editText = findViewById(R.id.editText);
        Button button = findViewById(R.id.button);
        textview7 = findViewById(R.id.textview7);
        textview8 = findViewById(R.id.textview8);

        button.setOnClickListener(view -> displayQuestion());
    }

    private void displayQuestion() {
        if (radioGroup == null || editText == null) {
            // Check for null references
            return;
        }

        if (radioGroup.getCheckedRadioButtonId() == -1) {
            // No RadioButton selected, handle this case as needed
            return;
        }

        Random random = new Random();
        int maxDigits = getMaxDigits();

        int firstNumber = random.nextInt((int) Math.pow(10, maxDigits));
        int secondNumber = random.nextInt((int) Math.pow(10, maxDigits));
        int operator = random.nextInt(4);

        displayOperandsAndOperator(firstNumber, secondNumber, operator);
    }
    private int getMaxDigits() {
        if (radiobtn1 == null || radiobtn2 == null || radiobtn3 == null) {
            // Check for null references
            return 0;
        }

        if (radiobtn1.isChecked()) {
            return 1;
        } else if (radiobtn2.isChecked()) {
            return 2;
        } else {
            return 3;
        }
    }

    private void displayOperandsAndOperator(int firstNumber, int secondNumber, int operator) {
        if (textview4 == null || textview5 == null || textview6 == null) {
            // Check for null references
            return;
        }

        textview4.setText(String.valueOf(firstNumber));
        textview5.setText(getOperatorSymbol(operator));
        textview6.setText(String.valueOf(secondNumber));
        displayPoints();  // Display points after showing the question
    }

    private String getOperatorSymbol(int operator) {
        switch (operator) {
            case 0:
                return "+";
            case 1:
                return "-";
            case 2:
                return "*";
            case 3:
                return "/";
            default:
                return "+";
        }
    }

    @SuppressLint("SetTextI18n")
    private void displayPoints() {
        try {
            if (editText == null || textview4 == null || textview6 == null) {
                // Check for null references
                return;
            }

            if (editText.getText().toString().isEmpty()) {
                // EditText is empty, handle this case as needed
                return;
            }

            int userAnswer = Integer.parseInt(editText.getText().toString());
            int firstNumber = Integer.parseInt(textview4.getText().toString());
            int secondNumber = Integer.parseInt(textview6.getText().toString());
            int operator = getOperatorFromString(textview5.getText().toString());
            int correctAnswer = calculateAnswer(firstNumber, secondNumber, operator);

            if (textview7 == null || textview8 == null) {
                // Check for null references
                return;
            }

            if (userAnswer == correctAnswer) {
                points++;
            } else {
                points--;
            }

            textview7.setText(getString(R.string.label_txtview7) + " " + points);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            // Handle the case where the EditText is empty or non-numeric
        }
    }

    private int calculateAnswer(int firstNumber, int secondNumber, int operator) {
        switch (operator) {
            case 0:
                return firstNumber + secondNumber;
            case 1:
                return firstNumber - secondNumber;
            case 2:
                return firstNumber * secondNumber;
            case 3:
                return firstNumber / secondNumber;
            default:
                return 0;
        }
    }

    private int getOperatorFromString(String operatorString) {
        switch (operatorString) {
            case "+":
                return 0;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 3;
            default:
                return 0;
        }
    }
}
