package org.companion.myunicompanion;

import lombok.Data;

import java.lang.String;
import java.time.LocalDate;
import java.util.*;
import java.time.Period;
@Data
public class lecturer extends Member{

    private ArrayList<String> courses_taught;
    private LocalDate date_hired;
    lecturer(){
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
