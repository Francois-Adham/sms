package classes;

import java.util.Date;

public class AssignmentSubmission {
    private String assignmentId;
    private String studentId;
    private String courseId;
    private String assignmentContentSubmitted;
    private final Date submissionDate = new Date();
    private float assignmentMarks;
    private final int id;
    private static int submissionCount = 0;

    public AssignmentSubmission(String assignmentId, String studentId, String courseId, String assignmentContentSubmitted){
        AssignmentSubmission.submissionCount++;
        this.id = AssignmentSubmission.submissionCount;
        this.assignmentId = assignmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.assignmentContentSubmitted = assignmentContentSubmitted;
        this.assignmentMarks = 0;
    }

    @Override
    public String toString() {
        return "{\tsubmission id: " + id +
                "\tassignment id: " + assignmentId +
                "\tstudent id: " + studentId +
                "\tcourse id: " + courseId +
                "\tcontent: " + assignmentContentSubmitted +
                "\tmarks: " + assignmentMarks + "}";
    }

    // =====================================
    // ============== SETTERS ==============
    // =====================================

    public void setAssignmentId(String newAssignmentId){
        this.assignmentId = newAssignmentId;
    }

    public void setStudentId(String newStudentId){
        this.studentId = newStudentId;
    }

    public void setCourseId(String newCourseId){
        this.courseId = newCourseId;
    }

    public void setAssignmentContentSubmitted(String newContent){
        this.assignmentContentSubmitted = newContent;
    }

    public void setAssignmentMarks(float newMark){
        this.assignmentMarks = newMark;
    }


    // =====================================
    // ============== GETTERS ==============
    // =====================================

    public int getId(){
        return this.id;
    }

    public Date getSubmissionDate(){
        return this.submissionDate;
    }

    public String getAssignmentId(){
        return this.assignmentId;
    }

    public String getStudentId(){
        return this.studentId;
    }

    public String getCourseId(){
        return this.courseId;
    }

    public String getAssignmentContentSubmitted(){
        return this.assignmentContentSubmitted;
    }

    public float getAssignmentMarks(){
        return this.assignmentMarks;
    }
}
