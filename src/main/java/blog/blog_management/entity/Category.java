package blog.blog_management.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
// @Table(name = "Category")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(name = "ID")
    private int id;
    // @Column(name = "Title")
    @NotEmpty(message = "Cannot be empty or blank")
    private String title;
}