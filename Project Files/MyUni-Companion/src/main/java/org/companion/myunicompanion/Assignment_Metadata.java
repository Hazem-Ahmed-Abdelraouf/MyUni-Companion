package org.companion.myunicompanion;

import java.io.File;

public class Assignment_Metadata {
    private int assignment_id;
    private String course_id;
    private File submission_file;
    double assignment_grade;

    public Assignment_Metadata(){
        assignment_id = 0;
        course_id = "";
        submission_file = null;
        assignment_grade = -1;
    }
    public Assignment_Metadata(int asgn_id, String course_id, String file_path, double asgn_grade){
        assignment_id = asgn_id;
        this.course_id = course_id;
        submission_file = new File(file_path);
        assignment_grade = asgn_grade;
    }

    public int getAssignment_id() {
        return this.assignment_id;
    }

    public String getCourse_id() {
        return this.course_id;
    }

    public File getSubmission_file() {
        return this.submission_file;
    }

    public double getAssignment_grade() {
        return this.assignment_grade;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setSubmission_file(File submission_file) {
        this.submission_file = submission_file;
    }

    public void setAssignment_grade(double assignment_grade) {
        this.assignment_grade = assignment_grade;
    }



    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Assignment_Metadata)) return false;
        final Assignment_Metadata other = (Assignment_Metadata) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getAssignment_id() != other.getAssignment_id()) return false;
        final Object this$course_id = this.getCourse_id();
        final Object other$course_id = other.getCourse_id();
        if (this$course_id == null ? other$course_id != null : !this$course_id.equals(other$course_id)) return false;
        final Object this$submission_file = this.getSubmission_file();
        final Object other$submission_file = other.getSubmission_file();
        if (this$submission_file == null ? other$submission_file != null : !this$submission_file.equals(other$submission_file))
            return false;
        if (Double.compare(this.getAssignment_grade(), other.getAssignment_grade()) != 0) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Assignment_Metadata;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getAssignment_id();
        final Object $course_id = this.getCourse_id();
        result = result * PRIME + ($course_id == null ? 43 : $course_id.hashCode());
        final Object $submission_file = this.getSubmission_file();
        result = result * PRIME + ($submission_file == null ? 43 : $submission_file.hashCode());
        final long $assignment_grade = Double.doubleToLongBits(this.getAssignment_grade());
        result = result * PRIME + (int) ($assignment_grade >>> 32 ^ $assignment_grade);
        return result;
    }

    public String toString() {
        return "Assignment_Metadata(assignment_id=" + this.getAssignment_id() + ", course_id=" + this.getCourse_id() + ", submission_file=" + this.getSubmission_file() + ", assignment_grade=" + this.getAssignment_grade() + ")";
    }
}