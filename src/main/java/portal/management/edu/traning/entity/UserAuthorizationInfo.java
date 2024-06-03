package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserAuthorizationInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String login;
    private String password;

    public UserAuthorizationInfo() {
    }

    public UserAuthorizationInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuthorizationInfo that = (UserAuthorizationInfo) o;
        return Objects.equals(login, that.login) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "UserAuthorizationInfo{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
