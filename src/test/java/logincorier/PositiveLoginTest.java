package logincorier;

import client.Courier;
import client.CourierMetods;
import client.Credentials;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

@DisplayName("Позитивная проверка логина")
public class PositiveLoginTest {

    private static final Courier COURIER = new Courier("SunBoysS4s", "1234", "Oleg");

    @Test
    @DisplayName("Позитивная проверка логина")
    public void positiveLoginTest() {
        CourierMetods.createCourier(COURIER);
        Response response = CourierMetods.loginCourier(Credentials.fromCourier(COURIER));
        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(200, statusCode);
        int body = response.then().extract().jsonPath().get("id");
        Assert.assertNotNull(body);
    }

    @After
    public void deleteTestCourier() {
        CourierMetods.deleteCourier(COURIER);
    }
}