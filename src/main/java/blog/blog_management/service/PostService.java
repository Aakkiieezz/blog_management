package blog.blog_management.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
import blog.blog_management.payload.EntityDtoConversion;
import blog.blog_management.payload.PostDto;
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
    @Autowired
    private EntityDtoConversion converter;

    public PostDto createPost(PostDto postDto, int userId, int categoryId)
    {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        Post post = converter.dtoToPost(postDto);
        post.setCategory(category);
        post.setUser(user);
        post.setDate(new Date());
        postRepo.save(post);
        postDto = converter.postToDto(post);
        return postDto;
    }

    public PostDto getPost(int id)
    {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        PostDto postDto = converter.postToDto(post);
        return postDto;
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
        List<PostDto> postDtos = posts.stream().map((post) -> converter.postToDto(post)).collect(Collectors.toList());
        PostResponse response = new PostResponse();
        response.setContent(postDtos);
        response.setPageNummber(pagePost.getNumber());
        response.setPageSize(pagePost.getSize());
        response.setTotalElements(pagePost.getNumberOfElements());
        response.setTotalPages(pagePost.getTotalPages());
        response.setLastPage(pagePost.isLast());
        return response;
    }

    public List<PostDto> getPostsByCategory(int category_id)
    {
        Category category = categoryRepo.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", category_id));
        List<Post> posts = postRepo.findByCategory(category);
        List<PostDto> postDtos = posts.stream().map((post) -> this.converter.postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    public List<PostDto> getPostsByUser(int user_id)
    {
        User user = userRepo.findById(user_id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", user_id));
        List<Post> posts = postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> this.converter.postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    public List<PostDto> getPostsByTitleSearch(String keyword)
    {
        List<Post> posts = postRepo.findByTitleContaining(keyword);
        List<PostDto> postDtos = posts.stream().map((post) -> this.converter.postToDto(post)).collect(Collectors.toList());
        return postDtos;
    }

    public PostDto updatePost(PostDto postNew, int id)
    {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        post.setTitle(postNew.getTitle());
        post.setContent(postNew.getContent());
        postRepo.save(post);
        PostDto postDto = converter.postToDto(post);
        return postDto;
    }

    public PostDto deletePost(int id)
    {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", id));
        postRepo.delete(post);
        PostDto postDto = converter.postToDto(post);
        return postDto;
    }
}