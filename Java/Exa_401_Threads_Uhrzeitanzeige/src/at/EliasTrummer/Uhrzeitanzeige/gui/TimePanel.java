package at.EliasTrummer.Uhrzeitanzeige.gui;

import java.awt.GridLayout;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;


public class TimePanel extends JPanel implements Runnable{
    
    private DigitLabel[] digits;
    private ZoneId timeZone;
    
    public TimePanel(ZoneId timeZone){
        this.timeZone = timeZone;
        this.setLayout(new GridLayout(1, 8, 0, 10));
        
        digits = new DigitLabel[8];
        for(int i = 0; i < digits.length; i++){
            digits[i] = new DigitLabel(0);
            
            if(i == 2 || i == 5){
                digits[i].setDigit(-1);
                digits[i].repaint();
            }
            
            this.add(digits[i]);
        }
    }

    public void setTimeZone(ZoneId timeZone) {
        this.timeZone = timeZone;
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            LocalTime time = timeZone == null ? LocalTime.now() : LocalTime.now(timeZone);
            
            String timeData = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            char[] digits = timeData.toCharArray();
            
            for(int i = 0; i < digits.length; i++){
                if(i == 2 || i == 5){
                    continue;
                }
                
                this.digits[i].setDigit(Integer.parseInt(digits[i] + ""));
                this.digits[i].repaint();
            }
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }

}
