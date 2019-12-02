package at.eliastrummer.bankaccount.bl;

public class StudentAccount extends Account {

    private boolean debitCard;

    public StudentAccount(String iban, double balance, float interest, boolean debitCard) {
        super(iban, balance, interest);
        this.debitCard = debitCard;
    }

    public StudentAccount(String param){
        super(null, 0, 0);
        //TODO:
    }

    public boolean isDebitCard() {
        return debitCard;
    }

    public void setDebitCard(boolean debitCard) {
        this.debitCard = debitCard;
    }
}
