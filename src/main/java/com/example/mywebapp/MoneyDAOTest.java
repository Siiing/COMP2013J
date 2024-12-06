package main.java.com.example.mywebapp;

public class MoneyDAOTest {
    public static void main(String[] args) {
        testUpdateMoney();
    }

    public static void testUpdateMoney() {
        // create test data
        String id = "testId";
        int year = 2018;
        int wealth = 100;

        boolean success = moneyDAO.updateMoney(id, year, wealth);

        // print test result
        if (success) {
            System.out.println("Update money successful");
        } else {
            System.out.println("Update money failed");
        }
    }
}
