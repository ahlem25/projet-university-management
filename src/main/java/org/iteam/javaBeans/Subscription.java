package main.java.org.iteam.javaBeans;

public class Subscription {
    private int id;
    private String year;
    private int student_id;
    private int class_id;

    public Subscription() {
    }

    public Subscription(int id, String year, int student_id, int class_id) {
        this.id = id;
        this.year = year;
        this.student_id = student_id;
        this.class_id = class_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public int getClassId() {
        return class_id;
    }

    public void setClassId(int class_id) {
        this.class_id = class_id;
    }
}
