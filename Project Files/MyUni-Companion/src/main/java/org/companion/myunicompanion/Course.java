public class Course {

    int course_ID;
    private int credits_num;
    private String course_name;
    private String course_type;
    private String school_name;
    private int year_applicable;
    private int lecturer_id;
public Course()
{
    course_ID = 0;
    credits_num = 0;
    course_name = " ";
    course_type = " ";
    school_name = " ";
    year_applicable = 0;
    lecturer_id = 0;
}

   public Course(int course_ID,int credits_num,String course_name,String course_type,String school_name,int year_applicable,int lecturer_id)
   {
       this.course_ID=course_ID;
       this.credits_num=credits_num;
       this.course_name=course_name;
       this.course_type=course_type;
       this.school_name=school_name;
       this.year_applicable=year_applicable;
       this.lecturer_id=lecturer_id;
   }

    public void setCourse_ID(int course_ID) {
        this.course_ID = course_ID;
    }

    public int getCourse_ID() {
        return course_ID;
    }

    public void setCredits_num(int credits_num) {
        this.credits_num = credits_num;
    }

    public int getCredits_num() {
        return credits_num;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_type(String course_type) {
        this.course_type = course_type;
    }

    public String getCourse_type() {
        return course_type;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setYear_applicable(int year_applicable) {
        this.year_applicable = year_applicable;
    }

    public int getYear_applicable() {
        return year_applicable;
    }

    public void setLecturer_id(int lecturer_id) {
        this.lecturer_id = lecturer_id;
    }

    public int getLecturer_id() {
        return lecturer_id;
    }

    public static void main(String[] args) {

    }


    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Course)) return false;
        final Course other = (Course) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getCourse_ID() != other.getCourse_ID()) return false;
        if (this.getCredits_num() != other.getCredits_num()) return false;
        final Object this$course_name = this.getCourse_name();
        final Object other$course_name = other.getCourse_name();
        if (this$course_name == null ? other$course_name != null : !this$course_name.equals(other$course_name))
            return false;
        final Object this$course_type = this.getCourse_type();
        final Object other$course_type = other.getCourse_type();
        if (this$course_type == null ? other$course_type != null : !this$course_type.equals(other$course_type))
            return false;
        final Object this$school_name = this.getSchool_name();
        final Object other$school_name = other.getSchool_name();
        if (this$school_name == null ? other$school_name != null : !this$school_name.equals(other$school_name))
            return false;
        if (this.getYear_applicable() != other.getYear_applicable()) return false;
        if (this.getLecturer_id() != other.getLecturer_id()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Course;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getCourse_ID();
        result = result * PRIME + this.getCredits_num();
        final Object $course_name = this.getCourse_name();
        result = result * PRIME + ($course_name == null ? 43 : $course_name.hashCode());
        final Object $course_type = this.getCourse_type();
        result = result * PRIME + ($course_type == null ? 43 : $course_type.hashCode());
        final Object $school_name = this.getSchool_name();
        result = result * PRIME + ($school_name == null ? 43 : $school_name.hashCode());
        result = result * PRIME + this.getYear_applicable();
        result = result * PRIME + this.getLecturer_id();
        return result;
    }

    public String toString() {
        return "Course(course_ID=" + this.getCourse_ID() + ", credits_num=" + this.getCredits_num() + ", course_name=" + this.getCourse_name() + ", course_type=" + this.getCourse_type() + ", school_name=" + this.getSchool_name() + ", year_applicable=" + this.getYear_applicable() + ", lecturer_id=" + this.getLecturer_id() + ")";
    }
}
