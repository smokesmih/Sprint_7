package orders;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

@DisplayName("Проверка получения списка заказов, с несуществующим Id")
public class GetOrdersTest {
    private static final String URL_SQOOT = "https://qa-scooter.praktikum-services.ru";
    private static final int FAKE_ID = 376870098;

    @Test
    @DisplayName("Проверка получения списка заказов")
    public void getOrderPositive() {
        Response response = given()
                .baseUri(URL_SQOOT)
                .get("/api/v1/orders");
        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(200, statusCode);

        Assert.assertNotNull(response.then().extract().body().jsonPath().get("orders"));
    }

    @Test
    @DisplayName("Проверка получения списка заказов, с несуществующим Id")
    public void getOrderWithFakeId() {
        Response response = given()
                .baseUri(URL_SQOOT)
                .header("Content-Type", "application/json")
                .get("/api/v1/orders?courierId=" + FAKE_ID);

        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(404, statusCode);

        String message = response.then().extract().body().jsonPath().get("message");
        Assert.assertEquals("Курьер с идентификатором " + FAKE_ID + " не найден", message);
    }
}
