package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class UserInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private LocalDate birthday;
    private String country;

    public UserInfo() {
    }

    public UserInfo(String name, LocalDate birthday, String country) {
        this.name = name;
        this.birthday = birthday;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(name, userInfo.name) && Objects.equals(birthday, userInfo.birthday) && Objects.equals(country, userInfo.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday, country);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", country='" + country + '\'' +
                '}';
    }

}
