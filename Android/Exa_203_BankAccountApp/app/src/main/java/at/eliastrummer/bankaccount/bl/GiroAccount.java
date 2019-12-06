package at.eliastrummer.bankaccount.bl;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class GiroAccount extends Account implements Serializable {

    private double overdraft;

    public GiroAccount(String iban, double balance, float interest, double overdraft) {
        super(iban, balance, interest);
        this.overdraft = overdraft;
    }

    public GiroAccount(String param){
        super(param.split(",")[2], Double.parseDouble(param.split(",")[3]), Float.parseFloat(param.split(",")[6]));
        this.overdraft = Double.parseDouble(param.split(",")[4]);
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
