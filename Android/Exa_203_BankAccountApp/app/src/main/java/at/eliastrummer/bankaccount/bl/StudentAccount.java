package at.eliastrummer.bankaccount.bl;

import java.io.Serializable;

public class StudentAccount extends Account implements Serializable {

    private boolean debitCard;

    public StudentAccount(String iban, double balance, float interest, boolean debitCard) {
        super(iban, balance, interest);
        this.debitCard = debitCard;
    }

    public StudentAccount(String param){
        super(param.split(",")[2], Double.parseDouble(param.split(",")[3]), Float.parseFloat(param.split(",")[6]));
        this.debitCard = Boolean.parseBoolean(param.split(",")[5]);
    }

    public boolean isDebitCard() {
        return debitCard;
    }

    public void setDebitCard(boolean debitCard) {
        this.debitCard = debitCard;
    }
}
