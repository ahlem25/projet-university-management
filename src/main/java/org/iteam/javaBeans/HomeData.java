package main.java.org.iteam.javaBeans;

public class HomeData {
    private int payementsNbr;
    private int subscriptionsNbr;
    private int usersNbr;
    private int studentsNbr;

    public HomeData() {
    }

    public HomeData(int payementsNbr, int subscriptionsNbr, int usersNbr, int studentsNbr) {
        this.payementsNbr = payementsNbr;
        this.subscriptionsNbr = subscriptionsNbr;
        this.usersNbr = usersNbr;
        this.studentsNbr = studentsNbr;
    }

    public int getPayementsNbr() {
        return payementsNbr;
    }

    public void setPayementsNbr(int payementsNbr) {
        this.payementsNbr = payementsNbr;
    }

    public int getSubscriptionsNbr() {
        return subscriptionsNbr;
    }

    public void setSubscriptionsNbr(int subscriptionsNbr) {
        this.subscriptionsNbr = subscriptionsNbr;
    }

    public int getUsersNbr() {
        return usersNbr;
    }

    public void setUsersNbr(int usersNbr) {
        this.usersNbr = usersNbr;
    }

    public int getStudentsNbr() {
        return studentsNbr;
    }

    public void setStudentsNbr(int studentsNbr) {
        this.studentsNbr = studentsNbr;
    }
}
