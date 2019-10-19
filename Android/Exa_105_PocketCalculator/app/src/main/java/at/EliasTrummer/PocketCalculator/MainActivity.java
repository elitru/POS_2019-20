package at.EliasTrummer.PocketCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import at.EliasTrummer.PocketCalculator.BL.CrazyStack;

public class MainActivity extends AppCompatActivity {

    private TextView tvInput;
    private String current = "";
    private boolean isResultDisplayed = false;
    private CrazyStack stack = new CrazyStack();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.btClear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClear(v);
            }
        });

        ((Button)findViewById(R.id.btComma)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onComma(v);
            }
        });

        ((Button)findViewById(R.id.btChangeNumberPreSign)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSign(v);
            }
        });

        ((Button)findViewById(R.id.btEnter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEnter(v);
            }
        });

        Button[] operatorBtns = new Button[] {
                findViewById(R.id.btDivide),
                findViewById(R.id.btMultiply),
                findViewById(R.id.btAdd),
                findViewById(R.id.btSubtract)
        };

        for(int i = 0; i < operatorBtns.length; i++){
            operatorBtns[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onOperator(v);
                }
            });
        }

        tvInput = findViewById(R.id.tvInput);
        tvInput.setText("0");
        current = "0";
    }

    public void onClear(View view) {
        tvInput.setText("0");
        current = "0";

        while(!stack.isEmpty()){
            stack.pop();
        }
    }

    public void onEnter(View view) {
        if(!current.isEmpty()){
            if(isResultDisplayed){
                Toast.makeText(getApplicationContext(), "Sie müssen zuerst eine zweite Zahl eingeben!", Toast.LENGTH_LONG).show();
                return;
            }
            if(current.charAt(0) == '-' && current.length() == 1){
                return;
            }

            if(current.endsWith(",")){
                return;
            }

            double number = Double.parseDouble(current.replace(",", "."));
            try {
                stack.push(number);
            }catch (RuntimeException e){
                Toast.makeText(getApplicationContext(), "StackOverflow! Rechner wurde auf 0 zurückgesetzt!", Toast.LENGTH_LONG).show();
                onClear(null);
                return;
            }
            current = "";
            tvInput.setText("");
        }
    }

    public void onComma(View view){
        String text = ((Button)view).getText().toString();

        //Commas
        if(!current.contains(",") && !current.isEmpty()){
            if(!(current.length() == 1 && current.charAt(0) == '-')){
                current += ",";
                tvInput.setText(current);
            }
        }
    }

    public void onSign(View view){
        String text = ((Button)view).getText().toString();

        //Vorzeichen
        if(text.equals("+ / -")){
            if(current.startsWith("-")){
                current = current.substring(1);
            }else{
                current = "-" + current;
            }
            tvInput.setText(current);
            return;
        }
    }

    public void onDigitClick(View view) {
        Button btn = (Button) view;
        String text = btn.getText().toString();

        if(current.replace(",", "").replace("-", "").length() == 12){
            Toast.makeText(getApplicationContext(), "Die Zahl darf maximal 12 Stellen lang sein!", Toast.LENGTH_LONG).show();
            return;
        }

        if(current.startsWith("0")){
            if(current.length() < 2) {
                current = "";
                tvInput.setText("");
            }
        }

        if(current.isEmpty() && text.equals("0")){
            return;
        }

        if(isResultDisplayed){
            current = "";
            isResultDisplayed = false;
        }

        //Ziffern
        current += text;
        tvInput.setText(current);
    }

    public void onOperator(View view) {
        String operator = ((Button)view).getText().toString();

        if(stack.isEmpty()){
            Toast.makeText(getApplicationContext(), "Sie müssen zuerst zwei Zahlen eingeben!", Toast.LENGTH_LONG).show();
            return;
        }

        if(stack.length() == 1){
            if(!current.isEmpty() && !isResultDisplayed){
                try{
                    double num = Double.parseDouble(current.replace(",", "."));
                    stack.push(num);
                    current = "";
                    tvInput.setText("");
                }catch(NumberFormatException e){
                    return;
                }catch(RuntimeException e){
                    Toast.makeText(getApplicationContext(), "StackOverflow! Rechner wurde auf 0 zurückgesetzt!", Toast.LENGTH_LONG).show();
                    onClear(null);
                    return;
                }
            }else{
                Toast.makeText(getApplicationContext(), "Sie müssen erst eine zweite Zahl eingeben!", Toast.LENGTH_LONG).show();
                return;
            }
        }

        double lastPushedNumberToStack = stack.pop();
        double oneNumberBeforeLastPushedNumberToStack = stack.pop();

        double result = 0;

        try {
            result = calculate(operator, oneNumberBeforeLastPushedNumberToStack, lastPushedNumberToStack);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Ungültige Eingabe! Division durch 0 nicht möglich!", Toast.LENGTH_LONG).show();
            onClear(null);
            return;
        }

        stack.push(result);

        if((Math.floor(result) == result) && !Double.isInfinite(result)){
            current = String.format("%.0f", result).replace(".", ".");
        }else{
            current = String.format("%.5f", result).replace(".", ".");
        }

        tvInput.setText(current);
        isResultDisplayed = true;
    }

    private double calculate(String operator, double firstNum, double secondNum){
        switch (operator){
            case "+":
                return firstNum + secondNum;

            case "-":
                return firstNum - secondNum;

            case "*":
                return firstNum * secondNum;

            case "/":
                if(secondNum == 0){
                    throw new IllegalArgumentException("Division by 0");
                }

                return firstNum / secondNum;

            default:
                return 0;
        }
    }
}
