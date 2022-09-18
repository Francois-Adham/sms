import classes.Course;
import classes.Database;
import users.Admin;
import users.Student;
import users.Teacher;
import ui.UIFactory;
import ui.UI;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class sms {
    private static void seedDatabase(Database data){
        // Adding admin
        Admin admin = new Admin("Admin", "admin@admin.com", "0123456789");
        data.getAdmins().put(admin.getId(), admin);

        // Adding teachers
        for(int i = 0; i < 10; i++){
            Teacher teacher = new Teacher("teacher" + (i+1), "teacher" + i + "@teacher.com", "012345" + i);
            data.getTeachers().put(teacher.getId(), teacher);
        }

        // Adding students
        for(int i = 0; i < 20; i++){
            Student student = new Student("student" + (i+1), "student" + i + "@student.com", "0123456" + i, Integer.toString(20 + i), "Isac Jacob St", "male");
            data.getStudents().put(student.getId(), student);
        }

        // Adding courses
        for(int i = 0; i < 10; i++){
            Course course = new Course(i+1, "course"+(i+1));
            data.getCourses().put(course.getId(), course);
        }
    }
    private static void users() {
        System.out.println("=============================");
        System.out.println("=========== USERS ===========");
        System.out.println("=============================");
        System.out.println("Please select your user type");
        System.out.println("Admin  : 1");
        System.out.println("Teacher: 2");
        System.out.println("Student: 3");
        System.out.println("Log out: 4");
        System.out.println("=============================");
    }

    public static void main(String[] args) {
        Database data = Database.getInstance();
        Scanner input = new Scanner(System.in);
        seedDatabase(data);
        Logger logger = LogManager.getRootLogger();
        boolean flag = true;
        int user;
        while (flag) {
            users();
            try {
                user = Integer.parseInt(input.nextLine());
                UI ui;
                switch (user) {
                    case 1:
                        ui = UIFactory.getUI("admin");
                        break;
                    case 2:
                        ui = UIFactory.getUI("teacher");
                        break;
                    case 3:
                        ui = UIFactory.getUI("student");
                        break;
                    case 4:
                        flag = false;
                        ui = null;
                        break;
                    default:
                        ui = null;
                        System.err.println("Please Enter a valid number");
                }
                if(ui != null) ui.display();
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number");
                logger.error("Invalid user type");
            }
        }
    }
}
