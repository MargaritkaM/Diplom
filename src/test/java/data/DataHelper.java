package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo getApprovedUser() {
        return new AuthInfo("4444444444444441", "06","25","Musatova Margarita","123");
    }
    public static AuthInfo getDeclinedUser() {
        return new AuthInfo("4444444444444442", "06","25","Musatova Margarita","123");
    }
    public static AuthInfo getEmptyUser() {
        return new AuthInfo("", "","","","");
    }

    @Value
    public static class AuthInfo{
        String cardNumber;
        String month;
        String year;
        String owner;
        String codeCvc;
    }
}
