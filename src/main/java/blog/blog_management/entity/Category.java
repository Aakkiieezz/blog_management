package blog.blog_management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "Category")
public class Category
{
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CategorySEQ")
    @SequenceGenerator(name = "CategorySEQ", sequenceName = "CategorySEQ", initialValue = 1, allocationSize = 1)
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "title")
    @NotBlank(message = "Cannot be empty or blank")
    private String title;
    // @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    // private List<Post> posts = new ArrayList<>();
}