package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = "C:\\Users\\Elias\\Desktop\\test.ser";

        List<Integer> list = new ArrayList<Integer>(){{
           add(0);
           add(1);
           add(2);
        }};

        /*try {
            FileOutputStream fos = new FileOutputStream(new File(path));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        List<Integer> test = (List<Integer>) ois.readObject();
        System.out.println(test);
        ois.close();
    }
}

class TestClass implements Serializable{
    private int i;

}
