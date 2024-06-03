package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Update implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idUpdate;
    private String title;
    private String content;
    private LocalDate date;

    public Update() {
    }

    public Update(int idUpdate) {
        this.idUpdate = idUpdate;
    }

    public Update(String title, String content, LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = date;
    }

    public Update(int idUpdate, String title, String content, LocalDate date) {
        this(title, content, date);
        this.idUpdate = idUpdate;
    }

    public int getIdUpdate() {
        return idUpdate;
    }

    public void setIdUpdate(int idUpdate) {
        this.idUpdate = idUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Update update = (Update) o;
        return idUpdate == update.idUpdate && Objects.equals(title, update.title) && Objects.equals(content, update.content) && Objects.equals(date, update.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUpdate, title, content, date);
    }

    @Override
    public String toString() {
        return "Update{" +
                "idUpdate=" + idUpdate +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", data=" + date +
                '}';
    }

}
