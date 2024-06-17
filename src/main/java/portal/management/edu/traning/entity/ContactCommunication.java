package portal.management.edu.traning.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class ContactCommunication implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idContact;
    private String img;
    private String link;
    private String status;
    private int idAdmin;

    public ContactCommunication() {
    }

    public ContactCommunication(String img, String link, String status) {
        this.img = img;
        this.link = link;
        this.status = status;
    }

    public ContactCommunication(String img, String link, String status, int idAdmin) {
        this(img, link, status);
        this.idAdmin = idAdmin;
    }

    public ContactCommunication(int idContact, String img, String link, String status) {
        this(img, link, status);
        this.idContact = idContact;
    }

    public ContactCommunication(int idContact) {
        this.idContact = idContact;
    }

    public ContactCommunication(String img, String link) {
        this.img = img;
        this.link = link;
    }

    public int getIdContact() {
        return idContact;
    }

    public void setIdContact(int idContact) {
        this.idContact = idContact;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        ContactCommunication that = (ContactCommunication) o;
        return idContact == that.idContact && idAdmin == that.idAdmin && Objects.equals(img, that.img) && Objects.equals(link, that.link) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContact, img, link, status, idAdmin);
    }

    @Override
    public String toString() {
        return "ContactCommunication{" +
                "idContact=" + idContact +
                ", img='" + img + '\'' +
                ", link='" + link + '\'' +
                ", status='" + status + '\'' +
                ", idAdmin=" + idAdmin +
                '}';
    }

}
