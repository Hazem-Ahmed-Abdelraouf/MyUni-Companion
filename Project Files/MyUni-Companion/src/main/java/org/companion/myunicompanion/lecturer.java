import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.lang.String;
import java.time.LocalDate;
import java.util.*;
import java.time.Period;
@Data
public class lecturer {

    private ArrayList<String> courses_taught;
    private LocalDate date_hired;
    lecturer(){
        courses_taught  = new ArrayList<>();
        date_hired = LocalDate.now();
    }
    public void add_course_taught(String course_id)
    {

        courses_taught.add(course_id);

    }
    public ArrayList<String> get_course_taught(){
        return courses_taught;
    }

    public void deregister_course(String course_id){
        courses_taught.remove(course_id);

    }



    public int get_lecturing_period(){
        Period.between(date_hired,LocalDate.now()).getMonths();
    }

    public void set_hired_date(int year, int month,int dayofmonth){
        date_hired=LocalDate.of(year, month, dayofmonth);
    }

    // default constructor

/*   later will set in main

    public void AddCourse(){

    }
*/
/*
    public void RemoveCourse(){

    }
*/
    public void AddAssignment(){

    System.out.println("------------Add Assignment----------");


    }
/* later will set in main
    public void RemoveAssignment(){

    }
*/
    public void GradeAssignment(){
        // can grading assignment by assignment id.
        System.out.println("Please input assignment ID to grading: ");


    }

    public void SearchStudent(){
        //use student ID to search.
        System.out.println("Please input student ID to search: ");

    }

    public void setLecturer_ID(int lecturer_ID) {
        this.lecturer_ID = lecturer_ID;
    }

    public int getLecturer_ID() {
        return lecturer_ID;
    }

    public void setLecturer_Fname(String lecturer_Fname) {
        this.lecturer_Fname = lecturer_Fname;
    }

    public String getLecturer_Fname() {
        return lecturer_Fname;
    }

    public void setLecturer_Lname(String lecturer_Lname) {
        this.lecturer_Lname = lecturer_Lname;
    }

    public String getLecturer_Lname() {
        return lecturer_Lname;
    }

    public void setLecturer_birthDate(LocalDate lecturer_birthDate) {
        this.lecturer_birthDate = lecturer_birthDate;
    }

    public LocalDate getLecturer_birthDate() {
        return lecturer_birthDate;
    }

    public void setLecturer_country(String lecturer_country) {
        this.lecturer_country = lecturer_country;
    }

    public String getLecturer_country() {
        return lecturer_country;
    }

    public void setLecturer_email(String lecturer_email) {
        this.lecturer_email = lecturer_email;
    }

    public String getLecturer_email() {
        return lecturer_email;
    }

    public static void main(String[] args) {
        Scanner sr = new Scanner(System.in);
        System.out.println("Please input your account ID" );
        String account = sr.nextLine();
        System.out.println("Please input your password" );
        String password = sr.nextLine();

        if(account==lec_account && password == lec_password )
        {
            System.out.println("Login successful!");
            lecturer ler = new lecturer();



        }else
        {
            System.out.println("Incorrect account or password!");
        }


    }


}
