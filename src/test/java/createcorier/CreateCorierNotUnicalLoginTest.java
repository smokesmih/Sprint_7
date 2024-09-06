package createcorier;

import client.Courier;
import client.CourierMetods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

@DisplayName("Проверка создания курьера с неуникальным логином")
public class CreateCorierNotUnicalLoginTest {

    private static final Courier COURIER_WITH_SAME_LOGIN = new Courier("BoSS008", "4321", "Leha");
    private static final Courier COURIER = new Courier("BoSS008", "1234W", "Aleksey");

    @Test
    @DisplayName("Проверка создания курьера с неуникальным логином")
    public void createCorierNotUnicalLogin() {
        CourierMetods.createCourier(COURIER);
        Response response = CourierMetods.createCourier(COURIER_WITH_SAME_LOGIN);
        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(409, statusCode);
        String bodyMessage = response.then().extract().jsonPath().getString("message");
        // Ожидаемый результат из доки, отличается от фактического
        Assert.assertEquals("Этот логин уже используется. Попробуйте другой.", bodyMessage);

    }
    @After
    public void deleteTestCourier() {
        CourierMetods.deleteCourier(COURIER);
    }
}
