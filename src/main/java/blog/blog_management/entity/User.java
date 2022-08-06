package blog.blog_management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User
{
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    @NotBlank(message = "Cannot be empty or blank")
    private String name;
    @Column(name = "email")
    @NotBlank(message = "Cannot be empty or blank")
    @Email(message = "Incorrect format")
    private String email;
    @Column(name = "password")
    @NotBlank(message = "Cannot be empty or blank")
    private String password;
    // @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // private List<Post> posts = new ArrayList<>();
}