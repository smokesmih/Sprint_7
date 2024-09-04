package client;

import io.qameta.allure.Step;

public class Credentials {
    private final String login;
    private final String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    private Credentials(String login, String password) {
        this.password = password;
        this.login = login;
    }
    @Step("Получение  данных для входа")
    public static Credentials fromCourier(Courier courier) {
        return new Credentials(courier.getLogin(), courier.getPassword());
    }
    @Step("Получение  данных для входа, без логина")
    public static Credentials fromCourierWithoutLogin(Courier courier) {
        return new Credentials(null, courier.getPassword());
    }
    @Step("Получение  данных для входа, без пароля")
    public static Credentials fromCourierWithoutPasswrod(Courier courier) {
        return new Credentials(courier.getLogin(),null);
    }
    @Step("Получение  данных для входа, с неверным логином")
    public static Credentials fromCourierWithNotRealUser(Courier courier) {
        return new Credentials(courier.getLogin()+"ovich1340",courier.getPassword());
    }
    @Step("Получение  данных для входа, с неверным паролем")
    public static Credentials fromCourierWithWrongPassword(Courier courier) {
        return new Credentials(courier.getLogin(),courier.getPassword()+"18");
    }

}
