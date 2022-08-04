package blog.blog_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import blog.blog_management.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>
{
}