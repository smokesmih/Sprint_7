package logincorier;

import client.Courier;
import client.CourierMetods;
import client.Credentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

@DisplayName("Проверка логина, без заполнения обязательного поля Login")
public class LoginWithoutLoginTest {
    private static final Courier COURIER = new Courier("SunBoychik2536", "1234", "Oleg");

    @Test
    @DisplayName("Проверка логина, без заполнения обязательного поля Login")
    public void positiveLoginTest() {
        CourierMetods.createCourier(COURIER);
        Response response = CourierMetods.loginCourier(Credentials.fromCourierWithoutLogin(COURIER));
        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(400, statusCode);
        String body = response.then().extract().jsonPath().get("message");
        Assert.assertEquals("Недостаточно данных для входа", body);
    }
    @After
    public void deleteTestCourier() {
        CourierMetods.deleteCourier(COURIER);
    }
}