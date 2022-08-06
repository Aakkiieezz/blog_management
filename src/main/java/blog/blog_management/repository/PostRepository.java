package blog.blog_management.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import blog.blog_management.entity.Category;
import blog.blog_management.entity.Post;
import blog.blog_management.entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>
{
    // Derived Query Method by Spring's data JPA repository
    List<Post> findByUser(User user);

    // Derived Query Method by Spring's data JPA repository
    List<Post> findByCategory(Category category);
}
