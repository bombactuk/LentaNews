package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class NewsCategories implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idCategories;
    private String type;

    public NewsCategories(){
    }

    public NewsCategories(int idCategories, String type){
        this.idCategories = idCategories;
        this.type = type;
    }

    public int getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(int idCategories) {
        this.idCategories = idCategories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsCategories that = (NewsCategories) o;
        return idCategories == that.idCategories && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategories, type);
    }

    @Override
    public String toString() {
        return "NewsCategories{" +
                "idCategories=" + idCategories +
                ", type='" + type + '\'' +
                '}';
    }

}
