package org.companion.myunicompanion.classes;


public class Assignment {

    private int assignment_ID;
    private String assignment_name;
    private String course_ID;
    private String assignment_type;
    private String assignment_question;


    public void setAssignment_ID(int assignment_ID) {
        this.assignment_ID = assignment_ID;
    }

    public int getAssignment_ID() {
        return assignment_ID;
    }

    public void setAssignment_name(String assignment_name){this.assignment_name = assignment_name;}

    public String getAssignment_name(){return this.assignment_name;}

    public void setCourse_ID(String course_ID) {
        this.course_ID = course_ID;
    }

    public String getCourse_ID() {
        return course_ID;
    }

    public void setAssignment_type(String assignment_type) {
        this.assignment_type = assignment_type;
    }

    public String getAssignment_type() {
        return assignment_type;
    }


    public void set_assignment_question(String questions) {
        assignment_question = new String(questions);
    }

    public String getAssignment_question() {
        return assignment_question;
    }

}