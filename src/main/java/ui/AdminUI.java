package ui;
import classes.Database;
import exceptions.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import users.Teacher;
import users.Student;
import users.Admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

public class AdminUI implements UI {
    @Override
    public void display() {

        Logger logger = LogManager.getRootLogger();
        try(InputStream inputFile = Files.newInputStream(Paths.get("src/main/resources/config.properties"))) {
            Properties prop = new Properties();
            prop.load(inputFile);

            String adminName = prop.getProperty("name");
            String adminPass = prop.getProperty("password");

            Database data = Database.getInstance();
            Scanner input = new Scanner(System.in);
            boolean flag = true, gotId = false;
            int function, id;
            String name, email, phone, age, address, gender;

            while(!gotId){

                System.out.print("enter your username : ");
                String userName = input.nextLine();
                System.out.print("enter your password : ");
                String password = input.nextLine();

                if(userName.equals(adminName) && password.equals(adminPass)){
                    gotId = true;
                    logger.info("Admin logged in");
                }else{
                    System.out.println("=> Please Check your username and password and try again");
                    System.out.println("Return : 0\nTry again otherwise");
                    String choice = input.nextLine();
                    if(choice.equals("0")){
                        return;
                    }
                }
            }
            Admin admin = data.getAdmins().get(1);
            while (flag) {
                try {
                    System.out.println("////////////////////////");
                    System.out.println("What do you want to do ?");
                    System.out.println("add teacher         : 1");
                    System.out.println("remove teacher      : 2");
                    System.out.println("view all teachers   : 3");
                    System.out.println("view teacher details: 4");
                    System.out.println("update teacher      : 5");
                    System.out.println("add student         : 6");
                    System.out.println("remove student      : 7");
                    System.out.println("view all students   : 8");
                    System.out.println("view student details: 9");
                    System.out.println("update student      :10");
                    System.out.println("Log Out             :11");
                    System.out.println("////////////////////////");

                    function = Integer.parseInt(input.nextLine());
                    switch (function) {
                        case 1:
                            System.out.println("////////////// New Teacher //////////////");
                            System.out.print("Enter Name: ");
                            name = input.nextLine();
                            System.out.print("Enter Email: ");
                            email = input.nextLine();
                            System.out.print("Enter Phone: ");
                            phone = input.nextLine();
                            Teacher teacher = new Teacher(name, email, phone);
                            admin.addTeacher(teacher);
                            break;
                        case 2:
                            System.out.println("////////////// Removing Teacher //////////////");
                            System.out.print("Enter Id to remove: ");
                            id = Integer.parseInt(input.nextLine());
                            admin.removeTeacher(id);
                            break;
                        case 3:
                            System.out.println("////////////// View ALL Teachers //////////////");
                            admin.viewAllTeachers();
                            break;
                        case 4:
                            System.out.println("////////////// View Teacher //////////////");
                            System.out.print("Enter Id to see details: ");
                            id = Integer.parseInt(input.nextLine());
                            admin.viewTeacherDetails(id);
                            break;
                        case 5:
                            System.out.println("////////////// Update Teacher //////////////");
                            System.out.print("Enter Id: ");
                            id = Integer.parseInt(input.nextLine());
                            System.out.print("Enter Name: ");
                            name = input.nextLine();
                            System.out.print("Enter Email: ");
                            email = input.nextLine();
                            System.out.print("Enter Phone: ");
                            phone = input.nextLine();
                            admin.updateTeacherData(id, name, phone, email);
                            break;
                        case 6:
                            System.out.println("////////////// New Student //////////////");
                            System.out.print("Enter Name: ");
                            name = input.nextLine();
                            System.out.print("Enter Email: ");
                            email = input.nextLine();
                            System.out.print("Enter Phone: ");
                            phone = input.nextLine();
                            System.out.print("Enter Age: ");
                            age = input.nextLine();
                            System.out.print("Enter Address: ");
                            address = input.nextLine();
                            System.out.print("Enter Gender: ");
                            gender = input.nextLine();
                            Student student = new Student(name, email, phone, age, address, gender);
                            admin.addStudent(student);
                            break;
                        case 7:
                            System.out.println("////////////// Removing Student //////////////");
                            System.out.print("Enter Id to remove: ");
                            id = Integer.parseInt(input.nextLine());
                            admin.removeStudent(id);
                            break;
                        case 8:
                            System.out.println("////////////// View ALL Students //////////////");
                            admin.viewAllStudents();
                            break;
                        case 9:
                            System.out.println("////////////// View Student //////////////");
                            System.out.print("Enter Id to see details: ");
                            id = Integer.parseInt(input.nextLine());
                            admin.viewStudentDetails(id);
                            break;
                        case 10:
                            System.out.println("////////////// Update Student //////////////");
                            System.out.print("Enter Id: ");
                            id = Integer.parseInt(input.nextLine());
                            System.out.print("Enter Name: ");
                            name = input.nextLine();
                            System.out.print("Enter Email: ");
                            email = input.nextLine();
                            System.out.print("Enter Phone: ");
                            phone = input.nextLine();
                            System.out.print("Enter Age: ");
                            age = input.nextLine();
                            System.out.print("Enter Address: ");
                            address = input.nextLine();
                            System.out.print("Enter Gender: ");
                            gender = input.nextLine();
                            admin.updateStudentData(id, name, email, phone, age, address, gender);
                            break;
                        case 11:
                            flag = false;
                            logger.info("Admin logged out");
                            break;
                        default:
                            System.err.println("= Please enter a valid number =");
                    }
                } catch (NotFoundException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());

                } catch (NumberFormatException e) {
                    System.err.println("== Please enter a valid number ==");
                    logger.error("User entered invalid number");
                }
            }
        } catch (IOException e) {
            logger.error("Properties File not found");
            throw new RuntimeException(e);
        }
    }
}
