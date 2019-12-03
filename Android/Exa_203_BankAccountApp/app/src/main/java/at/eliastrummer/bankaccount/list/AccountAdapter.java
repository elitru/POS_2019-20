package at.eliastrummer.bankaccount.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import at.eliastrummer.bankaccount.MainActivity;
import at.eliastrummer.bankaccount.R;
import at.eliastrummer.bankaccount.bl.Account;
import at.eliastrummer.bankaccount.bl.GiroAccount;
import at.eliastrummer.bankaccount.bl.IOHandler;
import at.eliastrummer.bankaccount.bl.StudentAccount;

public class AccountAdapter extends RecyclerView.Adapter<AccountViewHolder> {

    private List<Account> all = IOHandler.getAccounts();
    private List<Account> filtered = new ArrayList<Account>(){{
        addAll(all);
    }};

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false);

        LinearLayout container = view.findViewById(R.id.container);
        TextView tvAccount = view.findViewById(R.id.tvAccount),
                tvBalance = view.findViewById(R.id.tvBalance),
                tvIBAN = view.findViewById(R.id.tvBalance),
                tvAvailable = view.findViewById(R.id.tvAvailable);

        return new AccountViewHolder(view, container, tvAccount, tvBalance, tvIBAN, tvAvailable);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {
        if(filtered.get(position) instanceof StudentAccount){
            StudentAccount acc = (StudentAccount)filtered.get(position);
            holder.getTvAccount().setText("Student-Account");
            holder.getTvIBAN().setText("IBAN: " + acc.getIban());
            holder.getTvBalance().setText("€ " + String.format("%,10.2f", acc.getBalance()));
            holder.getTvAvailable().setText("€ " + String.format("%,10.2f", acc.getBalance()));
        }else{
            GiroAccount acc = (GiroAccount)filtered.get(position);
            holder.getTvAccount().setText("Giro-Account");
            holder.getTvIBAN().setText("IBAN: " + acc.getIban());
            holder.getTvBalance().setText("€ " + String.format("%,10.2f", acc.getBalance()));
            holder.getTvAvailable().setText("€ " + String.format("%,10.2f", (
                    acc.getBalance() > 0 ? acc.getBalance() + acc.getOverdraft() : acc.getOverdraft() - acc.getBalance())));

            if(acc.getBalance() < 0){
                holder.getTvBalance().setTextColor(MainActivity.main.getColor(R.color.red));
            }else{
                holder.getTvBalance().setTextColor(MainActivity.main.getColor(R.color.green));
            }
        }
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }

    public void filterAccount(String filter){
        filtered.clear();
        filtered.addAll(all);

        if(filter == null){
            notifyDataSetChanged();
            return;
        }

        if(filter.equals("students")){
            filtered.removeIf(v -> v instanceof GiroAccount);
        }else{
            filtered.removeIf(v -> v instanceof StudentAccount);
        }

        notifyDataSetChanged();
    }
}
