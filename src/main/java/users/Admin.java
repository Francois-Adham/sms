package users;
import classes.Database;
import exceptions.NotFoundException;

public class Admin  extends Person {
    private static int adminCount = 0;
    public Admin(String name, String email, String mobileNumber){
        Admin.adminCount++;
        this.id = Admin.adminCount;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    // =====================================
    // ============== METHODS ==============
    // =====================================

    // ======= Add =======
    public void addTeacher(Teacher teacher){
        try {
            Database.getInstance().getTeachers().put(teacher.getId(), teacher);
        } catch (Exception e) {
            System.err.println("Could not insert new teacher");
            e.printStackTrace();
        }
    }

    public void addStudent(Student student){
        try {
            Database.getInstance().getStudents().put(student.getId(), student);
        } catch (Exception e) {
            System.err.println("Could not insert new student");
            e.printStackTrace();
        }
    }


    // ======= Remove =======
    public void removeTeacher(int id) throws NotFoundException{

        Teacher teacher = Database.getInstance().getTeachers().remove(id);
        if(teacher == null) {
            throw new NotFoundException(String.format("Teacher with id %d not found", id));
        }
    }

    public void removeStudent(int id) throws NotFoundException{
        System.out.println("///////////////// Removing Users.Student /////////////////");
        Student student = Database.getInstance().getStudents().remove(id);
        if(student == null){
            throw new NotFoundException(String.format("Student with id %d not found", id));
        }
    }


    // ======= View All =======
    public void viewAllTeachers(){
        for(Teacher teacher: Database.getInstance().getTeachers().values()){
            System.out.println(teacher);
        }
    }

    public void viewAllStudents(){
        System.out.println("///////////////// View ALL Students /////////////////");
        for(Student student: Database.getInstance().getStudents().values()){
            System.out.println(student);
        }
    }

    // ======= View Details =======
    public void viewTeacherDetails( int id) throws NotFoundException{
        Teacher teacher = Database.getInstance().getTeachers().get(id);
        if(teacher == null){
            throw new NotFoundException(String.format("Teacher with id %d not found", id));
        }else{
            System.out.println(teacher);
        }
    }

    public void viewStudentDetails(int id) throws NotFoundException{
        System.out.println("///////////////// View Student /////////////////");
        Student student = Database.getInstance().getStudents().get(id);
        if(student == null){
            throw new NotFoundException(String.format("Student with id %d not found", id));
        }else{
            System.out.println(student);
        }
    }


    // ======= Update =======
    public void updateTeacherData( int id, String name, String phone, String email) throws NotFoundException{
        Teacher teacher = Database.getInstance().getTeachers().get(id);
        if(teacher == null)
        {
            throw new NotFoundException(String.format("Teacher with id %d not found", id));
        }else{
            teacher.setEmail(email);
            teacher.setName(name);
            teacher.setMobileNumber(phone);
            System.out.println("teacher updated");
        }
    }

    public void updateStudentData( int id, String name, String email, String phone, String age, String address, String gender) throws NotFoundException{
        Student student = Database.getInstance().getStudents().get(id);
        if(student == null){
            throw new NotFoundException(String.format("Student with id %d not found", id));
        }else{
            student.setEmail(email);
            student.setName(name);
            student.setMobileNumber(phone);
            student.setAddress(address);
            student.setAge(age);
            student.setGender(gender);
            System.out.println("student updated");
        }
    }
}
