package org.companion.myunicompanion;

public class Course_Metadata {
    private String course_ID;
    private String course_grade;

    public Course_Metadata(){
        course_ID = "";
        course_grade="";
    }
    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setCourse_grade(String course_grade) {
        this.course_grade = course_grade;
    }


    public String getCourse_grade() {
        return this.course_grade;
    }



    public String toString() {
        return "CourseMetadata(course_ID=" + this.getCourse_ID() + ", course_grade=" + this.getCourse_grade() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Course_Metadata)) return false;
        final Course_Metadata other = (Course_Metadata) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$course_ID = this.getCourse_ID();
        final Object other$course_ID = other.getCourse_ID();
        if (this$course_ID == null ? other$course_ID != null : !this$course_ID.equals(other$course_ID)) return false;
        final Object this$course_grade = this.getCourse_grade();
        final Object other$course_grade = other.getCourse_grade();
        if (this$course_grade == null ? other$course_grade != null : !this$course_grade.equals(other$course_grade))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Course_Metadata;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $course_ID = this.getCourse_ID();
        result = result * PRIME + ($course_ID == null ? 43 : $course_ID.hashCode());
        final Object $course_grade = this.getCourse_grade();
        result = result * PRIME + ($course_grade == null ? 43 : $course_grade.hashCode());
        return result;
    }
}