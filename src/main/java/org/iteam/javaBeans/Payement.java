package main.java.org.iteam.javaBeans;

import java.util.Date;

public class Payement {
    private int id;
    private String amount;
    private Date date;
    private String comment;
    private int student_id;

    public Payement() {
    }

    public Payement(int id, String amount, Date date, String comment, int student_id) {
        this(amount, date, comment, student_id);
        this.id = id;
    }

    public Payement(String amount, Date date, String comment, int student_id) {
        this.amount = amount;
        this.date = date;
        this.comment = comment;
        this.student_id = student_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }
}
