package at.EliasTrummer.CurrencyConverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton btConvert;
    private EditText etInput;
    private TextView tvFrom;
    private TextView tvTo;
    private TextView tvOutput;

    private final float EXCHANGE_RATE = 1.1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btConvert = findViewById(R.id.btConvert);
        etInput = findViewById(R.id.etInput);
        tvFrom = findViewById(R.id.tvCurrencyFrom);
        tvTo = findViewById(R.id.tvCurrencyTo);
        tvOutput = findViewById(R.id.tvOutput);

        //set default convertion text
        tvOutput.setText(String.format(getString(R.string.eurToUsd), 1f, EXCHANGE_RATE));

        //Events
        btConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvFrom.getText().equals(getString(R.string.eur))){
                    tvFrom.setText(getString(R.string.usd));
                    tvTo.setText(getString(R.string.eur));
                    etInput.setHint(R.string.hintUSD);

                    String input = etInput.getText().toString();
                    if(!input.isEmpty()){
                        tvOutput.setText(String.format(getString(R.string.usdToEur), Float.parseFloat(input), Float.parseFloat(input) / EXCHANGE_RATE));
                    }else{
                        tvOutput.setText(String.format(getString(R.string.usdToEur), 1f, (float)(1 / EXCHANGE_RATE)));
                    }
                }else{
                    tvFrom.setText(getString(R.string.eur));
                    tvTo.setText(getString(R.string.usd));
                    etInput.setHint(R.string.hintEur);

                    String input = etInput.getText().toString();
                    if(!input.isEmpty()){
                        tvOutput.setText(String.format(getString(R.string.eurToUsd), Float.parseFloat(input), Float.parseFloat(input) * EXCHANGE_RATE));
                    }else{
                        tvOutput.setText(String.format(getString(R.string.eurToUsd), 1f, EXCHANGE_RATE));
                    }
                }
            }
        });

        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();

                try{
                    float temp = Float.parseFloat(input);
                }catch(Exception e){
                    return;
                }

                if(input.isEmpty()){
                    return;
                }

                if(tvFrom.getText().equals(getString(R.string.eur))){
                    //from euro to usd
                    tvOutput.setText(String.format(getString(R.string.eurToUsd), Float.parseFloat(input), Float.parseFloat(input) * EXCHANGE_RATE));
                }else{
                    //from usd to euro
                    tvOutput.setText(String.format(getString(R.string.usdToEur), Float.parseFloat(input), Float.parseFloat(input) / EXCHANGE_RATE));
                }
            }
        });
    }
}
