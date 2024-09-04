package createcorier;

import client.Courier;
import client.CourierMetods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

@DisplayName("Проверка создания курьера без поля FistName")
public class CreateCorierWihoutFistNameTest {

    private static final Courier COURIER = new Courier("Cowboy2030", "234");

    @Test
    @DisplayName("Проверка создания курьера без поля FistName")
    public void createCorierWihoutFistName() {
        Response response = CourierMetods.createCourier(COURIER);
        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(201, statusCode);
        boolean body = response.then().extract().jsonPath().get("ok");
        Assert.assertTrue(body);
    }
    @After
    public void deleteTestCourier() {
        CourierMetods.deleteCourier(COURIER);
    }
}
