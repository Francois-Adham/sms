package classes;

import users.Admin;
import users.Student;
import users.Teacher;

import java.util.HashMap;

public class Database {
    private final HashMap<Integer, Admin> admins = new HashMap<>();
    private final HashMap<Integer, Assignment> assignments = new HashMap<>();
    private final HashMap<Integer, AssignmentSubmission> submissions = new HashMap<>();
    private final HashMap<Integer, Course> courses = new HashMap<>();
    private final HashMap<Integer, CourseAttendance> attendances = new HashMap<>();
    private final HashMap<Integer, Student> students = new HashMap<>();
    private final HashMap<Integer, Teacher> teachers = new HashMap<>();

    // ==========================================
    // ================== ENUM ==================
    // ==========================================
    /*
    public enum Database {
        instance;
        private Database(){}
        private final HashMap<Integer, Admin> admins = new HashMap<>();
        private final HashMap<Integer, Assignment> assignments = new HashMap<>();
        private final HashMap<Integer, AssignmentSubmission> submissions = new HashMap<>();
        private final HashMap<Integer, Course> courses = new HashMap<>();
        private final HashMap<Integer, CourseAttendance> attendances = new HashMap<>();
        private final HashMap<Integer, Student> students = new HashMap<>();
        private final HashMap<Integer, Teacher> teachers = new HashMap<>();

        public HashMap<Integer, Admin> getAdmins(){ return admins; }
        public HashMap<Integer, Assignment> getAssignments(){ return assignments; }
        public HashMap<Integer, AssignmentSubmission> getSubmissions(){ return submissions; }
        public HashMap<Integer, Course> getCourses(){ return courses; }
        public HashMap<Integer, CourseAttendance> getAttendance(){ return attendances; }
        public HashMap<Integer, Student> getStudents(){ return students; }
        public HashMap<Integer, Teacher> getTeachers(){ return teachers; }

    }
    */


    // ===========================================
    // =============== THREAD SAFE ===============
    // ===========================================
    /*
    private static Classes.Database database = null;
    public static synchronized Classes.Database getInstance(){
        if(Classes.Database.database == null)
            Classes.Database.database = new Classes.Database();
        return Classes.Database.database;
    }
    */

    // =====================================
    // =============== EAGER ===============
    // =====================================
    private static final Database database = new Database();
    public static Database getInstance(){
        return Database.database;
    }


    private Database(){}

    public HashMap<Integer, Admin> getAdmins(){ return admins; }
    public HashMap<Integer, Assignment> getAssignments(){ return assignments; }
    public HashMap<Integer, AssignmentSubmission> getSubmissions(){ return submissions; }
    public HashMap<Integer, Course> getCourses(){ return courses; }
    public HashMap<Integer, CourseAttendance> getAttendance(){ return attendances; }
    public HashMap<Integer, Student> getStudents(){ return students; }
    public HashMap<Integer, Teacher> getTeachers(){ return teachers; }
}
