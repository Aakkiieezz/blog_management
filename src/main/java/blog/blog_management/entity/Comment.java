package blog.blog_management.entity;

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
@Table(name = "Comment")
public class Comment
{
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CommentSEQ")
    @SequenceGenerator(name = "CommentSEQ", sequenceName = "CommentSEQ", initialValue = 1, allocationSize = 1)
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "content")
    @NotBlank(message = "Cannot be empty or blank")
    private String content;
    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne
    private Post post;
}