package blog.blog_management.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import blog.blog_management.entity.Category;
import blog.blog_management.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category categoryNew)
    {
        Category category = categoryService.createCategory(categoryNew);
        String msg = "Category "+category.getId()+" created successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Date", category), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable int id)
    {
        Category category = categoryService.getCategory(id);
        return category;
    }

    @GetMapping("/")
    public List<Category> getCategories()
    {
        List<Category> categories = categoryService.getCategories();
        return categories;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody Category categoryNew, @PathVariable int id)
    {
        Category category = categoryService.updateCategory(categoryNew, id);
        String msg = "Category "+id+" updated successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id)
    {
        Category category = categoryService.deleteCategory(id);
        String msg = "Category "+id+" deleted successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", category), HttpStatus.OK);
    }
}
