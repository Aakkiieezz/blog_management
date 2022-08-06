package blog.blog_management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import blog.blog_management.entity.Category;
import blog.blog_management.exception.ResourceNotFoundException;
import blog.blog_management.repository.CategoryRepository;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepo;

    public Category createCategory(Category category)
    {
        categoryRepo.save(category);
        return category;
    }

    public Category getCategory(int id)
    {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        return category;
    }

    public List<Category> getCategories()
    {
        List<Category> categories = categoryRepo.findAll();
        return categories;
    }

    public Category updateCategory(Category categoryNew, int id)
    {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        category.setTitle(categoryNew.getTitle());
        categoryRepo.save(category);
        return category;
    }

    public Category deleteCategory(int id)
    {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        categoryRepo.delete(category);
        return category;
    }
}
