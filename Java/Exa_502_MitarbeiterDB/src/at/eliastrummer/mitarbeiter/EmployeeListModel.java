package at.eliastrummer.mitarbeiter;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

public class EmployeeListModel extends AbstractListModel {

    private List<Employee> employees = new ArrayList<>();

    @Override
    public int getSize() {
        return employees.size();
    }

    @Override
    public Object getElementAt(int index) {
        return employees.get(index);
    }

    public void add(Employee e) {
        if (!employees.contains(e)) {
            employees.add(e);
            fireContentsChanged(this, 0, employees.size());
        }
    }

    public void addAll(List<Employee> e) {
        employees.addAll(e);
        fireContentsChanged(this, 0, employees.size());
    }

    public void clear() {
        employees.clear();
        fireContentsChanged(this, 0, employees.size());
    }
    
    public void remove(int perNr){
        employees.removeIf(e -> e.getPersNumber() == perNr);
        fireContentsChanged(this, 0, employees.size());
    }
}
