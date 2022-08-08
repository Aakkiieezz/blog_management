package blog.blog_management.payload;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import blog.blog_management.entity.Category;
import blog.blog_management.entity.Post;
import blog.blog_management.entity.User;

@Component
public class EntityDtoConversion
{
    ModelMapper mapper = new ModelMapper();

    public UserDto userToDto(User user)
    {
        UserDto userDto = mapper.map(user, UserDto.class);
        return userDto;
    }

    public PostDto postToDto(Post post)
    {
        PostDto postDto = mapper.map(post, PostDto.class);
        return postDto;
    }

    public CategoryDto categoryToDto(Category category)
    {
        CategoryDto categoryDto = mapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    public User dtoToUser(UserDto userDto)
    {
        User user = mapper.map(userDto, User.class);
        return user;
    }

    public Post dtoToPost(PostDto postDto)
    {
        Post post = mapper.map(postDto, Post.class);
        return post;
    }

    public Category dtoToCategory(CategoryDto categoryDto)
    {
        Category category = mapper.map(categoryDto, Category.class);
        return category;
    }
}
