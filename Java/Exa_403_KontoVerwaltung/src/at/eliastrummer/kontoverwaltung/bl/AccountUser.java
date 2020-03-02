package at.eliastrummer.kontoverwaltung.bl;

import java.util.Objects;
import java.util.Random;
import javax.swing.JTextArea;

public class AccountUser implements Runnable{

    private String name;
    private Account account;
    private JTextArea logger;
    
    private static final Random RND = new Random();

    public AccountUser(String name, Account account, JTextArea logger) {
        this.name = name;
        this.account = account;
        this.logger = logger;
    }    

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            try {
                logger.append(name + " has started\n");
                int amount = RND.nextInt(41) + 10;
                
                amount = RND.nextBoolean() ? amount : amount * (-1);
                
                synchronized(account){
                    logger.append("Trying to make transaction --> " + amount + "\n");
                    while((account.getBalance() + amount) < 0){
                        try {
                            logger.append(name + " is waiting now\n");
                            account.wait(2000);
                            logger.append(name + " has finished waiting\n");
                        } catch (InterruptedException ex) {}
                    }
                    
                    account.performTransaction(amount);
                    logger.append(name + " performed transaction: " + amount + "\n");
                    account.notifyAll();
                }
                
                Thread.sleep(RND.nextInt(1000) + 1);
            } catch (InterruptedException ex) {}
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AccountUser other = (AccountUser) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    } 

    @Override
    public String toString() {
        return this.name;
    }
}
