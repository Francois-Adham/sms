package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import classes.CourseAttendance;
import classes.Database;
import exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.Teacher;
import classes.Assignment;

public class TeacherUI implements UI {

    @Override
    public void display() {
        Database data = Database.getInstance();
        Scanner input = new Scanner(System.in);
        boolean flag = true, gotId = false;
        int function, teacherId = -1, id;
        Logger logger = LogManager.getRootLogger();
        while(!gotId){
            try {
                System.out.print("Please Enter your Id : ");
                teacherId = Integer.parseInt(input.nextLine());
                if(data.getTeachers().get(teacherId) != null){
                    gotId = true;
                    logger.info(String.format("Teacher with id %d logged in", teacherId));
                }else{
                    System.out.println("=> Please Enter a valid id, Teacher with id " + teacherId + " not found");
                    System.out.println("Return : 0\nTry again otherwise");
                    String choice = input.nextLine();
                    if(choice.equals("0")){
                        return;
                    }
                }
            }catch(NumberFormatException e){
                System.out.println("=> Please Enter a valid id");
                logger.error("Teacher entered invalid ID");
            }
        }
        Teacher teacher = data.getTeachers().get(teacherId);
        while(flag){
            System.out.println("////////////////////////////");
            System.out.println("Welcome " + teacher.getName());
            System.out.println("What do you want to do ?");
            System.out.println("view students' classes  : 1");
            System.out.println("get students in course  : 2");
            System.out.println("get all classes         : 3");
            System.out.println("get student's data  x`    : 4");
            System.out.println("add assignment          : 5");
            System.out.println("add students' attendance: 6");
            System.out.println("Log Out                 : 7");
            System.out.println("////////////////////////////");

            try{
                function = Integer.parseInt(input.nextLine());
                switch (function)
                {
                    case 1:
                        System.out.println("///////////////// View ALL Students' Courses /////////////////");
                        teacher.viewStudentsAssignedClasses();
                        break;
                    case 2:
                        System.out.println("///////////////// View Classes.Course's Students  /////////////////");
                        System.out.print("Enter Classes.Course Id : ");
                        id = Integer.parseInt(input.nextLine());
                        teacher.getStudentsInCourse(id);
                        break;
                    case 3:
                        System.out.println("///////////////// View ALL Courses /////////////////");
                        teacher.getAllClasses();
                        break;
                    case 4:
                        System.out.println("///////////////// View Student /////////////////");                        System.out.print("Enter Classes.Course Id : ");
                        System.out.print("Enter Student Id : ");
                        id = Integer.parseInt(input.nextLine());
                        teacher.getStudentData(id);
                        break;
                    case 5:
                        System.out.println("///////////////// Add Classes.Assignment /////////////////");
                        System.out.print("Enter assignment description : ");
                        String description = input.nextLine();
                        System.out.print("Enter course id : ");
                        String courseId = input.nextLine();
                        System.out.print("Enter due date (dd/mm/yyyy): ");
                        Date dueDate = new SimpleDateFormat("dd/MM/yyyy").parse(input.nextLine());
                        Assignment assignment = new Assignment(description, courseId, dueDate);
                        teacher.addAssignment(assignment);
                        break;
                    case 6:
                        System.out.println("///////////////// Add Attendance /////////////////");
                        ArrayList<CourseAttendance> attendances = new ArrayList<>();
                        boolean notFinished = true;
                        do {
                            System.out.print("Enter student id : ");
                            String studentId = input.nextLine();
                            System.out.print("Enter course id : ");
                            String course = input.nextLine();
                            System.out.print("Enter status: ");
                            String status = input.nextLine();
                            attendances.add(new CourseAttendance(studentId, course, status));
                            System.out.println("Enter 1 for more attendance\nanything else to finish");
                            if(input.nextLine().equals("1")) notFinished = false;
                        } while(notFinished);
                        teacher.submitStudentsAttendance(attendances);
                        break;
                    case 7:
                        flag = false;
                        logger.info(String.format("Teacher with id %d logged out", teacherId));
                        break;
                    default:
                        System.err.println("Please Enter a valid number");
                }
            } catch(NotFoundException e){
                e.printStackTrace();
                logger.error(e.getMessage());
            }
            catch (ParseException e){
                System.err.println("Please Enter a valid date in the format 'dd/mm/yyyy' ");
                logger.error("User entered invalid date format");
            }
            catch(NumberFormatException e){
                System.err.println("Please Enter a valid number");
                logger.error("User entered invalid number");
            }
        }
    }
}
