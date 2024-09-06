package client;

import java.util.Optional;

public class Courier {
    private final String login;
    private final String password;
    private final String firstName;

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public Courier(String login, String password) {
        this.login = login;
        this.password = password;
        this.firstName = null;
    }

    public Courier(Optional<String> login, String password, String firstName) {
        this.login = null;
        this.password = password;
        this.firstName = firstName;
    }
    public Courier(String login, Optional<String>  password, String firstName) {
        this.login = login;
        this.password = null;
        this.firstName = firstName;
    }


}
