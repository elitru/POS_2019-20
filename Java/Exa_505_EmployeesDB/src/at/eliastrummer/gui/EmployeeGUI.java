/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.eliastrummer.gui;

import at.eliastrummer.beans.DepartmentManagerInfo;
import at.eliastrummer.beans.Employee;
import at.eliastrummer.beans.Filter;
import at.eliastrummer.beans.Salary;
import at.eliastrummer.bl.EmployeesModel;
import at.eliastrummer.database.DBAccess;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EmployeeGUI extends javax.swing.JFrame {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("d.M.yyyy");

    private DefaultComboBoxModel depModel = new DefaultComboBoxModel();
    private EmployeesModel employeesModel;
    private List<Employee> employees;
    private Filter filter;
    private boolean isFetching = false;

    public EmployeeGUI() {
        try {
            initComponents();
            setLocationRelativeTo(null);

            filter = new Filter("", true, true, LocalDate.now(), 0, 900);

            DBAccess.getInstance().connect();

            depModel.addElement("Alle");
            taEmployees.setAutoCreateRowSorter(true);
            DBAccess.getInstance().getDepartments().forEach(depModel::addElement);

            employees = DBAccess.getInstance().getEmployees("", true, true, LocalDate.now(), 0, 900);

            employeesModel = new EmployeesModel(employees);
            taEmployees.setModel(employeesModel);
            this.cbDepartment.setModel(depModel);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        spEmployees.getVerticalScrollBar().addAdjustmentListener(e -> {
            if (!e.getValueIsAdjusting()) {
                JScrollBar scrollBar = (JScrollBar) e.getAdjustable();
                int extent = scrollBar.getModel().getExtent();
                int maximum = scrollBar.getModel().getMaximum();
                if (extent + e.getValue() == maximum && !isFetching) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                isFetching = true;
                                updateCurrentFilter();
                                filter.setTo(filter.getTo() + 900);
                                employees = DBAccess.getInstance().getEmployees(filter.getDepartment(), filter.isMale(), filter.isFemale(), filter.getBirthdateBefore(), filter.getFrom(), filter.getTo());
                                isFetching = false;
                                employeesModel.setEmployees(employees);
                                String text = "";

                                if (!filter.getDepartment().equals("")) {
                                    for (DepartmentManagerInfo entry : DBAccess.getInstance().getDepartmentManagers(filter.getDepartment())) {
                                        text += entry.getManager().getLastname() + ", " + entry.getManager().getFirstname();
                                        text += ": from " + entry.getFrom().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                                        text += ": to " + (entry.getTo().equals(LocalDate.of(9999, 1, 1)) ? "now" : entry.getTo().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                                        text += "\n";
                                    }
                                }

                                epManager.setText(text);
                            } catch (SQLException ex) {
                                isFetching = false;
                                Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }).start();
                }
            }
        });

        taEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taEmployees.getSelectionModel().addListSelectionListener(lse -> {
            if (!lse.getValueIsAdjusting() && taEmployees.getSelectedRow() != -1) {
                List<Salary> salaries = employeesModel.getByRow(taEmployees.getSelectedRow()).getSalaries();
                salaries.removeIf(s -> s.getEmployeeId() != employeesModel.getByRow(taEmployees.getSelectedRow()).getId());
                salaries.sort(Comparator.comparing(Salary::getFrom));
                salaries.forEach(s -> {
                    epSalaries.setText(epSalaries.getText() + "Gehalt: " + s.getSalary() + " € (" + s.getFrom().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " - " + s.getTo().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ")\n");
                });
            }
        });
    }

    private void updateCurrentFilter() {
        LocalDate birthdateBefore = null;

        try {
            String date = tfBirthdateBefore.getText();

            if (date.isEmpty() || !chbBirthdateBefore.isSelected()) {
                birthdateBefore = LocalDate.now();
            } else {
                birthdateBefore = LocalDate.parse(tfBirthdateBefore.getText(), DTF);
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ungültiges Datum (Format: d.M.yyyy)");
            return;
        }

        filter = new Filter(((String) cbDepartment.getSelectedItem()).equals("Alle") ? "" : (String) cbDepartment.getSelectedItem(),
                !chbMale.isSelected() && !chbFemale.isSelected() ? true : chbMale.isSelected(),
                !chbMale.isSelected() && !chbFemale.isSelected() ? true : chbFemale.isSelected(),
                birthdateBefore, filter.getFrom(), filter.getTo());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbDepartment = new javax.swing.JComboBox<>();
        chbBirthdateBefore = new javax.swing.JCheckBox();
        tfBirthdateBefore = new javax.swing.JTextField();
        chbMale = new javax.swing.JCheckBox();
        chbFemale = new javax.swing.JCheckBox();
        btFilter = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        epManager = new javax.swing.JEditorPane();
        jPanel2 = new javax.swing.JPanel();
        spEmployees = new javax.swing.JScrollPane();
        taEmployees = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        epSalaries = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));
        jPanel3.setLayout(new java.awt.GridLayout(4, 2));

        jLabel1.setText("Department:");
        jPanel3.add(jLabel1);

        cbDepartment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(cbDepartment);

        chbBirthdateBefore.setText("Birthdate before (d.M.yyyy)");
        chbBirthdateBefore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbBirthdateBeforeActionPerformed(evt);
            }
        });
        jPanel3.add(chbBirthdateBefore);
        jPanel3.add(tfBirthdateBefore);

        chbMale.setSelected(true);
        chbMale.setText("Male");
        jPanel3.add(chbMale);

        chbFemale.setSelected(true);
        chbFemale.setText("Female");
        jPanel3.add(chbFemale);

        btFilter.setText("Filtern");
        btFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFilterActionPerformed(evt);
            }
        });
        jPanel3.add(btFilter);

        jPanel1.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Management"));
        jPanel4.setLayout(new java.awt.BorderLayout());

        epManager.setEditable(false);
        jScrollPane2.setViewportView(epManager);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1);

        jPanel2.setLayout(new java.awt.GridLayout(2, 0));

        taEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        spEmployees.setViewportView(taEmployees);

        jPanel2.add(spEmployees);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(2503, 24));
        jScrollPane1.setRequestFocusEnabled(false);

        epSalaries.setEditable(false);
        epSalaries.setMinimumSize(new java.awt.Dimension(400, 400));
        epSalaries.setPreferredSize(new java.awt.Dimension(250, 21));
        jScrollPane1.setViewportView(epSalaries);

        jPanel5.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5);

        getContentPane().add(jPanel2);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        DBAccess.getInstance().disconnect();
    }//GEN-LAST:event_formWindowClosing

    private void chbBirthdateBeforeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbBirthdateBeforeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbBirthdateBeforeActionPerformed

    private void btFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFilterActionPerformed
        updateCurrentFilter();
        reload();
    }//GEN-LAST:event_btFilterActionPerformed

    private void reload() {
        if (isFetching) {
            return;
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    isFetching = true;
                    updateCurrentFilter();
                    filter.setTo(filter.getTo() + 900);
                    employees = DBAccess.getInstance().getEmployees(filter.getDepartment(), filter.isMale(), filter.isFemale(), filter.getBirthdateBefore(), filter.getFrom(), filter.getTo());
                    isFetching = false;
                    employeesModel.setEmployees(employees);
                    String text = "";

                    if (!filter.getDepartment().equals("")) {
                        for (DepartmentManagerInfo entry : DBAccess.getInstance().getDepartmentManagers(filter.getDepartment())) {
                            text += entry.getManager().getLastname() + ", " + entry.getManager().getFirstname();
                            text += ": from " + entry.getFrom().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                            text += ": to " + (entry.getTo().equals(LocalDate.of(9999, 1, 1)) ? "now" : entry.getTo().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                            text += "\n";
                        }
                    }

                    epManager.setText(text);
                } catch (SQLException ex) {
                    isFetching = false;
                    Logger.getLogger(EmployeeGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }).start();
    }

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
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btFilter;
    private javax.swing.JComboBox<String> cbDepartment;
    private javax.swing.JCheckBox chbBirthdateBefore;
    private javax.swing.JCheckBox chbFemale;
    private javax.swing.JCheckBox chbMale;
    private javax.swing.JEditorPane epManager;
    private javax.swing.JEditorPane epSalaries;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane spEmployees;
    private javax.swing.JTable taEmployees;
    private javax.swing.JTextField tfBirthdateBefore;
    // End of variables declaration//GEN-END:variables
}
