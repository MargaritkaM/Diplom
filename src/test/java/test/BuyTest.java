package test;

import data.DataHelper;
import data.SqlHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static java.awt.SystemColor.info;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyTest {

    @BeforeEach
    void setup() {
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
}
