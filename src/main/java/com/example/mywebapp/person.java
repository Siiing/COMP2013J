package main.java.com.example.mywebapp;

import javax.persistence.*;

@Entity
@Table(name = "person")
public class person {

    //新加pid,source
    @Id
    @Column(name = "PID")
    private int PID;

    @Column(name = "EnglishName")
    private String EnglishName;

    @Column(name = "ChineseName")
    private String ChineseName;

    @Column(name = "Country")
    private String Country;

    @Column(name = "Food")
    private String Food;

    @Column(name = "Source")
    private String Source;

    public person() {
    }

    public person(int PID, String englishName, String chineseName, String country, String favoriteFood,String source) {
        super();
        this.PID=PID;
        this.EnglishName = englishName;
        this.ChineseName = chineseName;
        this.Country = country;
        this.Food = favoriteFood;
        this.Source=source;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        this.EnglishName = englishName;
    }

    public String getChineseName() {
        return ChineseName;
    }

    public void setChineseName(String chineseName) {
        this.ChineseName = chineseName;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        this.Country = country;
    }


    public String getFood() {
        return Food;
    }

    public void setFood(String Food) {
        this.Food = Food;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }


    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    @Override
    public String toString() {
        return "Person{" +
                "PID='" + PID + '\'' +
                "EnglishName='" + EnglishName + '\'' +
                ", ChineseName='" + ChineseName + '\'' +
                ", Country='" + Country + '\'' +
                ", FavoriteFood='" + Food + '\'' +
                ", Source='" + Source + '\'' +
                '}';
    }
}
