package main;

import beans.Gender;
import beans.Student;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IOAccess {
    private List<Student> students = new ArrayList<>();
    private static final Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "students.ser");

    public IOAccess(){
        students.add(new Student("Elias", "Trummer", LocalDate.of(2002, 10, 17), Gender.MALE));
        students.add(new Student("Martin", "Linhard", LocalDate.of(200, 4, 7), Gender.FEMALE));
    }

    public void writeToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(path.toFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(students);
        /*for(Student s : students){
            oos.writeObject(s);
        }*/
        oos.close();
    }

    public void readFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path.toFile());
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Student> studentsFromFile = (List<Student>) ois.readObject();
        /*List<Student> studentsFromFile = new ArrayList<>();

        try {
            while(true) {
                Student student = (Student) ois.readObject();
                studentsFromFile.add(student);
            }
        } catch (EOFException e) {
            System.out.println(e.toString());
        }*/


        studentsFromFile.forEach(s -> {
            System.out.println(s);
        });
    }

    public static void main(String[] args) {
        IOAccess ioa = new IOAccess();
        try {
            ioa.writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ioa.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
