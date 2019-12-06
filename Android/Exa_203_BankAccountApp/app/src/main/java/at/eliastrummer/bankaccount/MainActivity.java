package at.eliastrummer.bankaccount;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import at.eliastrummer.bankaccount.bl.Account;
import at.eliastrummer.bankaccount.bl.IOHandler;
import at.eliastrummer.bankaccount.list.AccountAdapter;

public class MainActivity extends AppCompatActivity {

    public static MainActivity main;

    private AccountAdapter accountAdapter;
    private RecyclerView rvAccountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;
        IOHandler.init(getApplication());

        accountAdapter = new AccountAdapter();

        rvAccountList = findViewById(R.id.rvAccountList);
        rvAccountList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvAccountList.setAdapter(accountAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mpFilterByStudents:
                accountAdapter.filterAccount("students");
                break;

            case R.id.mpFilterByGiro:
                accountAdapter.filterAccount("giro");
                break;

            case R.id.mpFilterByAll:
                accountAdapter.filterAccount(null);
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == 2){
            Account acc = (Account)data.getSerializableExtra("account");
            double amount = Double.parseDouble(data.getStringExtra("amount"));
            String iban = data.getStringExtra("iban");
            accountAdapter.transfer(acc.getIban(), amount, iban);
        }
    }
}
