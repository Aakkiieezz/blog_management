package blog.blog_management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import blog.blog_management.entity.Comment;
import blog.blog_management.entity.Post;

public interface CommentRepository extends JpaRepository<Comment, Integer>
{
    // Derived Query Methods by Spring's data JPA repository
    List<Comment> findByPost(Post post);
}
