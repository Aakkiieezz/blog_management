package blog.blog_management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
// @Table(name = "User")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    // @Column(name = "UserName")
    @NotEmpty(message = "Cannot be empty or blank")
    private String name;
    // @Column(name = "UserEmail")
    @Email(message = "Incorrect format")
    @NotEmpty(message = "Cannot be empty or blank")
    private String email;
    // @Column(name = "UserPassword")
    @NotEmpty(message = "Cannot be empty or blank")
    private String password;
}