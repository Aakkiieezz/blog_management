package blog.blog_management.payload;

import lombok.Data;

@Data
public class UserDto
{
    private int id;
    private String name;
    private String email;
    private String password;
}
