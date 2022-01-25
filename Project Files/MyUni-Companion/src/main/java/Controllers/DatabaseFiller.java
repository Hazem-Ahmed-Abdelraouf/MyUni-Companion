package Controllers;
import org.companion.myunicompanion.Student;

public class DatabaseFiller {
    public static DataBase db = DataBase.getInstance();
    static {
        Student s = new Student();
        s.setFname("hazem");
        db.students.put(22,s);
    }
    /*
    public static void fill_students(){}
    public static void fill_lecturers(){}
    public static void fill_(){}
    public static void fill_(){}
    public static void fill_(){}
*/
}

