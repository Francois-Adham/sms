package users;
import classes.Database;
import classes.Course;
import classes.AssignmentSubmission;
import exceptions.NotFoundException;

import java.util.HashSet;

public class Student extends Person {
    private String age;
    private String address;
    private String gender;
    private final HashSet<Integer> enrolledCourses = new HashSet<>();
    private static int studentCount = 0;

    public Student(String name, String email, String mobileNumber, String age, String address, String gender){
        Student.studentCount++;
        this.id = Student.studentCount;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.address = address;
        this.gender = gender;
    }

    public String toString() {
        return "{\n\tUsers.Student id: " + id +
                "\n\tname: " + name +
                "\n\temail: " + email +
                "\n\tmobile phone: " + mobileNumber +
                "\n\taddress: " + address +
                "\n\tage: " + age +
                "\n\tgender: " + gender + "\n}";
    }

    // =====================================
    // ============== SETTERS ==============
    // =====================================
    public void setAge(String newAge) {
        this.age = newAge;
    }

    public void setAddress(String newAddress) {
        this.address = newAddress;
    }

    public void setGender(String newGender) {
        this.gender = newGender;
    }


    // =====================================
    // ============== GETTERS ==============
    // =====================================
    public String getAge(){
        return this.age;
    }
    public String getAddress(){
        return this.address;
    }
    public String getGender(){
        return this.gender;
    }

    public HashSet<Integer> getCourses(){
        return enrolledCourses;
    }

    // =====================================
    // ============== METHODS ==============
    // =====================================
    public void viewEnrolledCourses(){
        for(int courseId: enrolledCourses){
            System.out.println(Database.getInstance().getCourses().get(courseId));
        }
    }

    public void viewAssignments(int courseId) throws NotFoundException{
        Database data = Database.getInstance();
        Course course = data.getCourses().get(courseId);
        if (course == null) throw new NotFoundException(String.format("Course with id %d not found", courseId));
        else {
            for (int assignmentId : course.getAssignments()) {
                System.out.println(data.getAssignments().get(assignmentId));
            }
        }
    }

    public void submitAssignment(AssignmentSubmission assignmentSubmission){
        Database.getInstance().getSubmissions().put(assignmentSubmission.getId(), assignmentSubmission);
    }

    public void enrollInCourse(int courseId) throws NotFoundException {
        Course course = Database.getInstance().getCourses().get(courseId);
        if (course == null) throw new NotFoundException(String.format("Course with id %d not found", courseId));
        else {
            this.enrolledCourses.add(courseId);
            course.addStudent(id);
        }
    }
}
