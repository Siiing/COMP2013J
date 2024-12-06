package main.java.com.example.mywebapp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//新加表
@Entity
@Table(name = "result")
public class result {

    @Id
    @Column(name = "PID")
    private int PID;

    @Column(name = "Ranks")
    private int Rank;

    @Column(name = "Wealth")
    private String Wealth;

    @Column(name = "year")
    private int Year;

    public result() {

    }

    public result(int PID, int rank, String wealth, int year) {
        this.PID = PID;
        Rank = rank;
        Wealth = wealth;
        Year = year;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int rank) {
        Rank = rank;
    }

    public String getWealth() {
        return Wealth;
    }

    public void setWealth(String wealth) {
        Wealth = wealth;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }
}