package users;
import classes.Database;
import classes.Course;
import classes.Assignment;
import classes.CourseAttendance;
import exceptions.NotFoundException;

import java.util.ArrayList;

public class Teacher extends Person {
    private static int teacherCount = 0;
    public Teacher(String name, String email, String mobileNumber){
        Teacher.teacherCount++;
        this.id = Teacher.teacherCount;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "{\n\tUsers.Teacher id: " + id +
                "\n\tname: " + name +
                "\n\temail: " + email +
                "\n\tmobile phone: " + mobileNumber + "\n}";
    }


    // =====================================
    // ============== METHODS ==============
    // =====================================
    public void viewStudentsAssignedClasses(){
        for (Student student : Database.getInstance().getStudents().values()) {
            System.out.println("=====" + student.getId() + ": " + student.getName() + "=====");
            for (int courseId : student.getCourses()) {
                System.out.println(Database.getInstance().getCourses().get(courseId));
            }
        }
    }

    public void getStudentsInCourse(int courseId) throws NotFoundException{
        Course course = Database.getInstance().getCourses().get(courseId);
        if(course == null) throw new NotFoundException(String.format("Course with id %d not found", id));
        else {
            for(int studentId : course.getStudents()){
                System.out.println(Database.getInstance().getStudents().get(studentId));
            }
        }
    }

    public void getAllClasses(){
        for(Course course : Database.getInstance().getCourses().values())
        {
            System.out.println(course);
        }
    }

    public void getStudentData(int studentId) throws NotFoundException {
        Student student = Database.getInstance().getStudents().get(studentId);
        if(student == null){
            throw new NotFoundException(String.format("Student with id %d not found", id));
        }else{
            System.out.println(student);
        }
    }

    public void addAssignment(Assignment assignment){
        Database.getInstance().getAssignments().put(assignment.getId(), assignment);
    }

    public void submitStudentsAttendance(ArrayList<CourseAttendance> attendance){
        for (CourseAttendance courseAttendance : attendance) {
            Database.getInstance().getAttendance().put(courseAttendance.getId(), courseAttendance);
        }
    }
}
