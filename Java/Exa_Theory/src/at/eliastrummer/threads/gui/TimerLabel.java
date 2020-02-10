package at.eliastrummer.threads.gui;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;


public class TimerLabel extends JLabel implements Runnable {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");
    
    @Override
    public void run() {
        while(!Thread.interrupted()){
            LocalTime time = LocalTime.now();
            this.setText(DTF.format(time));
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                break;
            }
        }
        
        this.setText("00:00:00");
    }

}
