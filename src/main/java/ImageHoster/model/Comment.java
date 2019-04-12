package ImageHoster.model;/* Create by Mansi Elhance */

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "text")
    private String text;

    @Column(name = "createdDate")
    public LocalDate createdDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    public Integer getId() {
        return id;
    }

    public void setId(String text) {
        this.text = text;
    }

    public String gettext() { return text;  }

    public void settext(String text) {
        this.text = text;
    }

    public LocalDate getCreatedDate(LocalDate createdDate){return createdDate;}

    public void setCreatedDate(LocalDate createdDate){
        this.createdDate = createdDate; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user;  }

    public Image getImage() { return image; }

    public void setImage(Image image) { this.image = image; }



}
