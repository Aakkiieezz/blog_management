package blog.blog_management.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import blog.blog_management.entity.Category;
import blog.blog_management.exception.ResourceNotFoundException;
import blog.blog_management.payload.CategoryDto;
import blog.blog_management.payload.EntityDtoConversion;
import blog.blog_management.repository.CategoryRepository;

@Service
public class CategoryService
{
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private EntityDtoConversion converter;

    public CategoryDto createCategory(CategoryDto categoryDto)
    {
        Category category = converter.dtoToCategory(categoryDto);
        categoryRepo.save(category);
        categoryDto = converter.categoryToDto(category);
        return categoryDto;
    }

    public CategoryDto getCategory(int id)
    {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        CategoryDto categoryDto = converter.categoryToDto(category);
        return categoryDto;
    }

    public List<CategoryDto> getCategories()
    {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map(category -> this.converter.categoryToDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    public CategoryDto updateCategory(CategoryDto categoryDto, int id)
    {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        categoryDto.setId(id);
        category = converter.dtoToCategory(categoryDto);
        categoryRepo.save(category);
        return categoryDto;
    }

    public CategoryDto deleteCategory(int id)
    {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", id));
        CategoryDto categoryDto = converter.categoryToDto(category);
        categoryRepo.delete(category);
        return categoryDto;
    }
}
