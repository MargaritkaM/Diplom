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
    public void buyApprovedPaymentGate() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getApprovedUser();
        PaymentPage.buy(cardInfo);
        PaymentPage.successfulWay();
        assertEquals(SqlHelper.getStatus(), "APPROVED");
    }
    @Test
    public void buyDeclinedPaymentGate() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getDeclinedUser();
        PaymentPage.buy(cardInfo);
        PaymentPage.unSuccessfulWay();
        assertEquals(SqlHelper.getStatus(), "DECLINED");
    }
    @Test
    public void buyApprovedCreditGate() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getApprovedUser();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.successfulWay();
        assertEquals(SqlHelper.getStatusCredit(), "APPROVED");
    }
    @Test
    public void buyDeclinedCreditGate() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getDeclinedUser();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.unSuccessfulWay();
        assertEquals(SqlHelper.getStatusCredit(), "DECLINED");
    }
    @Test
    public void buyEmptyUserPaymentGate() {
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
    public void buyEmptyUserCreditGate() {
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
    public void buyAnotherCardNumberPaymentGate() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getAnotherCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.unSuccessfulWay();
        assertEquals(SqlHelper.getStatus(), "DECLINED");
    }
    @Test
    public void buyAnotherCardNumberCreditGate() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getAnotherCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.unSuccessfulWay();
        assertEquals(SqlHelper.getStatusCredit(), "DECLINED");
    }

    @Test
    public void buyLettersCardNumberPaymentGate() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getLettersCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buyRussianLettersCardNumberPaymentGate() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getRussianLettersCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySymbolsCardNumberPaymentGate() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getSymbolsCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
    }
    @Test
    public void buyCleanPaymentCreditGate() {
        var cardInfo = DataHelper.getApprovedUser();
        PaymentPage.cleanStrings(cardInfo);
    }
    @Test
    public void buyLettersCardNumberCreditGate() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getLettersCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buyRussianLettersCardNumberCreditGate() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getRussianLettersCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySymbolsCardNumberCreditGate() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getSymbolsCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buyOneCardNumberPaymentGate() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getOneDigitCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySevenCardNumberPaymentGate() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getSevenDigitCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySeventeenCardNumberPaymentGate() {
        PaymentPage.payToButton();
        var cardInfo = DataHelper.getSeventeenDigitCardNumber();
        PaymentPage.buy(cardInfo);
        PaymentPage.checkValueCardNumber("1234 5678 9123 4345");
    }

    @Test
    public void buyOneCardNumberCreditGate() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getOneDigitCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySevenCardNumberCreditGate() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getSevenDigitCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.cardNumberError();
    }

    @Test
    public void buySeventeenCardNumberCreditGate() {
        PaymentPage.payInCreditToButton();
        var cardInfo = DataHelper.getSeventeenDigitCardNumber();
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.checkValueCardNumber("1234 5678 9123 4345");
    }

    @ParameterizedTest
    @CsvSource({
            "ghjf",
            "пра",
            "@@",
            "1"
    })
    void buyIncorLettersSymbols1MonthPaymentGate(String month) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                month, approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buy(newCard);
        paymentPage.monthError();
    }

    @ParameterizedTest
    @CsvSource({
            "13",
            "00"
    })
    void buyIncorValuesMonthPaymentGate(String month) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                month, approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buy(newCard);
        paymentPage.monthError2();
    }
    @ParameterizedTest
    @CsvSource({
            "ghjf",
            "пра",
            "@@",
            "1"
    })
    void buyIncorLettersSymbols1MonthCreditGate(String month) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                month, approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buyCredit(newCard);
        paymentPage.monthError();
    }

    @ParameterizedTest
    @CsvSource({
            "13",
            "00"
    })
    void buyIncorValuesMonthCreditGate(String month) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                month, approvedCard.getYear(), approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buyCredit(newCard);
        paymentPage.monthError2();
    }

    @ParameterizedTest
    @CsvSource({
            "ghjf",
            "пра",
            "@@",
            "1"
    })
    void buyIncorLettersSymbols1YearPaymentGate(String year) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), year, approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buy(newCard);
        paymentPage.monthError();
    }

    @ParameterizedTest
    @CsvSource({
            "13",
            "00"
    })
    void buyIncorValuesYearPaymentGate(String year) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), year, approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buy(newCard);
        paymentPage.yearError2();
    }

    @ParameterizedTest
    @CsvSource({
            "91"
    })
    void buyMoreIncorValuesYearPaymentGate(String year) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), year, approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buy(newCard);
        paymentPage.yearError3();
    }

    @ParameterizedTest
    @CsvSource({
            "ghjf",
            "пра",
            "@@",
            "1"
    })
    void buyIncorLettersSymbols1YearCreditGate(String year) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), year, approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buyCredit(newCard);
        paymentPage.yearError();
    }

    @ParameterizedTest
    @CsvSource({
            "13",
            "00"
    })
    void buyIncorValuesYearCreditGate(String year) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), year, approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buyCredit(newCard);
        paymentPage.yearError2();
    }

    @ParameterizedTest
    @CsvSource({
            "91"
    })
    void buyMoreIncorValuesYearCreditGate(String year) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), year, approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buyCredit(newCard);
        paymentPage.yearError3();
    }
    @Test
    public void buyLastMonthYearPaymentGate() {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var month = DataHelper.generateMonthYear(1, "MM");
        var year = DataHelper.generateMonthYear(1, "yy");
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                month, year, approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buy(newCard);
        paymentPage.yearError2();
    }
    @Test
    public void buyLastMonthYearCreditGate() {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var month = DataHelper.generateMonthYear(1, "MM");
        var year = DataHelper.generateMonthYear(1, "yy");
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                month, year, approvedCard.getOwner(), approvedCard.getCodeCvc());
        paymentPage.buyCredit(newCard);
        paymentPage.yearError2();
    }

    @Test
    public void buyThreeDigitsMonthPaymentCreditGate() {
        var cardInfo = DataHelper.getThreeDigitsMonth();
        PaymentPage.buy(cardInfo);
        PaymentPage.checkValueMonth("12");
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.checkValueMonth("12");
    }
    @Test
    public void buyThreeDigitsYearPaymentCreditGate() {
        var cardInfo = DataHelper.getThreeDigitsYear();
        PaymentPage.buy(cardInfo);
        PaymentPage.checkValueYear("25");
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.checkValueYear("25");
    }

    @ParameterizedTest
    @CsvSource({
            "ggg",
            "пра",
            "@@",
            "1"
    })
    void buyIncorLettersSymbols1OwnerPaymentGate(String owner) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), owner, approvedCard.getCodeCvc());
        paymentPage.buy(newCard);
        paymentPage.ownerError2();
    }

    @ParameterizedTest
    @CsvSource({
            "Nash-Williams",
            "Archinevolokocherepopindrikovskaya",
            "a"
    })
    void buyCorOwnerPaymentGate(String owner) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), owner, approvedCard.getCodeCvc());
        paymentPage.buy(newCard);
        paymentPage.successfulWay();
    }

    @ParameterizedTest
    @CsvSource({
            "ggg",
            "пра",
            "@@",
            "1"
    })
    void buyIncorLettersSymbols1OwnerCreditGate(String owner) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), owner, approvedCard.getCodeCvc());
        paymentPage.buyCredit(newCard);
        paymentPage.ownerError2();
    }

    @ParameterizedTest
    @CsvSource({
            "Nash-Williams",
            "Archinevolokocherepopindrikovskaya",
            "a"
    })
    void buyCorOwnerCreditGate(String owner) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), owner, approvedCard.getCodeCvc());
        paymentPage.buyCredit(newCard);
        paymentPage.successfulWay();
    }

    @ParameterizedTest
    @CsvSource({
            "ghjf",
            "пра",
            "@@",
            "1"
    })
    void buyIncorLettersSymbols1CvcPaymentGate(String cvc) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), approvedCard.getOwner(), cvc);
        paymentPage.buy(newCard);
        paymentPage.cvcError();
    }

    @ParameterizedTest
    @CsvSource({
            "ghjf",
            "пра",
            "@@",
            "1"
    })
    void buyIncorLettersSymbols1CvcCreditGate(String cvc) {
        var paymentPage = new PaymentPage();
        var approvedCard = DataHelper.getApprovedUser();
        var newCard = new DataHelper.AuthInfo(approvedCard.getCardNumber(),
                approvedCard.getMonth(), approvedCard.getYear(), approvedCard.getOwner(), cvc);
        paymentPage.buyCredit(newCard);
        paymentPage.cvcError();
    }

    @Test
    public void buyFourDigitsCvcPaymentCreditGate() {
        var cardInfo = DataHelper.getFourDigitsCvv();
        PaymentPage.buy(cardInfo);
        PaymentPage.checkValueCvv("123");
        PaymentPage.buyCredit(cardInfo);
        PaymentPage.checkValueCvv("123");
    }
}

