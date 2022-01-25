package org.companion.myunicompanion;

public class CourseMetadata {
    private int course_ID;
    private int course_grade;

    public CourseMetadata(){
        course_ID = 0;
        course_grade=0;
    }
    public void setCourse_ID(int course_ID) {
        this.course_ID = course_ID;
    }

    public int getCourse_ID() {
        return course_ID;
    }

    public void setCourse_grade(int course_grade) {
        this.course_grade = course_grade;
    }


    public int getCourse_grade() {
        return this.course_grade;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CourseMetadata)) return false;
        final CourseMetadata other = (CourseMetadata) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getCourse_ID() != other.getCourse_ID()) return false;
        if (this.getCourse_grade() != other.getCourse_grade()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CourseMetadata;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getCourse_ID();
        result = result * PRIME + this.getCourse_grade();
        return result;
    }

    public String toString() {
        return "CourseMetadata(course_ID=" + this.getCourse_ID() + ", course_grade=" + this.getCourse_grade() + ")";
    }
}