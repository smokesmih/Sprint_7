package logincorier;

import client.Courier;
import client.CourierMetods;
import client.Credentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

@DisplayName("Проверка логина, c неверным паролем")
public class LoginWithWrongPasswordTest {
    private static final Courier COURIER = new Courier("Sup146", "12364", "Kolia");

    @Test
    @DisplayName("Проверка логина, c неверным паролем")
    public void positiveLoginTest() {
        CourierMetods.createCourier(COURIER);
        Response response = CourierMetods.loginCourier(Credentials.fromCourierWithWrongPassword(COURIER));

        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(404, statusCode);
        String body = response.then().extract().jsonPath().get("message");
        Assert.assertEquals("Учетная запись не найдена", body);
    }
    @After
    public void deleteTestCourier() {
        CourierMetods.deleteCourier(COURIER);
    }
}
