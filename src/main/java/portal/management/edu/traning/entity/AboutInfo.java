package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class AboutInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idContent;
    private String content;

    public AboutInfo() {
    }

    public AboutInfo(int idContent) {
        this.idContent = idContent;
    }

    public AboutInfo(String content) {
        this.content = content;
    }

    public AboutInfo(int idContent, String content) {
        this.idContent = idContent;
        this.content = content;
    }

    public int getIdContent() {
        return idContent;
    }

    public void setIdContent(int idContent) {
        this.idContent = idContent;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AboutInfo aboutInfo = (AboutInfo) o;
        return idContent == aboutInfo.idContent && Objects.equals(content, aboutInfo.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContent, content);
    }

    @Override
    public String toString() {
        return "AboutInfo{" +
                "idContent=" + idContent +
                ", content='" + content + '\'' +
                '}';
    }

}
