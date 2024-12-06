package main.java.com.example.mywebapp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "year")
public class year {

    @Id
    @Column(name = "year")
    private int Year;

    @Column(name = "Link")
    private String Link;

    @Column(name = "Date")
    private String Date;
    

    public year() {
    }

    public year(int Year, String Link, String Date) {
        super();
        this.Year = Year;
        this.Link = Link;
        this.Date = Date;

    }


    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        this.Year = year;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        this.Link = link;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
    
    @Override
    public String toString() {
        return "year{" +
                "Year='" + Year + '\'' +
                ", Link='" + Link + '\'' +
                ", Data=" + Date +
                '}';
    }
}

