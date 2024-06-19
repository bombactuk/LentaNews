package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idUser;
    private String role;
    private String token;

    private int idInfoUser;

    public User() {
    }

    public User(int idUser) {
        this.idUser = idUser;
    }

    public User(String token) {
        this.token = token;
    }

    public User(int idUser, String role, String token) {
        this.idUser = idUser;
        this.role = role;
        this.token = token;
    }

    public User(int idUser, String role) {
        this.idUser = idUser;
        this.role = role;
    }

    public User(int idUser, int idInfoUser) {
        this.idUser = idUser;
        this.idInfoUser = idInfoUser;
    }

    public int getIdInfoUser() {
        return idInfoUser;
    }

    public void setIdInfoUser(int idInfoUser) {
        this.idInfoUser = idInfoUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return idUser == user.idUser && idInfoUser == user.idInfoUser && Objects.equals(role, user.role) && Objects.equals(token, user.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, role, token, idInfoUser);
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", role='" + role + '\'' +
                ", token='" + token + '\'' +
                ", idInfoUser=" + idInfoUser +
                '}';
    }

}
