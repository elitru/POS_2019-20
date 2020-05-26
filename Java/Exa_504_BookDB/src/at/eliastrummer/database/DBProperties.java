package at.eliastrummer.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBProperties {

    private static final Properties PROPS = new Properties();

    static {
        FileInputStream fis = null;
        try {
            Path propertyFile = Paths.get(System.getProperty("user.dir"), "src", "res", "database.properties");
            fis = new FileInputStream(propertyFile.toFile());
            PROPS.load(fis);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getPropertyValue(String key) {
        return PROPS.getProperty(key);
    }

}
