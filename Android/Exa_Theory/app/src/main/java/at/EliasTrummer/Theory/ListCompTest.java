package at.EliasTrummer.Theory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListCompTest {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Elias", "Trummer"));
        students.add(new Student("Elias", "Wilfinger"));
        students.add(new Student("Denis", "Imeri"));
        students.add(new Student("Alex", "Kirschner"));
        students.add(new Student("Martin", "Linhard"));

        students.sort((s1, s2) -> s1.getLastname().compareTo(s2.getLastname()));

        students.sort(Comparator.comparing(Student::getLastname).thenComparing(Student::getFirstname));

        /*students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getLastname().compareTo(o2.getLastname());
            }
        });*/
    }
}
