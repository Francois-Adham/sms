package ui;

public class UIFactory {

    public static UI getUI(String type){
        switch (type.toLowerCase()){
            case "admin":
                return new AdminUI();
            case "teacher":
                return new TeacherUI();
            case "student":
                return new StudentUI();
            default:
                return null;
        }
    }
}
