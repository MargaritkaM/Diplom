package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.Keys;

import java.nio.file.Files;
import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {

    private static SelenideElement buttonBuy = $x("//*[.='Купить']");
    private static SelenideElement buttonBuyCredit = $x("//*[text()='Купить в кредит']");
    private static SelenideElement numberOfCard = $x("//input[@placeholder='0000 0000 0000 0000']");
    private static SelenideElement month = $("input[placeholder='08']");
    private static SelenideElement year = $("input[placeholder='22']");
    private static ElementsCollection ownerSet = $$x("//*[@class='input__control']");
    private static SelenideElement owner = ownerSet.get(3);
    private static SelenideElement cvv = $("input[placeholder='999']");
    private static SelenideElement continueButton= $x("//*[text()='Продолжить']");

    private static SelenideElement emptyCardNumber = $(byText("Неверный формат"));

    private static SelenideElement emptyMonth = $(byText("Неверный формат"));

    private SelenideElement emptyYear = $(byText("Неверный формат"));

    private SelenideElement emptyOwner = $(byText("Поле обязательно для заполнения"));

    private SelenideElement emptyCVC = $(byText("Неверный формат"));

    private static SelenideElement sucMes = $(byText("Операция одобрена Банком."));
    private static SelenideElement errMes = $(byText("Ошибка! Банк отказал в проведении операции."));


    public static void buy(DataHelper.AuthInfo info) {
        buttonBuy.click();
        numberOfCard.setValue(info.getCardNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCodeCvc());
        continueButton.click();
    }
    public static void buyCredit(DataHelper.AuthInfo info) {
        buttonBuyCredit.click();
        numberOfCard.setValue(info.getCardNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        owner.setValue(info.getOwner());
        cvv.setValue(info.getCodeCvc());
        continueButton.click();
    }
    public void cleanStrings() {
        numberOfCard.doubleClick().sendKeys(Keys.DELETE);
        month.doubleClick().sendKeys(Keys.DELETE);
        year.doubleClick().sendKeys(Keys.DELETE);
        owner.doubleClick().sendKeys(Keys.DELETE);
        cvv.doubleClick().sendKeys(Keys.DELETE);
    }


    public static void payToButton() {
        buttonBuy.click();
    }

    public static void payInCreditToButton() {
       buttonBuyCredit.click();
    }

    public static void successfulWay() {
        sucMes.shouldBe(Condition.visible, Duration.ofSeconds(30));
    }

    public static void unSuccessfulWay() {
        errMes.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    public static void cardNumberError() {
        numberOfCard.shouldBe(Condition.visible);
    }

    public static void monthError() {
        month.shouldBe(Condition.visible);
    }

    public static void yearError() {
        year.shouldBe(Condition.visible);
    }

    public static void ownerError() {
        owner.shouldBe(Condition.visible);
    }

    public static void cvcError() {
        cvv.shouldBe(Condition.visible);
    }
}
