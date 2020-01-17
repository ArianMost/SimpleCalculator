package com.example.arian.simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;

    Button btn_clear;
    Button btn_clearEntity;
    Button btn_back;
    Button btn_sign;
    Button btn_point;

    Button btn_division;
    Button btn_X;
    Button btn_add;
    Button btn_minus;
    Button btn_equal;

    TextView txt_result;
    TextView txt_history;

    boolean reset = false;
    float currentResult = 0;
    String oldOperand = "";


  public void appendNumber(int num) {
    if (reset){
      txt_result.setText("");
      reset = false;
    }

    String oldValue = txt_result.getText().toString();
    if (txt_result.length() > 9){
      return;
    }
    if (oldValue.equals("0")){
      oldValue = Integer.toString(num);
      txt_result.setText(oldValue);
    }
    else {
      txt_result.setText(oldValue + num);
    }
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    btn_0 = (Button)findViewById(R.id.btn_0);
    btn_1 = (Button)findViewById(R.id.btn_1);
    btn_2 = (Button)findViewById(R.id.btn_2);
    btn_3 = (Button)findViewById(R.id.btn_3);
    btn_4 = (Button)findViewById(R.id.btn_4);
    btn_5 = (Button)findViewById(R.id.btn_5);
    btn_6 = (Button)findViewById(R.id.btn_6);
    btn_7 = (Button)findViewById(R.id.btn_7);
    btn_8 = (Button)findViewById(R.id.btn_8);
    btn_9 = (Button)findViewById(R.id.btn_9);

    btn_clear = (Button)findViewById(R.id.btn_clear);
    btn_clearEntity = (Button)findViewById(R.id.btn_clearEntity);
    btn_back = (Button)findViewById(R.id.btn_back);
    btn_sign = (Button)findViewById(R.id.btn_sign);
    btn_point = (Button)findViewById(R.id.btn_point);

    btn_division = (Button)findViewById(R.id.btn_division);
    btn_X = (Button)findViewById(R.id.btn_X);
    btn_add = (Button)findViewById(R.id.btn_add);
    btn_minus = (Button)findViewById(R.id.btn_minus);
    btn_equal = (Button)findViewById(R.id.btn_equal);

    txt_result = (TextView) findViewById(R.id.txt_result);
    txt_history = (TextView) findViewById(R.id.txt_history);


    View.OnClickListener numListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int index = Integer.parseInt(v.getTag().toString());
        appendNumber(index);
      }
    };
    btn_0.setOnClickListener(numListener);
    btn_1.setOnClickListener(numListener);
    btn_2.setOnClickListener(numListener);
    btn_3.setOnClickListener(numListener);
    btn_4.setOnClickListener(numListener);
    btn_5.setOnClickListener(numListener);
    btn_6.setOnClickListener(numListener);
    btn_7.setOnClickListener(numListener);
    btn_8.setOnClickListener(numListener);
    btn_9.setOnClickListener(numListener);


    btn_clear.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        txt_result.setText("0");
        txt_history.setText("");
        currentResult = 0;
        reset = false;
      }
    });
    btn_clearEntity.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        txt_result.setText("0");
      }
    });
    btn_point.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String oldValue = txt_result.getText().toString();
        if (reset){
          txt_result.setText("0");
          reset = false;
        }
        if (oldValue.contains(".")){
          return;
        }else {
          txt_result.setText(oldValue + ".");
        }
      }
    });

    View.OnClickListener operandListener = new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        operand(v.getTag().toString());
      }
    };

    btn_minus.setOnClickListener(operandListener);
    btn_add.setOnClickListener(operandListener);
    btn_X.setOnClickListener(operandListener);
    btn_division.setOnClickListener(operandListener);

    btn_equal.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        compute(Float.parseFloat(txt_result.getText().toString()));
        txt_result.setText("" + currentResult);
        oldOperand = "";
        reset = true;
        txt_history.setText("");
      }
    });
    btn_back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        backOneLetter();
      }
    });
    btn_sign.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      String value = txt_result.getText().toString();
        if (value.contains("-")){
          value.replace("-" , "");
        }else {
          value = "-" + value;
        }
        txt_result.setText(value);
      }
    });

  }

  public void backOneLetter() {
    String value = txt_result.getText().toString();
    if (value.length() == 1){
      txt_result.setText("0");
    }else {
      String newValue = value.substring(0, value.length() - 1);
      txt_result.setText(newValue);
    }

  }

  public void operand(String operand) {
    txt_history.setText(txt_history.getText().toString() + " " + txt_result.getText().toString() + " " + operand);
    compute(Float.parseFloat(txt_result.getText().toString()));
    txt_result.setText("" + currentResult);
    oldOperand = operand;
    reset = true;
  }

  public void compute(float currentNumber) {

    switch (oldOperand) {
      case "+":
        currentResult += currentNumber;
        break;
      case "-":
        currentResult -= currentNumber;
        break;
      case "x":
        currentResult *= currentNumber;
        break;
      case "/":
        currentResult /= currentNumber;
        break;
      case "":
        currentResult = currentNumber;
    }
  }
}
