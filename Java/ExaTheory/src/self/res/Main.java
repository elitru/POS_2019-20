package self.res;

import beans.Gender;
import self.beans.Alien;
import self.beans.Entity;
import self.beans.Human;
import self.io.IOHandler;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Entity> entities = null;

        try {
            entities = IOHandler.getEntities();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //print(entities);
        //test(entities);
        //test2();
        test3();
    }

    public static void print(List<Entity> entities){
        entities.forEach(e -> {
            if(e instanceof Human){
                System.out.println(((Human)e).toString());
            }else{
                System.out.println(((Alien)e).toString());
            }
        });
        System.out.println("===========================================");
    }

    public static void test(List<Entity> entities){

        Predicate<Entity> filter = e -> {
            if(e instanceof Alien){
                return ((Alien)e).getAmountChildren() > 5;
            }

            return ((Human)e).getGender() == Gender.FEMALE;
        };

        entities.stream()
            .distinct()
            .sorted(Comparator.comparing(Entity::getName))
            .filter(filter)
            .forEach(System.out::println);
    }

    public static void test2(){
        int[] nums = new int[] {
            1,
            5,
            7,
            2,
            8,
            3,
            2
        };

        int sum = Arrays.stream(nums)
                .sum();

        System.out.println(sum);

        double sum2 = Arrays.stream(nums)
                .asDoubleStream()
                .average()
                .orElse(-1);

        System.out.println(sum2);
    }

    public static void test3(){
        List<Integer> list = new ArrayList<Integer>(){{
            add(0);
            add(2);
            add(8);
            add(4);
            add(6);
            add(5);
        }};

        try {
            IOHandler.saveSeLeInts((ArrayList<Integer>) list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<Integer> list2 = IOHandler.getSeLeInts();
            list2.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
