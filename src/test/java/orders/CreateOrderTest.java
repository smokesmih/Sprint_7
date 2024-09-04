package orders;

import client.Order;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

@RunWith(Parameterized.class)
@DisplayName("Проверка создания заказа с различными вариациями цвета")
public class CreateOrderTest {

    private String[] color;
    private static final String URL_SCOOTER = "https://qa-scooter.praktikum-services.ru";

    public CreateOrderTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GRAY"}},
                {new String[]{"BLACK", "GRAY"}},
                {new String[]{}}
        };
    }

    @Test
    @DisplayName("Проверка создания заказа с различными вариациями цвета")
    public  void  createOrderTest() {
        Order order  = new Order("Кот", "Бегемот", "Пушкина 12", "8", "+7 800 355 35 35", 5, "2024-09-06", "КОммент",  color);
      Response response =   given()
                .baseUri(URL_SCOOTER)
                .body(order)
                .header("Content-Type", "application/json")
                .post("/api/v1/orders");
        int statusCode = response.then().extract().statusCode();
        Assert.assertEquals(201, statusCode);
        int body = response.then().extract().jsonPath().get("track");
        Assert.assertNotNull(body);
    }

    /*@After
    после тоже стоит удалить тестовый заказ*/
}
