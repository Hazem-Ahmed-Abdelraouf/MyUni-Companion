package org.companion.myunicompanion.classes;


public class Assignment_Metadata {
    private int assignment_id;
    private String course_id;
    private String answer;
    String assignment_grade;

    public Assignment_Metadata() {
        assignment_id = 0;
        course_id = "";
        answer = null;
        assignment_grade = "";
    }

    public Assignment_Metadata(int asgn_id, String course_id, String answer, String asgn_grade) {
        this.assignment_id = asgn_id;
        this.course_id = course_id;
        this.answer = new String(answer);
        this.assignment_grade = asgn_grade;
    }

    public int getAssignment_id() {
        return this.assignment_id;
    }

    public String getCourse_id() {
        return this.course_id;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getAssignment_grade() {
        return this.assignment_grade;
    }

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAssignment_grade(String assignment_grade) {
        this.assignment_grade = assignment_grade;
    }


    public String toString() {
        return "Assignment_Metadata(assignment_id=" + this.getAssignment_id() + ", course_id=" + this.getCourse_id() + ", answer=" + this.getAnswer() + ", assignment_grade=" + this.getAssignment_grade() + ")";
    }
}