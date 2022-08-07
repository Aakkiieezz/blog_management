package blog.blog_management.payload;

import java.util.List;
import blog.blog_management.entity.Post;
import lombok.Data;

@Data
public class PostResponse
{
    private List<Post> content;
    private int pageNummber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}
