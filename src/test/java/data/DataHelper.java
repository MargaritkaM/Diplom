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
    public static AuthInfo getAnotherCardNumber() {
        return new AuthInfo("1111222233334441", "06","25","Musatova Margarita","123");
    }

    public static String CVC() {
        return faker.number().digits(3);
    }

    public static AuthInfo getLettersCardNumber() {
        return new AuthInfo("LOP", "06","25","Musatova Margarita",CVC());
    }
    public static AuthInfo getRussianLettersCardNumber() {
        return new AuthInfo("Поп", "06","25","Musatova Margarita",CVC());
    }
    public static AuthInfo getSymbolsCardNumber() {
        return new AuthInfo("@#", "06","25","Musatova Margarita",CVC());
    }
    public static AuthInfo getOneDigitCardNumber() {
        return new AuthInfo("1", "06","25","Musatova Margarita",CVC());
    }
    public static AuthInfo getSevenDigitCardNumber() {
        return new AuthInfo("1234567", "06","25","Musatova Margarita",CVC());
    }
    public static AuthInfo getSeventeenDigitCardNumber() {
        return new AuthInfo("1234 5678 9123 4345 1", "06","25","Musatova Margarita",CVC());
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
