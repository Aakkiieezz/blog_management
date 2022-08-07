package blog.blog_management.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import blog.blog_management.entity.Category;
import blog.blog_management.entity.Post;
import blog.blog_management.entity.User;
import blog.blog_management.exception.ResourceNotFoundException;
import blog.blog_management.payload.PostResponse;
import blog.blog_management.repository.CategoryRepository;
import blog.blog_management.repository.PostRepository;
import blog.blog_management.repository.UserRepository;

@Service
public class PostService
{
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    public Post createPost(Post post, int userId, int categoryId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        postRepo.save(post);
        return post;
    }

    public Post getPost(int id)
    {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        return post;
    }

    public PostResponse getPosts(int pageNumber, int pageSize, String sortBy, String sortDir)
    {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc") || sortDir.equalsIgnoreCase("ascending"))
            sort = Sort.by(sortBy).ascending();
        else if(sortDir.equalsIgnoreCase("desc") || sortDir.equalsIgnoreCase("descending"))
            sort = Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepo.findAll(page);
        List<Post> posts = pagePost.getContent();
        PostResponse response = new PostResponse();
        response.setContent(posts);
        response.setPageNummber(pagePost.getNumber());
        response.setPageSize(pagePost.getSize());
        response.setTotalElements(pagePost.getNumberOfElements());
        response.setTotalPages(pagePost.getTotalPages());
        response.setLastPage(pagePost.isLast());
        return response;
    }

    public List<Post> getPostsByCategory(int category_id)
    {
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", category_id));
        List<Post> posts = postRepo.findByCategory(category);
        return posts;
    }

    public List<Post> getPostsByUser(int user_id)
    {
        User user = userRepo.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id));
        List<Post> posts = postRepo.findByUser(user);
        return posts;
    }

    public Post updatePost(Post postNew, int id)
    {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        post.setTitle(postNew.getTitle());
        post.setContent(postNew.getContent());
        postRepo.save(post);
        return post;
    }

    public Post deletePost(int id)
    {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepo.delete(post);
        return post;
    }
}
