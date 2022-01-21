import lombok.Data;

@Data
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

    public static void main(String[] args) {

    }
}