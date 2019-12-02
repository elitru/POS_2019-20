package at.eliastrummer.bankaccount.bl;

import androidx.annotation.NonNull;

public class GiroAccount extends Account{

    private double overdraft;

    public GiroAccount(String iban, double balance, float interest, double overdraft) {
        super(iban, balance, interest);
        this.overdraft = overdraft;
    }

    public GiroAccount(String param){
        super(null, 0, 0);
        //TODO:
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
