// Singleton class used a database
package Controllers;
import java.util.HashMap;
import org.companion.myunicompanion.*;
public class DataBase {
    public static HashMap<Integer, Student> students = new HashMap<>();
    public static HashMap<Integer, lecturer> lecturers = new HashMap<>();
    public static HashMap<String, Course> courses = new HashMap<>();
    public static HashMap<String, Assignment> assignments = new HashMap<>();
    private static DataBase instance = new DataBase();
    private DataBase(){}
    public static DataBase getInstance(){
        return instance;
    }
}