package at.eliastrummer.bankaccount.bl;

import android.os.Parcel;
import java.io.Serializable;

public class Account implements Serializable {

    protected String iban;
    protected double balance;
    protected float interest;

    public Account(String iban, double balance, float interest) {
        this.iban = iban;
        this.balance = balance;
        this.interest = interest;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }
}
