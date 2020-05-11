package at.eliastrummer.database;

public class SQLStatements {

    public static final String GET_ALL_STUDENTS = "SELECT * "
            + "FROM student s "
            + "INNER JOIN grade g ON g.classid = s.classid;";
    public static final String GET_ALL_STUDENTS_FOR_CLASS = "SELECT * "
            + "FROM student s "
            + "INNER JOIN grade g ON g.classid = s.classid "
            + "WHERE g.classname = '%s';";

    public static final String GET_ALL_GRADES = "SELECT * FROM grade;";
    public static final String GET_CLASS = "SELECT * FROM grade WHERE classname = '%s';";
    public static final String INSERT_CLASS = "INSERT INTO grade (classname) VALUES (?);";
    public static final String INSERT_STUDENT = "INSERT INTO public.student("
            + "catno, firstname, lastname, gender, dateofbirth, classid) "
            + "VALUES (?, ?, ?, ?, ?, ?);";
    public static final String DELETE_STUDENTS = "DELETE FROM student;";
    public static final String DELETE_CLASSES = "DELETE FROM grade;";
    public static final String GET_NEXT_CATNO = "select catno \n"
            + "from student s\n"
            + "inner join grade g on s.classid = g.classid\n"
            + "where g.classname = '%s'\n"
            + "order by catno desc;";
    public static final String UPDATE_CATNO = "UPDATE student SET catno=%d WHERE studentid=%d;";
    public static final String GET_ALL_EXPORT = "SELECT studentid, catno, classname, firstname, lastname, gender, dateofbirth\n"
            + "FROM student s\n"
            + "INNER JOIN grade g ON s.classid = g.classid;";
}
