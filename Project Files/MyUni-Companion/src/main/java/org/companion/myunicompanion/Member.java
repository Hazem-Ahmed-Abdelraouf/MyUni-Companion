package org.companion.myunicompanion;

import java.time.LocalDate;
import java.time.Period;

class Member {
    private int uni_ID;
    private String Fname;
    private String Lname;
    private String country;
    private String school_name;
    private String email;
    private LocalDate birthdate;
    private String password;


    public Member() {
        uni_ID = 0;
        Fname = "";
        Lname = "";
        country = "";
        school_name = "";
        email = "";
        birthdate = LocalDate.now();
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public int getUni_ID() {
        return this.uni_ID;
    }

    public String getFname() {
        return this.Fname;
    }

    public String getLname() {
        return this.Lname;
    }

    public String getCountry() {
        return this.country;
    }

    public String getSchool_name() {
        return this.school_name;
    }

    public String getEmail() {
        return this.email;
    }

    public int get_student_year(){
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    public void setBirthdate(int year,int month_num,int day_of_month) {
        birthdate = LocalDate.of(year, month_num, day_of_month);
    }
    public int getBirthdate(){
        return Period.between(birthdate, LocalDate.now()).getYears();
    }
    public void setUni_ID(int uni_ID) {
        this.uni_ID = uni_ID;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Member(uni_ID=" + this.getUni_ID() + ", Fname=" + this.getFname() + ", Lname=" + this.getLname() + ", country=" + this.getCountry() + ", school_name=" + this.getSchool_name() + ", email=" + this.getEmail() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Member)) return false;
        final Member other = (Member) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getUni_ID() != other.getUni_ID()) return false;
        final Object this$Fname = this.getFname();
        final Object other$Fname = other.getFname();
        if (this$Fname == null ? other$Fname != null : !this$Fname.equals(other$Fname)) return false;
        final Object this$Lname = this.getLname();
        final Object other$Lname = other.getLname();
        if (this$Lname == null ? other$Lname != null : !this$Lname.equals(other$Lname)) return false;
        final Object this$country = this.getCountry();
        final Object other$country = other.getCountry();
        if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
        final Object this$school_name = this.getSchool_name();
        final Object other$school_name = other.getSchool_name();
        if (this$school_name == null ? other$school_name != null : !this$school_name.equals(other$school_name))
            return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$birthdate = this.getBirthdate();
        final Object other$birthdate = other.getBirthdate();
        if (this$birthdate == null ? other$birthdate != null : !this$birthdate.equals(other$birthdate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Member;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getUni_ID();
        final Object $Fname = this.getFname();
        result = result * PRIME + ($Fname == null ? 43 : $Fname.hashCode());
        final Object $Lname = this.getLname();
        result = result * PRIME + ($Lname == null ? 43 : $Lname.hashCode());
        final Object $country = this.getCountry();
        result = result * PRIME + ($country == null ? 43 : $country.hashCode());
        final Object $school_name = this.getSchool_name();
        result = result * PRIME + ($school_name == null ? 43 : $school_name.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $birthdate = this.getBirthdate();
        result = result * PRIME + ($birthdate == null ? 43 : $birthdate.hashCode());
        return result;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
