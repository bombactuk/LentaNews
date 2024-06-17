package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idComment;
    private String content;
    private LocalDate datePost;
    private String nameUser;
    private int idUser;
    private int idNews;

    public Comment(){}

    public Comment(int idComment, String content,LocalDate datePost, int idUser, int idNews){

        this.idComment = idComment;
        this.content = content;
        this.datePost = datePost;
        this.idUser = idUser;
        this.idNews = idNews;

    }

    public Comment(int idComment, String content, LocalDate datePost, int idUser, String nameUser){

        this.idComment = idComment;
        this.content = content;
        this.datePost = datePost;
        this.idUser = idUser;
        this.nameUser = nameUser;

    }

    public Comment(int idComment){
        this.idComment = idComment;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdNews() {
        return idNews;
    }

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public LocalDate getDatePost() {
        return datePost;
    }

    public void setDatePost(LocalDate datePost) {
        this.datePost = datePost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return idComment == comment.idComment && idUser == comment.idUser && idNews == comment.idNews && Objects.equals(content, comment.content) && Objects.equals(datePost, comment.datePost) && Objects.equals(nameUser, comment.nameUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComment, content, datePost, nameUser, idUser, idNews);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "idComment=" + idComment +
                ", content='" + content + '\'' +
                ", datePost=" + datePost +
                ", nameUser='" + nameUser + '\'' +
                ", idUser=" + idUser +
                ", idNews=" + idNews +
                '}';
    }

}
