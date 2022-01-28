// Singleton class used a database
package Controllers;
import org.companion.myunicompanion.Assignment;
import org.companion.myunicompanion.Course;
import org.companion.myunicompanion.Student;
import org.companion.myunicompanion.lecturer;

import java.util.HashMap;
public class DataBase {
    public static HashMap<Integer, Student> students = new HashMap<>();
    public static HashMap<Integer, lecturer> lecturers = new HashMap<>();
    public static HashMap<String, Course> courses = new HashMap<>();
    public static HashMap<Integer, Assignment> assignments = new HashMap<>();
    private static DataBase instance = new DataBase();
    //public JavaSecurityAccess.ProtectionDomainCache temStu;

    private DataBase(){}
    public static DataBase getInstance(){
        return instance;
    }
}