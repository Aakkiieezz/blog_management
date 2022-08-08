package blog.blog_management.payload;

import lombok.Data;

@Data
public class PostDto
{
    private int id;
    private String title;
    private String content;
}
