package classes;

import java.util.Date;

public class CourseAttendance {
    private String studentId;
    private String courseId;
    private String status;
    private Date attendanceDate = new Date();
    private final int id;
    private static int attendanceCount = 0;

    public CourseAttendance(String studentId, String courseId, String status){
        CourseAttendance.attendanceCount++;
        this.id = CourseAttendance.attendanceCount;
        this.studentId = studentId;
        this.courseId = courseId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\n\tstudent id: " + studentId +
                "\n\tcourse id: " + courseId +
                "\n\t"+ "\n}";
    }

    // =====================================
    // ============== SETTERS ==============
    // =====================================
    public void setStatus(String newStatus){
        this.status = newStatus;
    }

    public void setStudentId(String newStudentId){
        this.studentId = newStudentId;
    }

    public void setCourseId(String newCourseId){
        this.courseId = newCourseId;
    }

    public void setAttendanceDate(Date newDate){
        this.attendanceDate = newDate;
    }


    // =====================================
    // ============== GETTERS ==============
    // =====================================

    public Date getAttendanceDate(){
        return this.attendanceDate;
    }

    public String getStudentId(){
        return this.studentId;
    }

    public String getCourseId(){
        return this.courseId;
    }

    public String getStatus(){
        return this.status;
    }

    public int getId(){
        return this.id;
    }

}
