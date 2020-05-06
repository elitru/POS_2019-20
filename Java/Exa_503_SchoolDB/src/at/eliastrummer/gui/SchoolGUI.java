/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.gui;

import at.eliastrummer.beans.Student;
import at.eliastrummer.database.DBAcces;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Elias
 */
public class SchoolGUI extends javax.swing.JFrame {

    private List<Student> students;
    private List<String> grades;
    private DefaultComboBoxModel cbModel = new DefaultComboBoxModel();
    private List<Student> filteredStudents = new ArrayList<>();
    private int pageIndex = 0;

    private boolean viewmode = true;

    public SchoolGUI() {
        initComponents();

        this.setLocationRelativeTo(null);
        this.cbClass.setModel(cbModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btConnect = new javax.swing.JButton();
        btImport = new javax.swing.JButton();
        btExport = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbClass = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfCatno = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfClass = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfFirstname = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        tfLastname = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tfBirthdate = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tfGender = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btNew = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        btStart = new javax.swing.JButton();
        btBefore = new javax.swing.JButton();
        btNext = new javax.swing.JButton();
        btEnd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        btConnect.setText("Verbinden");
        btConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConnectActionPerformed(evt);
            }
        });
        jPanel1.add(btConnect);

        btImport.setText("Importieren");
        btImport.setEnabled(false);
        btImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImportActionPerformed(evt);
            }
        });
        jPanel1.add(btImport);

        btExport.setText("Exportieren");
        btExport.setEnabled(false);
        btExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportActionPerformed(evt);
            }
        });
        jPanel1.add(btExport);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Klasse:");
        jLabel1.setAutoscrolls(true);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel3.add(jLabel1, java.awt.BorderLayout.CENTER);

        cbClass.setEnabled(false);
        cbClass.setMaximumSize(new java.awt.Dimension(50, 26));
        cbClass.setMinimumSize(new java.awt.Dimension(50, 26));
        cbClass.setPreferredSize(new java.awt.Dimension(200, 26));
        cbClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbClassActionPerformed(evt);
            }
        });
        jPanel3.add(cbClass, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel4.setLayout(new java.awt.GridLayout(4, 2));

        jPanel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        jLabel2.setText("Kat-Nr.:");
        jPanel5.add(jLabel2);

        tfCatno.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tfCatno.setEnabled(false);
        tfCatno.setPreferredSize(new java.awt.Dimension(140, 24));
        jPanel5.add(tfCatno);

        jPanel4.add(jPanel5);

        jPanel6.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel6.setLayout(new java.awt.GridLayout(1, 2));

        jLabel3.setText("Klasse:");
        jPanel6.add(jLabel3);

        tfClass.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tfClass.setEnabled(false);
        tfClass.setPreferredSize(new java.awt.Dimension(140, 24));
        jPanel6.add(tfClass);

        jPanel4.add(jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel7.setLayout(new java.awt.GridLayout(1, 2));

        jLabel4.setText("Vorname:");
        jPanel7.add(jLabel4);

        tfFirstname.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tfFirstname.setEnabled(false);
        tfFirstname.setPreferredSize(new java.awt.Dimension(140, 24));
        jPanel7.add(tfFirstname);

        jPanel4.add(jPanel7);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel8.setLayout(new java.awt.GridLayout(1, 2));

        jLabel5.setText("Nachname:");
        jPanel8.add(jLabel5);

        tfLastname.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tfLastname.setEnabled(false);
        tfLastname.setPreferredSize(new java.awt.Dimension(140, 24));
        jPanel8.add(tfLastname);

        jPanel4.add(jPanel8);

        jPanel9.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel9.setLayout(new java.awt.GridLayout(1, 2));

        jLabel6.setText("Geb. dat.: (dd.MM.yyyy)");
        jPanel9.add(jLabel6);

        tfBirthdate.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tfBirthdate.setEnabled(false);
        tfBirthdate.setPreferredSize(new java.awt.Dimension(140, 24));
        jPanel9.add(tfBirthdate);

        jPanel4.add(jPanel9);

        jPanel10.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel10.setLayout(new java.awt.GridLayout(1, 2));

        jLabel7.setText("Geschlecht: (m/w)");
        jPanel10.add(jLabel7);

        tfGender.setDisabledTextColor(new java.awt.Color(51, 51, 51));
        tfGender.setEnabled(false);
        tfGender.setPreferredSize(new java.awt.Dimension(140, 24));
        jPanel10.add(tfGender);

        jPanel4.add(jPanel10);

        jPanel11.setLayout(new java.awt.GridLayout(1, 2));

        btNew.setText("Neu");
        btNew.setEnabled(false);
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });
        jPanel11.add(btNew);

        btCancel.setText("Abbrechen");
        btCancel.setEnabled(false);
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });
        jPanel11.add(btCancel);

        jPanel4.add(jPanel11);

        jPanel12.setLayout(new java.awt.GridLayout(1, 4));

        btStart.setText("|<");
        btStart.setEnabled(false);
        btStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStartActionPerformed(evt);
            }
        });
        jPanel12.add(btStart);

        btBefore.setText("<");
        btBefore.setEnabled(false);
        btBefore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBeforeActionPerformed(evt);
            }
        });
        jPanel12.add(btBefore);

        btNext.setText(">");
        btNext.setEnabled(false);
        btNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNextActionPerformed(evt);
            }
        });
        jPanel12.add(btNext);

        btEnd.setText(">|");
        btEnd.setEnabled(false);
        btEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEndActionPerformed(evt);
            }
        });
        jPanel12.add(btEnd);

        jPanel4.add(jPanel12);

        jPanel2.add(jPanel4, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConnectActionPerformed
        final SchoolGUI _this = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (DBAcces.getInstance().isConnected()) {
                    if (DBAcces.getInstance().disconnect()) {
                        btBefore.setEnabled(false);
                        btNext.setEnabled(false);
                        btNew.setEnabled(false);
                        btExport.setEnabled(false);
                        btImport.setEnabled(false);
                        btEnd.setEnabled(false);
                        btStart.setEnabled(false);
                        cbClass.setEnabled(false);
                        cbModel.removeAllElements();
                        btConnect.setText("Verbinden");
                        return;
                    }

                    JOptionPane.showMessageDialog(_this, "Beim Trennen der Verbinung zu Datenbank ist ein Fehler aufgetreten!");

                } else {
                    if (DBAcces.getInstance().connect()) {
                        btBefore.setEnabled(true);
                        btNext.setEnabled(true);
                        btNew.setEnabled(true);
                        btExport.setEnabled(true);
                        btImport.setEnabled(true);
                        btEnd.setEnabled(true);
                        btStart.setEnabled(true);
                        cbClass.setEnabled(true);
                        btConnect.setText("Trennen");
                        grades = DBAcces.getInstance().getAllClasses();
                        students = DBAcces.getInstance().getAllDatabaseEntries();
                        reloadDisplay();
                        return;
                    }

                    JOptionPane.showMessageDialog(_this, "Beim Verbinden zur Datenbank ist ein Fehler aufgetreten!");
                }
            }
        }).start();
    }//GEN-LAST:event_btConnectActionPerformed

    private void reloadDisplay() {
        cbModel.removeAllElements();

        if (grades == null) {
            return;
        }

        grades.forEach(cbModel::addElement);

        //filter students
        if (cbModel.getSelectedItem() != null) {
            pageIndex = 0;
            filteredStudents.clear();
            filteredStudents.addAll(students);
            filteredStudents.removeIf(s -> !s.getClassname().equals(cbModel.getSelectedItem()));
            displayStudent();
        }
    }

    private void displayStudent() {
        if (filteredStudents.size() > 0 && pageIndex < filteredStudents.size()) {
            Student s = filteredStudents.get(pageIndex);
            tfCatno.setText(s.getCatno() + "");
            tfFirstname.setText(s.getFirstname());
            tfLastname.setText(s.getSurname());
            tfBirthdate.setText(s.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            tfClass.setText(s.getClassname());
            tfGender.setText(s.getGender());
        }
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (DBAcces.getInstance().isConnected()) {
            DBAcces.getInstance().disconnect();
        }
    }//GEN-LAST:event_formWindowClosing

    private void btImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImportActionPerformed
        final JFileChooser fc = new JFileChooser(Paths.get(System.getProperty("user.dir"), "src", "res").toFile());

        FileNameExtensionFilter filter = new FileNameExtensionFilter("SVG Files", "csv");
        fc.setFileFilter(filter);
        int result = fc.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            BufferedReader br = null;
            try {
                final SchoolGUI _this = this;
                List<Student> students = new ArrayList<>();/* new BufferedReader(new FileReader(fc.getSelectedFile()))
                .lines()
                .skip(1)
                .map(Student::parse)
                .collect(Collectors.toList());*/
                br = new BufferedReader(new FileReader(fc.getSelectedFile()));

                String line = "";
                String classname = br.readLine().split(";")[0];
                int counter = 1;

                while ((line = br.readLine()) != null) {
                    if (classname.equals(line.split(";")[0])) {
                        students.add(Student.parse(line, counter));
                        counter++;
                    } else {
                        classname = line.split(";")[0];
                        counter = 1;
                        students.add(Student.parse(line, counter));
                        counter++;
                    }
                }

                br.close();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (DBAcces.getInstance().deleteStudents() && DBAcces.getInstance().deleteClasses()) {
                            if (DBAcces.getInstance().importStudents(students)) {
                                JOptionPane.showMessageDialog(_this, "Data was successfully imported into the database!");
                            } else {
                                JOptionPane.showMessageDialog(_this, "An error occured during import!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(_this, "An error occured during deleting existing entries!");
                        }
                    }
                }).start();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SchoolGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SchoolGUI.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(SchoolGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btImportActionPerformed

    private void btStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStartActionPerformed
        pageIndex = 0;
        displayStudent();
    }//GEN-LAST:event_btStartActionPerformed

    private void btEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEndActionPerformed
        pageIndex = filteredStudents.size() - 1;
        displayStudent();
    }//GEN-LAST:event_btEndActionPerformed

    private void btBeforeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBeforeActionPerformed
        if (pageIndex == 0 || filteredStudents.size() == 0) {
            return;
        }

        pageIndex--;
        displayStudent();
    }//GEN-LAST:event_btBeforeActionPerformed

    private void btNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNextActionPerformed
        if (pageIndex == filteredStudents.size() - 1 || filteredStudents.size() == 0) {
            return;
        }

        pageIndex++;
        displayStudent();
    }//GEN-LAST:event_btNextActionPerformed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        if (!viewmode) {
            String firstname = tfFirstname.getText();
            String surname = tfLastname.getText().toUpperCase();
            String bd = tfBirthdate.getText();
            String classname = tfClass.getText().toUpperCase();
            String gender = tfGender.getText();

            if (firstname.equals("") || surname.equals("") || bd.equals("") || classname.equals("") || gender.equals("")) {
                JOptionPane.showMessageDialog(this, "Alle Felder müssen ausgefüllt sein!");
                return;
            }

            try {
                LocalDate birthdate = LocalDate.parse(bd, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                Student student = new Student(-1, classname, 0, firstname, surname, gender, birthdate);

                SchoolGUI _this = this;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (DBAcces.getInstance().insertStudent(student)) {
                            grades = DBAcces.getInstance().getAllClasses();
                            students = DBAcces.getInstance().getAllDatabaseEntries();
                            reloadDisplay();
                            JOptionPane.showMessageDialog(_this, "Student was successfully inserted!");
                            tfFirstname.setEnabled(false);
                            tfLastname.setEnabled(false);
                            tfBirthdate.setEnabled(false);
                            tfGender.setEnabled(false);
                            tfClass.setEnabled(false);
                            btNew.setText("Neu");
                            viewmode = !viewmode;
                            btCancel.setEnabled(false);
                            return;
                        }
                        JOptionPane.showMessageDialog(_this, "An error occured during insertion of student!");
                    }
                }).start();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ungültiges Geburtsdatum");
                return;
            }
        } else {
            tfCatno.setText("");
            //tfCatno.setEnabled(true);
            tfFirstname.setText("");
            tfFirstname.setEnabled(true);
            tfLastname.setText("");
            tfLastname.setEnabled(true);
            tfBirthdate.setText("");
            tfBirthdate.setEnabled(true);
            tfGender.setText("");
            tfGender.setEnabled(true);
            tfClass.setText("");
            tfClass.setEnabled(true);
            btNew.setText("Speichern");
            btCancel.setEnabled(true);

            viewmode = !viewmode;
        }
    }//GEN-LAST:event_btNewActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        tfCatno.setEnabled(false);
        tfFirstname.setEnabled(false);
        tfLastname.setEnabled(false);
        tfBirthdate.setEnabled(false);
        tfGender.setEnabled(false);
        tfClass.setEnabled(false);
        btNew.setText("Neu");
        btCancel.setEnabled(false);
        viewmode = true;
        reloadDisplay();
    }//GEN-LAST:event_btCancelActionPerformed

    private void cbClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbClassActionPerformed
        //filter students
        if (cbModel.getSelectedItem() != null) {
            pageIndex = 0;
            filteredStudents.clear();
            filteredStudents.addAll(students);
            filteredStudents.removeIf(s -> !s.getClassname().equals(cbModel.getSelectedItem()));
            displayStudent();
        }

        displayStudent();
    }//GEN-LAST:event_cbClassActionPerformed

    private void btExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportActionPerformed
        final JFileChooser fc = new JFileChooser(Paths.get(System.getProperty("user.dir"), "src", "res").toFile());

        FileNameExtensionFilter filter = new FileNameExtensionFilter("SVG Files", "csv");
        fc.setFileFilter(filter);
        SchoolGUI _this = this;
        int result = fc.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            final File file = fc.getSelectedFile();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    BufferedWriter bw = null;
                    try {
                        String fileContent = DBAcces.getInstance().export();
                        bw = new BufferedWriter(new FileWriter(file));
                        bw.write(fileContent);
                        bw.close();
                        JOptionPane.showMessageDialog(_this, "Data was exported sccessfully!");
                    } catch (IOException ex) {
                        Logger.getLogger(SchoolGUI.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            bw.close();
                        } catch (IOException ex) {
                            Logger.getLogger(SchoolGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();
        }
    }//GEN-LAST:event_btExportActionPerformed

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
            java.util.logging.Logger.getLogger(SchoolGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SchoolGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SchoolGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SchoolGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SchoolGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btBefore;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btConnect;
    private javax.swing.JButton btEnd;
    private javax.swing.JButton btExport;
    private javax.swing.JButton btImport;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btNext;
    private javax.swing.JButton btStart;
    private javax.swing.JComboBox<String> cbClass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JTextField tfBirthdate;
    private javax.swing.JTextField tfCatno;
    private javax.swing.JTextField tfClass;
    private javax.swing.JTextField tfFirstname;
    private javax.swing.JTextField tfGender;
    private javax.swing.JTextField tfLastname;
    // End of variables declaration//GEN-END:variables
}
