package org.companion.myunicompanion;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class lecturer extends Member{

    private ArrayList<String> courses_taught;
    private LocalDate date_hired;
    public lecturer(){
        //super();
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
        return Period.between(date_hired,LocalDate.now()).getMonths();
    }

    public void set_hired_date(int year, int month,int dayofmonth){
        date_hired=LocalDate.of(year, month, dayofmonth);
    }
}
