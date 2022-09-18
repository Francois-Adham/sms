package ui;
import classes.Database;
import classes.AssignmentSubmission;
import exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.Student;
import java.util.Scanner;

public class StudentUI implements UI{
    @Override
    public void display() {
        Database data = Database.getInstance();
        Scanner input = new Scanner(System.in);
        boolean flag = true, gotId = false;
        int function, studentId = -1, id;
        Logger logger = LogManager.getRootLogger();

        while(!gotId){
            try {
                System.out.print("Please Enter your Id : ");
                studentId = Integer.parseInt(input.nextLine());
                if(data.getStudents().get(studentId) != null){
                    gotId = true;
                    logger.info(String.format("Student with id %d logged in", studentId));
                }else{
                    System.out.println("=> Please Enter a valid id, Student with id " + studentId + " not found");
                    System.out.println("Return : 0\nTry again otherwise");
                    String choice = input.nextLine();
                    if(choice.equals("0")){
                        return;
                    }
                }
            }catch(NumberFormatException e){
                System.out.println("=> Please Enter a valid id");
                logger.error("Student entered invalid ID");
            }
        }
        Student student = data.getStudents().get(studentId);

        while(flag){
            System.out.println("///////////////////////////////");
            System.out.println("Welcome " + student.getName());
            System.out.println("What do you want to do ?");
            System.out.println("view enrolled courses      : 1");
            System.out.println("view assignments for course: 2");
            System.out.println("submit assignment          : 3");
            System.out.println("enroll in course           : 4");
            System.out.println("Log out                    : 5");
            System.out.println("///////////////////////////////");

            try{
                function = Integer.parseInt(input.nextLine());
                switch (function)
                {
                    case 1:
                        System.out.println("///////////////// View Student's Courses /////////////////");
                        student.viewEnrolledCourses();
                        break;
                    case 2:
                        System.out.println("///////////////// View Assignments /////////////////");
                        System.out.print("Enter Classes.Course Id : ");
                        id = Integer.parseInt(input.nextLine());
                        student.viewAssignments(id);
                        break;
                    case 3:
                        System.out.println("///////////////// Submit Assignment /////////////////");
                        System.out.print("Enter assignment id : ");
                        String assignmentId = input.nextLine();
                        System.out.print("Enter course id : ");
                        String courseId = input.nextLine();
                        System.out.print("Enter content: ");
                        String content = input.nextLine();
                        AssignmentSubmission submission = new AssignmentSubmission(assignmentId, String.valueOf(student.getId()), courseId, content);
                        student.submitAssignment(submission);
                        break;
                    case 4:
                        System.out.println("///////////////// Enroll in Course /////////////////");
                        System.out.print("Enter Course Id : ");
                        id = Integer.parseInt(input.nextLine());
                        student.enrollInCourse(id);
                        break;
                    case 5:
                        flag = false;
                        logger.info(String.format("Student with id %d logged out", studentId));
                        break;
                    default:
                        System.err.println("Please Enter a valid number");
                }
            } catch (NotFoundException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("== Please enter a valid number ==");
                logger.error("User entered invalid number");
            }
        }
    }
}
