package createcorier;

import client.Courier;
import client.CourierMetods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

@DisplayName("Проверка создания курьера без поля Login")
public class CreateCorierWithoutLoginTest {
    private static final Courier COURIER = new Courier(Optional.ofNullable(null),"2317", "Неимя");

    @Test
    @DisplayName("Проверка создания курьера без поля Login")
    public void createCorierWithoutLogin() {
        Response response = CourierMetods.createCourier(COURIER);
        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(400, statusCode);
        String bodyMessage = response.then().extract().jsonPath().getString("message");
        Assert.assertEquals("Недостаточно данных для создания учетной записи", bodyMessage);
    }

}


