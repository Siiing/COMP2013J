package main.java.com.example.mywebapp;

import java.util.HashMap;
import java.util.Map;

public class InitUserInfo {
    private static final Map<String, String> adminCredentials = new HashMap<>();
    private static final Map<String, String> guestCredentials = new HashMap<>();

    static {
        //Admins
        adminCredentials.put("Li", "0608");
        adminCredentials.put("Shen", "1101");
        adminCredentials.put("An", "0910");
        adminCredentials.put("Zhu", "0126");

        //guest
        guestCredentials.put("AA", "123");
        guestCredentials.put("BB", "234");
        guestCredentials.put("CC", "345");
        guestCredentials.put("DD", "456");
    }

    public static Map<String, String> getAdminCredentials() {
        return adminCredentials;
    }

    public static Map<String, String> getGuestCredentials() {
        return guestCredentials;
    }
}
