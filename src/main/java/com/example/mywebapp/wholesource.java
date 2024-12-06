package main.java.com.example.mywebapp;

import javax.persistence.*;

@Entity
@Table(name = "wholesource")
public class wholesource {

    @Id
    @Column(name = "SourceOfWealth")
    private String SourceOfWealth;

    @Column(name = "Wealth2018")
    private int wealth2018;

    @Column(name = "Wealth2019")
    private int wealth2019;

    @Column(name = "Wealth2020")
    private int wealth2020;

    @Column(name = "Wealth2021")
    private int wealth2021;

    @Column(name= "Wealth2022")
    private int wealth2022;

    public wholesource() {
    }

    public wholesource(String sourceOfWealth, int wealth2018, int wealth2019, int wealth2020, int wealth2021, int wealth2022) {
        SourceOfWealth = sourceOfWealth;
        this.wealth2018 = wealth2018;
        this.wealth2019 = wealth2019;
        this.wealth2020 = wealth2020;
        this.wealth2021 = wealth2021;
        this.wealth2022 = wealth2022;
    }

    public String getSourceOfWealth() {
        return SourceOfWealth;
    }

    public void setSourceOfWealth(String sourceOfWealth) {
        SourceOfWealth = sourceOfWealth;
    }

    public int getWealth2018() {
        return wealth2018;
    }

    public void setWealth2018(int wealth2018) {
        this.wealth2018 = wealth2018;
    }

    public int getWealth2019() {
        return wealth2019;
    }

    public void setWealth2019(int wealth2019) {
        this.wealth2019 = wealth2019;
    }

    public int getWealth2020() {
        return wealth2020;
    }

    public void setWealth2020(int wealth2020) {
        this.wealth2020 = wealth2020;
    }

    public int getWealth2021() {
        return wealth2021;
    }

    public void setWealth2021(int wealth2021) {
        this.wealth2021 = wealth2021;
    }

    public int getWealth2022() {
        return wealth2022;
    }

    public void setWealth2022(int wealth2022) {
        this.wealth2022 = wealth2022;
    }
}
