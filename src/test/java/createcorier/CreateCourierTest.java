package createcorier;

import client.Courier;
import client.CourierMetods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

@DisplayName("Позитивная проверка на создание клиента")
public class CreateCourierTest {


    private static final Courier COURIER = new Courier("SnowwwW2034", "1234", "Pasha");

    @Test
    @DisplayName("Позитивная проверка на создание клиента")
    public void createCorierPositive() {
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



