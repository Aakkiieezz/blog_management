package blog.blog_management.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "Post")
public class Post
{
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PostSEQ")
    @SequenceGenerator(name = "PostSEQ", sequenceName = "PostSEQ", initialValue = 1, allocationSize = 1)
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "title")
    @NotBlank(message = "Cannot be empty or blank")
    private String title;
    @Column(name = "content")
    @NotBlank(message = "Cannot be empty or blank")
    private String content;
    @Column(name = "date")
    private Date date;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    @JoinColumn(name = "category_id", nullable = false)
    @ManyToOne
    private Category category;
    // @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    // private Set<Comment> comments = new HashSet<>();
}
