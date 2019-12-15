package at.eliastrummer.bankaccount;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import at.eliastrummer.bankaccount.bl.Account;
import at.eliastrummer.bankaccount.bl.GiroAccount;

public class Transfer extends AppCompatActivity {

    private ArrayList<String> ibans;
    private Account account;
    private AutoCompleteTextView tvIBANSTransfer;
    private EditText tvAmount;
    private Button tvSubmitTransfer;

    private double amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        Object temp = getIntent().getSerializableExtra("account");
        if(temp instanceof Account){
            account = (Account) temp;
        }

        ((TextView)findViewById(R.id.tvIBANTransferData)).setText(account.getIban());
        ((TextView)findViewById(R.id.tvBalanceTransferData)).setText(String.format("€ %10.2f", account.getBalance()));

        if(account instanceof GiroAccount){
            ((TextView)findViewById(R.id.tvAvailableData)).setText(String.format("€ %10.2f", account.getBalance() + ((GiroAccount)account).getOverdraft()));
        }else{
            ((TextView)findViewById(R.id.tvAvailableData)).setText(String.format("€ %10.2f", account.getBalance()));
        }

        ibans = getIntent().getStringArrayListExtra("ibans");

        tvIBANSTransfer = findViewById(R.id.tvIBANTransferValue);
        tvAmount = findViewById(R.id.tvAmountValue);
        tvSubmitTransfer = findViewById(R.id.tvSubmitTransfer);

        ArrayAdapter<String> adpater = new ArrayAdapter<>(getApplicationContext(), android.R.layout.select_dialog_item, ibans);
        tvIBANSTransfer.setAdapter(adpater);

        tvIBANSTransfer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    disable();
                }else{
                    if(!ibans.contains(s.toString())){
                        disable();
                        return;
                    }
                    enable();
                }
            }
        });

        tvAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().isEmpty()){
                    disable();
                    ((TextView)findViewById(R.id.tvBalanceTransferData)).setText("€ " + String.format("%10.2f", account.getBalance()));
                    return;
                }else{
                    String amountStr = s.toString().replace("€ ", "").replace(" ", "").replace(",", ".");

                    try{
                        amount = Double.parseDouble(amountStr);
                    }catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(), "Invalid number", Toast.LENGTH_LONG).show();
                        disable();
                        return;
                    }

                    double available = 0;

                    if(account instanceof GiroAccount){
                        available = ((GiroAccount)account).getBalance() + ((GiroAccount)account).getOverdraft();
                    }else{
                        available = account.getBalance();
                    }

                    ((TextView)findViewById(R.id.tvBalanceTransferData)).setText("€ " + String.format("%10.2f", account.getBalance() - amount));

                    if(available - amount < 0){
                        disable();
                        ((TextView)findViewById(R.id.tvBalanceTransferData)).setTextColor(getColor(R.color.red));
                        return;
                    }

                    ((TextView)findViewById(R.id.tvBalanceTransferData)).setTextColor(getColor(R.color.green));

                    enable();
                }
            }
        });
    }

    private void enable(){
        tvSubmitTransfer.setEnabled(true);
        tvSubmitTransfer.setBackgroundColor(getColor(R.color.green));
    }

    private void disable(){
        tvSubmitTransfer.setEnabled(false);
        tvSubmitTransfer.setBackgroundColor(getColor(R.color.grey));
    }

    public void onSubmitTransfer(View view) {
        Intent intent = getIntent();
        intent.putExtra("account", account);
        intent.putExtra("amount", String.format("%10.2f", amount).replace(",", "."));
        intent.putExtra("iban", tvIBANSTransfer.getText().toString());
        setResult(2, intent);
        finish();
    }
}
