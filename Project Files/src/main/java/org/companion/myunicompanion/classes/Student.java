package org.companion.myunicompanion.classes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;


public class Student extends Member {
    private LocalDate date_enrolled;
    private HashMap<Integer, Assignment_Metadata> asgns_metadata;
    private HashMap<String, Course_Metadata> courses_metadata;


    public Student() {
        super();
        date_enrolled = LocalDate.now();
        courses_metadata = new HashMap<>();
        asgns_metadata = new HashMap<>();
    }

    public void set_date_enrolled(int year, int month_num, int day_of_month) {
        date_enrolled = LocalDate.of(year, month_num, day_of_month);
    }
    public void set_date_enrolled(LocalDate date_enrolled){this.date_enrolled=date_enrolled;}
    public int get_student_year() {
        return Period.between(date_enrolled, LocalDate.now()).getYears();
    }


    public void add_course_metadata(String course_id, Course_Metadata course) {
        courses_metadata.put(course_id, course);
    }

    public Course_Metadata get_course_metadata(String course_id) {
        //verify id exist
        if (courses_metadata.containsKey(course_id))
            return courses_metadata.get(course_id);
        else
            return null;
    }

    public boolean set_course_grade(String course_id, String grade) {
        //verify if id exist
        if (courses_metadata.containsKey(course_id)) {
            courses_metadata.get(course_id).setCourse_grade(grade);
            return true;
        } else
            return false;
    }

    public String get_course_grade(String course_id) {
        if (courses_metadata.containsKey(course_id))
            return courses_metadata.get(course_id).getCourse_grade();
        else
            return null;
    }

    public void add_asgn_metadata(int asgn_id, Assignment_Metadata asgn) {
        asgns_metadata.put(asgn_id, asgn);
    }

    public Assignment_Metadata get_asgn_metadata(int asgn_id) {
        //verify id exist
        if (asgns_metadata.containsKey(asgn_id))
            return asgns_metadata.get(asgn_id);
        else
            return null;
    }

    public boolean set_asgn_grade(int asgn_id, String grade) {
        //verify if id exist
        if (asgns_metadata.containsKey(asgn_id)) {
            asgns_metadata.get(asgn_id).setAssignment_grade(grade);
            return true;
        } else
            return false;
    }

    public String get_asgn_grade(int asgn_id) {
        if (asgns_metadata.containsKey(asgn_id))
            return asgns_metadata.get(asgn_id).getAssignment_grade();
        else
            return null;
    }

    public void remove_asgn(int asgn_id) {
        if (asgns_metadata.containsKey(asgn_id))
            asgns_metadata.remove(asgn_id);

        return;
    }

    public void unregister_course(String course_id) {

        if (courses_metadata.containsKey(course_id)) {
            ArrayList<Integer> asgns_keys = new ArrayList<>();
            courses_metadata.remove(course_id);
            //getting the asssinment keys to delete them
            asgns_metadata.forEach((key, asgn) -> {
                if (asgn.getCourse_id() == course_id)
                    asgns_keys.add(asgn.getAssignment_id());
            });
            //deleting the assignments metadata
            for (int i = 0; i < asgns_keys.size(); i++)
                asgns_metadata.remove(asgns_keys.get(i));

        }

    }

    public HashMap<Integer, Assignment_Metadata> get_all_assignments() {
        return asgns_metadata;
    }

    public HashMap<String, Course_Metadata> get_all_courses() {
        return courses_metadata;
    }


}