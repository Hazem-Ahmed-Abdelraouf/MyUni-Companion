package Controllers;

import org.companion.myunicompanion.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseFiller {
    public static DataBase db = DataBase.getInstance();
    static {
        fill_students();
        fill_lecturers();
        fill_assignments();
        fill_courses();
        fill_courses_metadata();
        fill_assignment_metadata();
    }
    public static void fill_students() {
        String path = "data-source/STUDENT TABLE.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while((line = br.readLine())!=null)
            {

                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String firstName = values[1];
                String lastName = values[2];
                int day_born = Integer.parseInt(values[3]);
                int month_born = Integer.parseInt(values[4]);
                int year_born = Integer.parseInt(values[5]);
                String country = values[6];
                String school_name = values[7];
                String email = values[8];
                int day_enrolled = Integer.parseInt(values[9]);
                int month_enrolled = Integer.parseInt(values[10]);
                int year_enrolled = Integer.parseInt(values[11]);
                String password = values[12];


                Student stu = new Student();
                stu.setUni_ID(id);
                stu.setFname(firstName);
                stu.setLname(lastName);
                stu.setBirthdate(year_born,month_born,day_born);
                stu.setCountry(country);
                stu.setSchool_name(school_name);
                stu.setEmail(email);
                stu.set_date_enrolled(year_enrolled,month_enrolled,day_enrolled);
                stu.setPassword(password);

                db.students.put(id,stu);
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void fill_lecturers(){
        String path = "data-source/LECTURER TABLE.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine()) !=null)
            {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String firstName = values[1];
                String lastName = values[2];
                int day_born = Integer.parseInt(values[3]);
                int month_born = Integer.parseInt(values[4]);
                int year_born = Integer.parseInt(values[5]);
                String country = values[6];
                String school_name = values[7];
                String email = values[8];
                String password = values[9];
                int day_hired = Integer.parseInt(values[10]);
                int month_hired = Integer.parseInt(values[11]);
                int year_hired = Integer.parseInt(values[12]);
                lecturer lec = new lecturer();
                lec.setUni_ID(id);
                lec.setFname(firstName);
                lec.setLname(lastName);
                lec.setBirthdate(year_born,month_born,day_born);
                lec.setCountry(country);
                lec.setSchool_name(school_name);
                lec.setEmail(email);
                lec.setPassword(password);
                lec.set_hired_date(year_hired,month_hired,day_hired);

                db.lecturers.put(id,lec);
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void fill_assignments(){

        String path = "data-source/ASSIGNMENT TABLE.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while((line = br.readLine())!=null)
            {
                String[] values = line.split(",");
                int asgn_id = Integer.parseInt(values[0]);
                String course_id = values[1];
                String asgn_type = values[2];
                String asgn_questions = values[3];

                Assignment assi = new Assignment();
                assi.setAssignment_ID(asgn_id);
                assi.setCourse_ID(course_id);
                assi.setAssignment_type(asgn_type);
                assi.set_assignment_question(asgn_questions);
                db.assignments.put(asgn_id,assi);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void fill_courses() {
        String path = "data-source/COURSE TABLE.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while((line = br.readLine())!=null)
            {
                String[] values = line.split(",");

                String course_id = values[0];
                String course_name = values[1];
                String course_type = values[2];
                int credits_num = Integer.parseInt(values[3]);
                String school_name = values[4];
                int year_applicable = Integer.parseInt(values[5]);
                int lecturer_id = Integer.parseInt(values[6]);

                Course course = new Course();
                course.setCourse_ID(course_id);
                course.setCourse_name(course_name);
                course.setCourse_type(course_type);
                course.setCredits_num(credits_num);
                course.setSchool_name(school_name);
                course.setYear_applicable(year_applicable);
                course.setLecturer_id(lecturer_id);

                db.courses.put(course_id,course);
                if(lecturer_id != -99 && db.lecturers.containsKey(lecturer_id))
                {
                    db.lecturers.get(lecturer_id).get_course_taught().add(course_id);
                }
            }
            br.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void fill_courses_metadata(){
        String path = "data-source/COURSE_METADATA TABLE.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while((line = br.readLine())!=null)
            {
                String[] values = line.split(",");
                int stu_id = Integer.parseInt(values[0]);
                String course_id = values[1];
                String course_grade = values[2];

                Course_Metadata course_metadata = new Course_Metadata();
                course_metadata.setCourse_ID(course_id);
                course_metadata.setCourse_grade(course_grade);
                //making sure that this course metadata belongs to an actual student in the system
                if (db.students.containsKey(stu_id) && db.courses.containsKey(course_id)) {
                    db.students.get(stu_id).get_all_courses().put(course_id, course_metadata);
                }

            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void fill_assignment_metadata(){
        String path = "data-source/ASSIGNMENT_METADATA TABLE.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            br.readLine();
            while ((line = br.readLine())!=null)
            {
                String[] values = line.split(",");
                int stu_id = Integer.parseInt(values[0]);
                int asgn_id = Integer.parseInt(values[1]);
                String course_id = values[2];
                String asgn_answer = values[3];
                String asgn_grade = values[4];

                Assignment_Metadata assignment_metadata = new Assignment_Metadata(asgn_id,course_id,asgn_answer,asgn_grade);
                //making sure that this assignment metadata belongs to an actual student in the system
                if (db.students.containsKey(stu_id) && db.assignments.containsKey(asgn_id)) {
                    db.students.get(stu_id).get_all_assignments().put(asgn_id, assignment_metadata);
                }

            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

