package main.java.com.example.mywebapp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rank")
public class rank {

    @Id
    @Column(name = "EnglishName")
    private String EnglishName;

    @Column(name = "ChineseName")
    private String ChineseName;

    @Column(name = "Rank2018")
    private int Rank2018;

    @Column(name = "Rank2019")
    private int Rank2019;

    @Column(name = "Rank2020")
    private int Rank2020;

    @Column(name = "Rank2021")
    private int Rank2021;

    @Column(name = "Rank2022")
    private int Rank2022;

    public rank() {
    }

    public rank(String englishName, String chineseName, int rank2018, int rank2019, int rank2020,int rank2021,int rank2022) {
        super();
        this.EnglishName = englishName;
        this.ChineseName = chineseName;
        this.Rank2018 = rank2018;
        this.Rank2019 = rank2019;
        this.Rank2020 = rank2020;
        this.Rank2021 = rank2021;
        this.Rank2022 = rank2022;
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

    public int getRank2018() {
        return Rank2018;
    }

    public void setRank2018(int rank2018) {
        this.Rank2018 = rank2018;
    }

    public int getRank2019() {
        return Rank2019;
    }

    public void setRank2019(int rank2019) {
        this.Rank2019 = rank2019;
    }

    public int getRank2020() {
        return Rank2020;
    }

    public void setRank2020(int rank2020) {
        this.Rank2020 = rank2020;
    }

    public int getRank2021() {
        return Rank2021;
    }

    public void setRank2021(int rank2021) {
        this.Rank2021 = rank2021;
    }

    public int getRank2022() {
        return Rank2022;
    }

    public void setRank2022(int rank2022) {
        this.Rank2022 = rank2022;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "englishName='" + EnglishName + '\'' +
                ", chineseName='" + ChineseName + '\'' +
                ", rank2018=" + Rank2018 +
                ", rank2019=" + Rank2019 +
                ", rank2020=" + Rank2020 +
                ", rank2020=" + Rank2021 +
                ", rank2020=" + Rank2022 +
                '}';
    }
}
