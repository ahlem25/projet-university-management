package main.java.org.iteam.javaBeans;

public class Classe {
    private int id;
    private String name;
    private String comment;
    private String of_year;

    public Classe() {
    }

    public Classe(int id, String name, String comment, String of_year) {
        this(name, comment, of_year);
        this.id = id;
    }

    public Classe(String name, String comment, String of_year) {
        this.name = name;
        this.comment = comment;
        this.of_year = of_year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOfYear() {
        return of_year;
    }

    public void setOfYear(String ofYear) {
        this.of_year = ofYear;
    }
}
