package self.io;

import beans.Gender;
import self.beans.Alien;
import self.beans.Entity;
import self.beans.Human;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IOHandler {

    private static final Path pathToFile = Paths.get(System.getProperty("user.dir"), "src", "self", "res", "entities.csv");
    private static final Path pathToSerFile = Paths.get(System.getProperty("user.dir"), "src", "self", "res", "ints.ser");

    public static List<Entity> getEntities() throws IOException {
        List<Entity> entities;

        Function<String, Entity> mapper = (s) -> {
                String[] parts = s.split(";");
                return parts[0].equalsIgnoreCase("human") ?
                    new Human(parts[1], LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd.MM.yyyy")), Gender.valueOf(parts[3].toUpperCase())) :
                    new Alien(parts[1], LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("dd.MM.yyyy")), Integer.parseInt(parts[4]));
                };

        entities = Files.lines(pathToFile)
                .skip(1)
                .map(mapper)
                .collect(Collectors.toList());

        return entities;
    }

    public static void saveSeLeInts(ArrayList<Integer> param) throws IOException {
        FileOutputStream fos = new FileOutputStream(pathToSerFile.toFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(param);
        oos.close();
    }

    public static List<Integer> getSeLeInts() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(pathToSerFile.toFile());
        ObjectInputStream ois = new ObjectInputStream(fis);

        return (ArrayList<Integer>) ois.readObject();
    }
}
