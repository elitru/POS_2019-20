package at.eliastrummer.bl;

import at.eliastrummer.beans.Employee;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class EmployeesModel extends AbstractTableModel {

    private List<Employee> employees = new ArrayList<>();

    public EmployeesModel(List<Employee> employees) {
        this.employees.addAll(employees);
        employees.sort(Comparator.comparing(Employee::getLastname).thenComparing(Employee::getFirstname));
        fireTableRowsUpdated(0, employees.size());
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return Employee.COLUMN_TILES.length;
    }

    @Override
    public String getColumnName(int column) {
        return Employee.COLUMN_TILES[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return employees.get(rowIndex).getValueForColumn(columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        employees.get(rowIndex).setValueForColumn(columnIndex, aValue);
        fireTableRowsUpdated(0, employees.size());
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 3 ? true : false;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees.clear();
        this.employees.addAll(employees);
        try{
            fireTableRowsInserted(0, employees.size() - 1);
        }catch(IndexOutOfBoundsException e){
            
        }
    }

    public void addEmployees(List<Employee> employees) {
        this.employees.addAll(employees);
        try{
            fireTableRowsInserted(0, employees.size() - 1);
        }catch(IndexOutOfBoundsException e){
            
        }
    }
}
