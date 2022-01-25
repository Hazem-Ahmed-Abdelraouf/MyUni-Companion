package org.companion.myunicompanion;

public class Course_Metadata {
    private int course_ID;
    private double course_grade;

    public Course_Metadata(){
        course_ID = 0;
        course_grade=0.0;
    }
    public void setCourse_ID(int course_ID) {
        this.course_ID = course_ID;
    }

    public int getCourse_ID() {
        return course_ID;
    }

    public void setCourse_grade(double course_grade) {
        this.course_grade = course_grade;
    }


    public double getCourse_grade() {
        return this.course_grade;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Course_Metadata)) return false;
        final Course_Metadata other = (Course_Metadata) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getCourse_ID() != other.getCourse_ID()) return false;
        if (this.getCourse_grade() != other.getCourse_grade()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Course_Metadata;
    }

    public int hashCode() {
        final int PRIME = 59;
        double result = 1;
        result = result * PRIME + this.getCourse_ID();
        result = result * PRIME + this.getCourse_grade();
        return (int)result;
    }

    public String toString() {
        return "CourseMetadata(course_ID=" + this.getCourse_ID() + ", course_grade=" + this.getCourse_grade() + ")";
    }
}