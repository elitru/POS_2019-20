package at.eliastrummer.bankaccount.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import at.eliastrummer.bankaccount.Transfer;
import at.eliastrummer.bankaccount.bl.Account;

public class AccountViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout container;
    private TextView tvAccount;
    private TextView tvBalance;
    private TextView tvIBAN;
    private TextView tvAvailable;

    private Account acc;
    private ArrayList<String> ibans;

    public AccountViewHolder(@NonNull View itemView, LinearLayout container, TextView tvAccount, TextView tvBalance, TextView tvIBAN, TextView tvAvailable, ArrayList<String> ibans) {
        super(itemView);
        this.container = container;
        this.tvAccount = tvAccount;
        this.tvBalance = tvBalance;
        this.tvIBAN = tvIBAN;
        this.tvAvailable = tvAvailable;

        this.ibans = ibans;

        container.setOnLongClickListener(v -> {

            Intent intent = new Intent(itemView.getContext(), Transfer.class);
            intent.putExtra("account", acc);
            intent.putStringArrayListExtra("ibans", ibans);
            ((Activity)itemView.getContext()).startActivityForResult(intent, 1);

            return true;
        });
    }

    public void setAccount(Account acc) {
        this.acc = acc;
    }

    public LinearLayout getContainer() {
        return container;
    }

    public void setContainer(LinearLayout container) {
        this.container = container;
    }

    public TextView getTvAccount() {
        return tvAccount;
    }

    public void setTvAccount(TextView tvAccount) {
        this.tvAccount = tvAccount;
    }

    public TextView getTvBalance() {
        return tvBalance;
    }

    public void setTvBalance(TextView tvBalance) {
        this.tvBalance = tvBalance;
    }

    public TextView getTvIBAN() {
        return tvIBAN;
    }

    public void setTvIBAN(TextView tvIBAN) {
        this.tvIBAN = tvIBAN;
    }

    public TextView getTvAvailable() {
        return tvAvailable;
    }

    public void setTvAvailable(TextView tvAvailable) {
        this.tvAvailable = tvAvailable;
    }
}
