package client;


import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.*;

public class CourierMetods {
    private static final String URL_SCOOTER = "https://qa-scooter.praktikum-services.ru";

    @Step("Создание курьера")
    public static Response createCourier(Courier courier) {
        return   given()
                .baseUri(URL_SCOOTER)
                .body(courier)
                .header("Content-Type", "application/json")
                .post("/api/v1/courier");

    }
    @Step("Логин курьера в системе")
    public static Response login_Courier(Credentials credentials) {
        return   given()
                .baseUri(URL_SCOOTER)
                .body(credentials)
                .header("Content-Type", "application/json")
                .post("/api/v1/courier/login");

    }
    @Step("Удаление курьера")
    public static  void deleteCourier(Courier courier) {
    int id_courier = login_Courier(Credentials.fromCourier(courier)).then().extract().body().jsonPath().get("id");
          given()
                .baseUri(URL_SCOOTER)
                .header("Content-Type", "application/json")
                .delete("/api/v1/courier/" + id_courier);

    }

    }



