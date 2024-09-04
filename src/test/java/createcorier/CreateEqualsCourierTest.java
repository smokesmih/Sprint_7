package createcorier;

import client.Courier;
import client.CourierMetods;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

@DisplayName("Проверка создания двух одинаковых курьеров")
public class CreateEqualsCourierTest {
    private final Courier COURIER = new Courier("Kotik303", "777", "Vasia");

    @Test
    @DisplayName("Проверка создания двух одинаковых курьеров")
    public void CreateEqualsCourierTest() {
        CourierMetods.createCourier(COURIER);
        Response response = CourierMetods.createCourier(COURIER);

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
