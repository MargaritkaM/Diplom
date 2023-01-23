package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import data.SqlHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static java.awt.SystemColor.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTest {

    @BeforeEach
    void setup() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }

    @AfterEach
    void cleanAll() {
        SqlHelper.cleanAll();
    }


    @Test
    public void buyHappyPathApproved() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getApprovedUser();
        PaymentPage.buy(cardInfo);
        PaymentPage.successfulWay();
        assertEquals(SqlHelper.getStatus(), "APPROVED");
    }

    @Test
    public void buyBadPathDeclined() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getDeclinedUser();
        PaymentPage.buy(cardInfo);
        PaymentPage.unSuccessfulWay();
        assertEquals(SqlHelper.getStatus(), "DECLINED");
    }

    @Test
    public void creditHappyPathApproved() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getApprovedUser();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.successfulWay();
        assertEquals(SqlHelper.getStatus(), "APPROVED");
    }

    @Test
    public void creditBadPathDeclined() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getDeclinedUser();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.successfulWay();
        assertEquals(SqlHelper.getStatus(), "DECLINED");
    }

    @Test
    public void buyEmptyUser() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getEmptyUser();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
        PaymentPage.monthError();
        PaymentPage.yearError();
        PaymentPage.ownerError();
        PaymentPage.cvcError();
    }

    @Test
    public void CreditEmptyUser() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getEmptyUser();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
        PaymentPage.monthError();
        PaymentPage.yearError();
        PaymentPage.ownerError();
        PaymentPage.cvcError();
    }

    @Test
    public void buyAnotherCardNumber() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getAnotherCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.unSuccessfulWay();
        assertEquals(SqlHelper.getStatus(), "DECLINED");
    }

    @Test
    public void buyLettersCardNumber() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getLettersCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buyRussianLettersCardNumber() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getRussianLettersCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySymbolsCardNumber() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getSymbolsCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
        PaymentPage.cleanStrings();
    }

    @Test
    public void buyLettersCreditCardNumber() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getLettersCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buyRussianLettersCreditCardNumber() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getRussianLettersCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySymbolsCardCreditNumber() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getSymbolsCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
        PaymentPage.cleanStrings();
    }

    @Test
    public void buyOneCardNumber() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getOneDigitCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySevenCardNumber() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getSevenDigitCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySeventeenCardNumber() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getSeventeenDigitCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.checkValueCardNumber("1234 5678 9123 4345");
    }
    @Test
    public void buyOneCreditCardNumber() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getOneDigitCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySevenCreditCardNumber() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getSevenDigitCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySeventeenCreditCardNumber() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getSeventeenDigitCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.checkValueCardNumber("1234 5678 9123 4345");
    }
}

