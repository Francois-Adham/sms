package classes;

import java.util.Date;

public class Assignment {
    private final int id;
    private String description;
    private String courseId;
    private Date dueDate;
    private static int assignmentCount = 0;

    public Assignment(String description,String courseId, Date dueDate){
        Assignment.assignmentCount++;
        this.id = Assignment.assignmentCount;
        this.description = description;
        this.courseId = courseId;
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return String.format("{ %-2d %-5d %10s %s }", id, courseId, dueDate, description);
        /*
        return "{\n\tassignment id: " + id +
                "\n\tcourse id: " + courseId +
                "\n\tdue date: " + dueDate +
                "\n\tdescription: " + description + "\n}";

         */
    }

    // =====================================
    // ============== SETTERS ==============
    // =====================================

    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    public void setCourseId(String newCourseId){
        this.courseId = newCourseId;
    }

    public void setDueDate(Date newDueDate){
        this.dueDate = newDueDate;
    }

    // =====================================
    // ============== GETTERS ==============
    // =====================================

    public int getId(){
        return this.id;
    }
    public String getDescription(){
        return this.description;
    }
    public String getCourseId(){
        return this.courseId;
    }
    public Date getDueDate(){
        return this.dueDate;
    }
}
