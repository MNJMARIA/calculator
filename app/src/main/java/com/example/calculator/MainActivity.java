package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {
    private TextView tvInput;
    private TextView tvResult;
    private TextView tvOne;
    private TextView tvTwo;
    private TextView tvThree;
    private TextView tvFour;
    private TextView tvFive;
    private TextView tvSix;
    private TextView tvSeven;
    private TextView tvEight;
    private TextView tvNine;
    private TextView tvZero;
    private TextView tvDel;
    private TextView tvEquals;
    private TextView tvDot;
    private TextView tvMinus;
    private TextView tvPlus;
    private TextView tvMultiply;
    private TextView tvDivide;
    private TextView tvAC;
    private TextView tvCloseBrackets;
    private TextView tvOpenBrackets;


    private boolean lastInputWasOperator = false;
    private boolean lastInputWasMinus = false;
    private boolean lastInputWasSymbol = false;

    private boolean lastInputWasNumber = false;

    private int openBracketsCount = 0;
    private int closeBracketsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }
    private void initViews(){
        tvOne = findViewById(R.id.btn_one);
        tvTwo = findViewById(R.id.btn_two);
        tvThree = findViewById(R.id.btn_three);
        tvFour = findViewById(R.id.btn_four);
        tvFive = findViewById(R.id.btn_five);
        tvSix = findViewById(R.id.btn_six);
        tvSeven = findViewById(R.id.btn_seven);
        tvEight = findViewById(R.id.btn_eight);
        tvNine = findViewById(R.id.btn_nine);
        tvZero = findViewById(R.id.btn_zero);
        tvPlus = findViewById(R.id.btn_plus);
        tvMinus = findViewById(R.id.btn_minus);
        tvMultiply = findViewById(R.id.btn_multiply);
        tvDivide = findViewById(R.id.btn_divide);
        tvAC = findViewById(R.id.btn_ac);
        tvCloseBrackets = findViewById(R.id.btn_close_brackets);
        tvOpenBrackets = findViewById(R.id.btn_open_brackets);
        tvDel = findViewById(R.id.btn_del);
        tvDot = findViewById(R.id.btn_dot);
        tvEquals = findViewById(R.id.btn_equals);
        tvInput = findViewById(R.id.input_view);
        tvResult = findViewById(R.id.result_view);
    }

    private void initListeners(){
        tvOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("1");
            }
        });
        tvTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("2");
            }
        });
        tvThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("3");
            }
        });
        tvFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("4");
            }
        });
        tvFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("5");
            }
        });
        tvSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("6");
            }
        });
        tvSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("7");
            }
        });
        tvEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("8");
            }
        });
        tvNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("9");
            }
        });
        tvZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputNumber("0");
            }
        });
        tvPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputOperator("+");
            }
        });
        tvMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputOperator("-");
            }
        });
        tvMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputOperator("*");
            }
        });
        tvDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputOperator("/");
            }
        });
        tvDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputSymbol(".");
            }
        });
        tvOpenBrackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputSymbol("(");
            }
        });
        tvCloseBrackets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInputSymbol(")");
            }
        });




        tvAC.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tvInput.setText("");
                tvResult.setText("");
            }
        });

        tvDel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tvInputText =  tvInput.getText().toString();
                if(!tvInputText.isEmpty()){
                    tvInput.setText(tvInputText.substring(0, tvInputText.length() - 1));
                }
            }
        });

        tvEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String inputText = tvInput.getText().toString();

                    if(!inputText.isEmpty()){
                        Expression expression = new ExpressionBuilder(tvInput.getText().toString()).build();
                        double result = expression.evaluate();

                        tvResult.setText(result == (long)result ? String.valueOf((long)result) : String.valueOf(result));

                        // Очищення поля вводу
                        tvInput.setText("");
                    }

                }catch(Exception e){
                    Log.e("ERROR_TAG", e.getMessage().toString());
                }
            }
        });

    }
    /*private void updateResultInGray() {
        String inputText = tvInput.getText().toString();
        if (!inputText.isEmpty()) {
            try {
                Expression expression = new ExpressionBuilder(inputText).build();
                double result = expression.evaluate();
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.gray_text));
                tvResult.setText(result == (long) result ? String.valueOf((long) result) : String.valueOf(result));
            } catch (Exception e) {
                Log.e("ERROR_TAG", e.getMessage().toString());
            }
        }
    }*/

    private void addInputNumber(String number){
        if(lastInputWasOperator){
            tvInput.append(number);
            lastInputWasOperator = false;
        }else{
            tvInput.append(number);
        }
    }

    private void addInputOperator(String operator){
        String inputText = tvInput.getText().toString();
        int inputLength = tvInput.getText().length();

        if(inputLength > 0){
            char lastChar = inputText.charAt(inputLength - 1);
            if(lastChar != operator.charAt(0) && !lastInputWasOperator){
                tvInput.append(operator);
                lastInputWasOperator = true;
                lastInputWasSymbol = false;
                if(lastChar == '-'){
                    lastInputWasMinus = true;
                }
            }else if (lastInputWasOperator && !isOperator(inputText.charAt(inputLength - 1))) {
                // Оператор був видалений, дозволяємо новий оператор
                tvInput.append(operator);
                lastInputWasOperator = true;
                lastInputWasSymbol = false;
            }
        }else if(inputLength == 0){
            if(operator.equals("-")){
                tvInput.append(operator);
                lastInputWasOperator = true;
                lastInputWasSymbol = false;
            }else if(operator.equals("+")){
                tvInput.append(operator);
                lastInputWasOperator = true;
                lastInputWasSymbol = false;
            }
        }
    }
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private void addInputSymbol(String symbol){
        String inputText = tvInput.getText().toString();
        int inputLength = tvInput.getText().length();

        if (symbol.equals("(")) {
            if(inputLength >= 0){
                // Додавання відкритої дужки ( перевіряємо, чи попередній символ - оператор або дужка
                if (inputLength == 0 || lastInputWasOperator) {
                    tvInput.append(symbol);
                    lastInputWasSymbol = true;
                    lastInputWasOperator = false;
                    lastInputWasNumber = false;
                }
            }
        } else if (symbol.equals(")")) {
            if(inputLength > 0){
                // Додавання закритої дужки ( перевіряємо, чи попередній символ - число
                if (isNumber(inputText.charAt(inputLength - 1))) {
                    tvInput.append(symbol);
                    lastInputWasSymbol = true;
                    lastInputWasOperator = false;
                    lastInputWasNumber = false;
                }
            }
        }
        else if (symbol.equals(".")) {
            if(inputLength > 0){
                // Додавання крапки ( перевіряємо, чи попередній символ - число
                if (isNumber(inputText.charAt(inputLength - 1))) {
                    tvInput.append(symbol);
                    lastInputWasSymbol = true;
                    lastInputWasOperator = false;
                    lastInputWasNumber = false;
                }
            }
        }
    }


    private boolean isNumber(char c) {
        return Character.isDigit(c);
    }

}