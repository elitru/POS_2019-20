package at.eliastrummer.bankaccount.list;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout container;
    private TextView tvAccount;
    private TextView tvBalance;
    private TextView tvIBAN;
    private TextView tvAvailable;

    public AccountViewHolder(@NonNull View itemView, LinearLayout container, TextView tvAccount, TextView tvBalance, TextView tvIBAN, TextView tvAvailable) {
        super(itemView);
        this.container = container;
        this.tvAccount = tvAccount;
        this.tvBalance = tvBalance;
        this.tvIBAN = tvIBAN;
        this.tvAvailable = tvAvailable;
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
