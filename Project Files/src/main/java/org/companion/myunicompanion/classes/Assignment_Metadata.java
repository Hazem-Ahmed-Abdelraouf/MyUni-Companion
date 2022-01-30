package org.companion.myunicompanion.classes;


public class Assignment_Metadata {
    private int assignment_id;
    private String course_id;
    private String answer;


    public Assignment_Metadata() {
        assignment_id = 0;
        course_id = "";
        answer = null;

    }

    public Assignment_Metadata(int asgn_id, String course_id, String answer) {
        this.assignment_id = asgn_id;
        this.course_id = course_id;
        this.answer = new String(answer);
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

    public void setAssignment_id(int assignment_id) {
        this.assignment_id = assignment_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }




    public String toString() {
        return "Assignment_Metadata(assignment_id=" + this.getAssignment_id() + ", course_id=" + this.getCourse_id() + ", answer=" + this.getAnswer() + ")";
    }
}