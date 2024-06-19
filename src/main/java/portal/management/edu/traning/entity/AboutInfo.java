package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class AboutInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idAbout;
    private String content;
    private LocalDate date_post;
    private String status;
    private int idAdmin;

    public AboutInfo() {
    }

    public AboutInfo(int idAbout) {
        this.idAbout = idAbout;
    }

    public AboutInfo(int idAbout, String content) {
        this.idAbout = idAbout;
        this.content = content;
    }

    public AboutInfo(int idAbout, String content, LocalDate date_post, String status, int idAdmin) {
        this(idAbout, content);
        this.date_post = date_post;
        this.status = status;
        this.idAdmin = idAdmin;
    }

    public int getIdAbout() {
        return idAbout;
    }

    public void setIdAbout(int idAbout) {
        this.idAbout = idAbout;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate_post() {
        return date_post;
    }

    public void setDate_post(LocalDate date_post) {
        this.date_post = date_post;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AboutInfo aboutInfo = (AboutInfo) o;
        return idAbout == aboutInfo.idAbout && idAdmin == aboutInfo.idAdmin && Objects.equals(content, aboutInfo.content) && Objects.equals(date_post, aboutInfo.date_post) && Objects.equals(status, aboutInfo.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAbout, content, date_post, status, idAdmin);
    }

    @Override
    public String toString() {
        return "AboutInfo{" +
                "idContent=" + idAbout +
                ", content='" + content + '\'' +
                ", date_post=" + date_post +
                ", status='" + status + '\'' +
                ", idAdmin=" + idAdmin +
                '}';
    }

}
