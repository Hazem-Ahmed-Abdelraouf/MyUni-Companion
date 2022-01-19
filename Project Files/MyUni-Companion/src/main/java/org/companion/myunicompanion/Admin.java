import java.time.LocalDate;
import java.time.Period;

public class Admin extends Member {
    LocalDate date_hired;

    public int get_administration_period(){
        Period.between(date_hired,LocalDate.now()).getMonths();
    }

    public void set_hired_date(int year, int month,int dayofmonth){
        date_hired=LocalDate.of(year, month, dayofmonth);
    }
}







