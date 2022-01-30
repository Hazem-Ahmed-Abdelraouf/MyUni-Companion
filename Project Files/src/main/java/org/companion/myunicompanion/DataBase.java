/**
 * Singleton class used as a database
 **/

package org.companion.myunicompanion;

import org.companion.myunicompanion.classes.Assignment;
import org.companion.myunicompanion.classes.Course;
import org.companion.myunicompanion.classes.Student;
import org.companion.myunicompanion.classes.lecturer;

import java.util.HashMap;

public class DataBase {
    public static HashMap<Integer, Student> students = new HashMap<>();
    public static HashMap<Integer, lecturer> lecturers = new HashMap<>();
    public static HashMap<String, Course> courses = new HashMap<>();
    public static HashMap<Integer, Assignment> assignments = new HashMap<>();
    // for data transformation between scenes
    public static HashMap<String, String> dataTransporter = new HashMap<>();
    private static int asgnSurrogateKey = 0;
    private static DataBase instance = new DataBase();

    private DataBase() {
    }

    public static DataBase getInstance() {
        return instance;
    }
    public static int getAsgnSurKey(){return asgnSurrogateKey;}
    public static void incrementAsgnSurKey(){asgnSurrogateKey++;}
    public static void resetAsgnSurKey(){asgnSurrogateKey = 0;}
}