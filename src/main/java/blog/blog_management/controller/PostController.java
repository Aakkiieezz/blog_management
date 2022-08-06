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
import blog.blog_management.entity.Post;
import blog.blog_management.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController
{
    @Autowired
    private PostService postService;

    @PostMapping("/users/{userId}/categories/{categoryId}/posts/")
    public ResponseEntity<?> createPost(@Valid @RequestBody Post postNew, @PathVariable int userId, @PathVariable int categoryId)
    {
        Post post = postService.createPost(postNew, userId, categoryId);
        String msg = "Post "+post.getId()+" created successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", post), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable int id)
    {
        Post post = postService.getPost(id);
        return post;
    }

    @GetMapping("/posts/")
    public List<Post> getPosts()
    {
        List<Post> posts = postService.getPosts();
        return posts;
    }

    @GetMapping("/users/{userId}/posts")
    public List<Post> getPostsByUser(@PathVariable int userId)
    {
        List<Post> posts = postService.getPostsByUser(userId);
        return posts;
    }

    @GetMapping("/categories/{categoryId}/posts")
    public List<Post> getPostsByCategory(@PathVariable int categoryId)
    {
        List<Post> posts = postService.getPostsByCategory(categoryId);
        return posts;
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@Valid @RequestBody Post postNew, @PathVariable int id)
    {
        Post post = postService.updatePost(postNew, id);
        String msg = "Post "+id+" updated successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", post), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id)
    {
        Post post = postService.deletePost(id);
        String msg = "Post "+id+" deleted successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", post), HttpStatus.OK);
    }
}
