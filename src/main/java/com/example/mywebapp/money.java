package main.java.com.example.mywebapp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "money")
public class money {

    @Id
    @Column(name = "EnglishName")
    private String EnglishName;

    @Column(name = "ChineseName")
    private String ChineseName;

    @Column(name = "Wealth2018")
    private int Wealth2018;

    @Column(name = "Wealth2019")
    private int Wealth2019;

    @Column(name = "Wealth2020")
    private int Wealth2020;

    @Column(name = "Wealth2021")
    private int Wealth2021;

    @Column(name = "Wealth2022")
    private int Wealth2022;

    public money() {
    }

    public money(String englishName, String chineseName, int wealth2018, int wealth2019, int wealth2020,int wealth2021, int wealth2022) {
        super();
        this.EnglishName = englishName;
        this.ChineseName = chineseName;
        this.Wealth2018 = wealth2018;
        this.Wealth2019 = wealth2019;
        this.Wealth2020 = wealth2020;
        this.Wealth2021 = wealth2021;
        this.Wealth2022 = wealth2022;
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

    public int getWealth2018() {
        return Wealth2018;
    }

    public void setWealth2018(int wealth2018) {
        this.Wealth2018 = wealth2018;
    }

    public float getWealth2019() {
        return Wealth2019;
    }

    public void setWealth2019(int Wealth2019) {
        this.Wealth2019 = Wealth2019;
    }

    public int getWealth2020() {
        return Wealth2020;
    }

    public void setWealth2020(int wealth2020) {
        this.Wealth2020 = wealth2020;
    }

    public int getWealth2021() {
        return Wealth2021;
    }

    public void setWealth2021(int wealth2021) {
        this.Wealth2021 = wealth2021;
    }

    public int getWealth2022() {
        return Wealth2022;
    }

    public void setWealth2022(int wealth2022) {
        this.Wealth2022 = wealth2022;
    }

    @Override
    public String toString() {
        return "Money{" +
                "englishName='" + EnglishName + '\'' +
                ", chineseName='" + ChineseName + '\'' +
                ", wealth2018=" + Wealth2018 +
                ", wealth2019=" + Wealth2019 +
                ", wealth2020=" + Wealth2020 +
                ", wealth2019=" + Wealth2021 +
                ", wealth2020=" + Wealth2022 +
                '}';
    }
}
