package com.example.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //fields
    private EditText result;
    private EditText newNumber;
    private TextView operation;
    private String key = "temp";
    private String key2 = "temp2";

    //variable to store user inputs and type of calculation
    private Double operand1 = null;
    private String operationType = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //finds the appropriate widgets and attaches to the references created
        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        operation = findViewById(R.id.operation);

        //find the buttons and attaches to the references declared
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttondot = findViewById(R.id.buttondot);

        Button buttonadd = findViewById(R.id.buttonadd);
        Button buttonsub = findViewById(R.id.buttonsub);
        Button buttonmult = findViewById(R.id.buttonmult);
        Button buttondiv = findViewById(R.id.buttondiv);
        Button buttonequ = findViewById(R.id.buttonequ);

        Button buttonNeg = findViewById(R.id.buttonNeg);
        Button buttonClear = findViewById(R.id.buttonClear);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());
            }
        };

        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        buttondot.setOnClickListener(listener);

        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String value = newNumber.getText().toString();
                try {
                    Double doubleValue = Double.valueOf(value);
                    performOperation(doubleValue);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                operationType = b.getText().toString();
                operation.setText(operationType);

            }
        };

        buttonadd.setOnClickListener(listener2);
        buttonsub.setOnClickListener(listener2);
        buttonmult.setOnClickListener(listener2);
        buttondiv.setOnClickListener(listener2);
        buttonequ.setOnClickListener(listener2);

        View.OnClickListener listener3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(newNumber.getText().toString().equals("")){
                    newNumber.append("-");
                } else if(newNumber.getText().toString().equals("-")){
                    newNumber.setText("");
                } else if(!newNumber.getText().toString().contains("-")){
                    String s = "-" + newNumber.getText().toString();
                    newNumber.setText(s);
                } else{
                    newNumber.setText(newNumber.getText().toString().substring(1));
                }
            }
        };

        buttonNeg.setOnClickListener(listener3);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                newNumber.setText("");
                operation.setText("=");
                operationType = "=";
            }
        });

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operationType = savedInstanceState.getString(key);
        operand1 = savedInstanceState.getDouble(key2);
        operation.setText(operationType);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(key, operationType);
        if(operand1 != null){
            outState.putDouble(key2, operand1);
        }
        super.onSaveInstanceState(outState);
    }

    //helper methods
    private void performOperation(Double value) {
        if (operand1 == null) {
            operand1 = value;
        } else {
            switch (operationType) {
                case "=":
                    operand1 = value;
                    break;
                case "/":
                    if (value == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 /= value;
                    }
                    break;
                case "*":
                    operand1 *= value;
                    break;
                case "-":
                    operand1 -= value;
                    break;
                case "+":
                    operand1 += value;
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}
