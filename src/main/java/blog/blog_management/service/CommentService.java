package blog.blog_management.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import blog.blog_management.entity.Comment;
import blog.blog_management.entity.Post;
import blog.blog_management.exception.ResourceNotFoundException;
import blog.blog_management.payload.CommentDto;
import blog.blog_management.payload.EntityDtoConversion;
import blog.blog_management.repository.CommentRepository;
import blog.blog_management.repository.PostRepository;

@Service
public class CommentService
{
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private CommentRepository commentRepo;
    @Autowired
    private EntityDtoConversion converter;

    public CommentDto createComment(CommentDto commentDto, int postId)
    {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = converter.dtoToComment(commentDto);
        comment.setPost(post);
        commentRepo.save(comment);
        commentDto = converter.commentToDto(comment);
        return commentDto;
    }

    public List<CommentDto> getCommentsByPost(int postId)
    {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        List<Comment> comments = commentRepo.findByPost(post);
        List<CommentDto> commentDtos = comments.stream().map((comment) -> this.converter.commentToDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    public CommentDto updateComment(@Valid CommentDto commentDto, int commentId)
    {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        comment.setContent(commentDto.getContent());
        commentRepo.save(comment);
        commentDto = converter.commentToDto(comment);
        return commentDto;
    }

    public CommentDto deleteComment(int commentId)
    {
        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        commentRepo.delete(comment);
        CommentDto commentDto = converter.commentToDto(comment);
        return commentDto;
    }

    public List<CommentDto> deleteCommentsByPost(int postId)
    {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        // to check if we can use deleteByPost instead of next 2 lines
        List<Comment> comments = commentRepo.findByPost(post);
        commentRepo.deleteAll(comments);
        List<CommentDto> commentDtos = comments.stream().map((comment) -> this.converter.commentToDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }
}
