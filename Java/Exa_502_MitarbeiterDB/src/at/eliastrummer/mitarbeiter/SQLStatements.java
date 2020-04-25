package at.eliastrummer.mitarbeiter;

public class SQLStatements {

    public static final String CREATE_TABLE = "CREATE TABLE mitarbeiter (\n"
            + "	pers_nr INTEGER PRIMARY KEY NOT NULL,\n"
            + "	name VARCHAR(40) NOT NULL,\n"
            + "	vorname VARCHAR(40) NOT NULL,\n"
            + "	geb_datum DATE,\n"
            + "	gehalt NUMERIC(7,2),\n"
            + "	abt_nr INTEGER NOT NULL,\n"
            + "	geschlecht CHAR(1) NOT NULL\n"
            + ");";

    public static final String DROP_DATABASE = "DROP DATABASE IF EXISTS mitarbeiterdb;";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS mitarbeiter;";
    public static final String INSERT_EMPLOYEE = "INSERT INTO mitarbeiter (PERS_NR, NAME, VORNAME, GEB_DATUM, GEHALT, ABT_NR, GESCHLECHT) VALUES\n (?, ?, ?, ?, ?, ?, ?);";
    public static final String AVERAGE_SALARY = "SELECT AVG(gehalt)\n"
            + "FROM mitarbeiter\n"
            + "WHERE geschlecht = ?;";
    public static final String EMPLYOEES_FOR_DEPARTMEN = "SELECT *\n"
            + "FROM mitarbeiter\n"
            + "WHERE abt_nr = ?\n"
            + "ORDER BY name, vorname;";
    public static final String DELETE_EMPLOYEE = "DELETE FROM mitarbeiter\n"
            + "WHERE pers_nr = ?;";
    public static final String NEXT_ID = "SELECT MAX(pers_nr)\n"
            + "FROM mitarbeiter;";
}
