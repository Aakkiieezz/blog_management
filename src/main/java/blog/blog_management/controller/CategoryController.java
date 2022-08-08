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
import blog.blog_management.payload.CategoryDto;
import blog.blog_management.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto categoryDtoNew)
    {
        CategoryDto categoryDto = categoryService.createCategory(categoryDtoNew);
        String msg = "Category "+categoryDto.getId()+" created successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable int id)
    {
        CategoryDto categoryDto = categoryService.getCategory(id);
        return categoryDto;
    }

    @GetMapping("/")
    public List<CategoryDto> getCategories()
    {
        List<CategoryDto> categoryDtos = categoryService.getCategories();
        return categoryDtos;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody CategoryDto categoryDtoNew, @PathVariable int id)
    {
        CategoryDto categoryDto = categoryService.updateCategory(categoryDtoNew, id);
        String msg = "Category "+id+" updated successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", categoryDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id)
    {
        CategoryDto categoryDto = categoryService.deleteCategory(id);
        String msg = "Category "+id+" deleted successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", categoryDto), HttpStatus.OK);
    }
}
