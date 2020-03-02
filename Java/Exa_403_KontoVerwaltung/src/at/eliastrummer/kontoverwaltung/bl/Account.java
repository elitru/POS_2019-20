package at.eliastrummer.kontoverwaltung.bl;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Account {
    private double balance;
    private JLabel label;
    private JTextArea logger;

    public Account(double balance, JLabel label, JTextArea logger) {
        this.balance = balance;
        this.label = label;
        this.logger = logger;
        
        this.logger.setText("Account has been initialized with 50€");
    }

    public double getBalance() {
        return balance;
    }
    
    public void performTransaction(int amount){
        if(balance + amount < 0){
            throw new RuntimeException("Balance cannot be negative");
        }
        
        balance += amount;
        label.setText(String.format("%.2f", balance));
    }
}
