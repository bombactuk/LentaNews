package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class News implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idNews;
    private String title;
    private String shortDescription;
    private String content;
    private LocalDate postDate;
    private int idAdmin;
    private int idCategories;

    public News() {
    }

    public News(int idNews){

        this.idNews = idNews;

    }

    public News(int idNews, String title, String shortDescription, LocalDate postDate){
        this.idNews = idNews;
        this.title = title;
        this.shortDescription = shortDescription;
        this.postDate = postDate;
    }

    public News(int idNews, String title, String shortDescription, String content,
                LocalDate postDate, int idAdmin, int idCategories) {

        this(idNews, title, shortDescription, postDate);
        this.content = content;
        this.idAdmin = idAdmin;
        this.idCategories = idCategories;

    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idUser) {
        this.idAdmin = idUser;
    }

    public int getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(int idCategories) {
        this.idCategories = idCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return idNews == news.idNews && idAdmin == news.idAdmin && idCategories == news.idCategories && Objects.equals(title, news.title) && Objects.equals(shortDescription, news.shortDescription) && Objects.equals(content, news.content) && Objects.equals(postDate, news.postDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNews, title, shortDescription, content, postDate, idAdmin, idCategories);
    }

    @Override
    public String toString() {
        return "News{" +
                "idNews=" + idNews +
                ", title='" + title + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", content='" + content + '\'' +
                ", postDate=" + postDate +
                ", idUser=" + idAdmin +
                ", idCategories=" + idCategories +
                '}';
    }

}
