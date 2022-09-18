package classes;

import java.util.HashSet;

public class Course {
    private final int id;
    private final String name;
    private int teacherId;
    private final HashSet<Integer> assignments = new HashSet<>();
    private final HashSet<Integer> students = new HashSet<>();
    private static int courseCount = 0;

    public Course(int teacherId, String courseName){
        this.teacherId = teacherId;
        Course.courseCount++;
        this.id = Course.courseCount;
        this.name = courseName;
    }

    @Override
    public String toString() {
        return "{\n\t Classes.Course id: " + id +
                "\n\t Classes.Course name: " + name +
                "\n\t Users.Teacher id: " + teacherId +"\n}";
    }

    public int getId(){
        return id;
    }

    public HashSet<Integer> getAssignments(){
        return assignments;
    }

    public HashSet<Integer> getStudents(){
        return students;
    }

    public void addStudent(int studentId) { students.add(studentId); }

    public int getTeacherId(){
        return this.teacherId;
    }

    public void setTeacherId(int newTeacherId){
        this.teacherId = newTeacherId;
    }
}
