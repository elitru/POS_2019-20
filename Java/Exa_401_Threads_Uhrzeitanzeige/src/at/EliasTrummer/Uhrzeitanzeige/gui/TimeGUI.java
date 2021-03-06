/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.EliasTrummer.Uhrzeitanzeige.gui;

import java.time.ZoneId;
import javafx.animation.KeyValue;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author elias
 */
public class TimeGUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    
    private TimePanel pnLocale;
    private TimePanel pnCountry1, pnCountry2;
    private Thread localeTime, country1Time, country2Time;
    private DefaultComboBoxModel<String> zoneModel1, zoneModel2;
    
    public TimeGUI() {
        initComponents();
        
        zoneModel1 = new DefaultComboBoxModel(ZoneId.getAvailableZoneIds().toArray());
        zoneModel2 = new DefaultComboBoxModel(ZoneId.getAvailableZoneIds().toArray());
        cbCountry1.setModel(zoneModel1);
        cbCountry2.setModel(zoneModel2);
        
        pnLocale = new TimePanel(null);
        pnCountry1 = new TimePanel(ZoneId.of((String) cbCountry1.getSelectedItem()));
        pnCountry2 = new TimePanel(ZoneId.of((String) cbCountry2.getSelectedItem()));
        pnClocks.add(pnLocale);
        pnClocks.add(pnCountry1);
        pnClocks.add(pnCountry2);
        
        localeTime = new Thread(pnLocale);
        country1Time = new Thread(pnCountry1);
        country2Time = new Thread(pnCountry2);
        localeTime.start();
        country1Time.start();
        country2Time.start();
        
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnContainer = new javax.swing.JPanel();
        pnLocation = new javax.swing.JPanel();
        lbLocale = new javax.swing.JLabel();
        cbCountry1 = new javax.swing.JComboBox<>();
        cbCountry2 = new javax.swing.JComboBox<>();
        pnClocks = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 400));

        pnContainer.setBackground(new java.awt.Color(51, 51, 51));
        pnContainer.setLayout(new java.awt.BorderLayout(25, 0));

        pnLocation.setBackground(new java.awt.Color(51, 51, 51));
        pnLocation.setPreferredSize(new java.awt.Dimension(200, 450));
        pnLocation.setLayout(new java.awt.GridLayout(3, 1, 100, 100));

        lbLocale.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        lbLocale.setForeground(new java.awt.Color(255, 255, 255));
        lbLocale.setText("Österreich");
        pnLocation.add(lbLocale);

        cbCountry1.setBackground(new java.awt.Color(51, 51, 51));
        cbCountry1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCountry1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCountry1Change(evt);
            }
        });
        pnLocation.add(cbCountry1);

        cbCountry2.setBackground(new java.awt.Color(51, 51, 51));
        cbCountry2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCountry2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onCountry2Change(evt);
            }
        });
        pnLocation.add(cbCountry2);

        pnContainer.add(pnLocation, java.awt.BorderLayout.WEST);

        pnClocks.setBackground(new java.awt.Color(51, 51, 51));
        pnClocks.setLayout(new java.awt.GridLayout(3, 1, 0, 30));
        pnContainer.add(pnClocks, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onCountry1Change(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCountry1Change
        pnCountry1.setTimeZone(ZoneId.of((String) cbCountry1.getSelectedItem()));
    }//GEN-LAST:event_onCountry1Change

    private void onCountry2Change(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onCountry2Change
        pnCountry2.setTimeZone(ZoneId.of((String) cbCountry2.getSelectedItem()));
    }//GEN-LAST:event_onCountry2Change

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbCountry1;
    private javax.swing.JComboBox<String> cbCountry2;
    private javax.swing.JLabel lbLocale;
    private javax.swing.JPanel pnClocks;
    private javax.swing.JPanel pnContainer;
    private javax.swing.JPanel pnLocation;
    // End of variables declaration//GEN-END:variables
}
