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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import blog.blog_management.config.AppConstants;
import blog.blog_management.payload.PostDto;
import blog.blog_management.payload.PostResponse;
import blog.blog_management.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController
{
    @Autowired
    private PostService postService;

    @PostMapping("/users/{userId}/categories/{categoryId}/posts/")
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDtoNew, @PathVariable int userId, @PathVariable int categoryId)
    {
        PostDto postDto = postService.createPost(postDtoNew, userId, categoryId);
        String msg = "Post "+postDto.getId()+" created successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", postDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}")
    public PostDto getPost(@PathVariable int id)
    {
        PostDto postDto = postService.getPost(id);
        return postDto;
    }

    @GetMapping("/posts/")
    public PostResponse getPosts(@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
    {
        PostResponse response = postService.getPosts(pageNumber, pageSize, sortBy, sortDir);
        return response;
    }

    @GetMapping("/categories/{categoryId}/posts")
    public List<PostDto> getPostsByCategory(@PathVariable int categoryId)
    {
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return postDtos;
    }

    @GetMapping("/users/{userId}/posts")
    public List<PostDto> getPostsByUser(@PathVariable int userId)
    {
        List<PostDto> postDtos = postService.getPostsByUser(userId);
        return postDtos;
    }

    @GetMapping("/posts/search/{keyword}")
    public List<PostDto> getPostsByTitleSearch(@PathVariable String keyword)
    {
        List<PostDto> postDtos = postService.getPostsByTitleSearch(keyword);
        return postDtos;
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<?> updatePost(@Valid @RequestBody PostDto postDtoNew, @PathVariable int id)
    {
        PostDto postDto = postService.updatePost(postDtoNew, id);
        String msg = "Post "+id+" updated successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", postDto), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable int id)
    {
        PostDto postDto = postService.deletePost(id);
        String msg = "Post "+id+" deleted successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", postDto), HttpStatus.OK);
    }
}
