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
import blog.blog_management.payload.CommentDto;
import blog.blog_management.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentDto commentDtoNew, @PathVariable int postId)
    {
        CommentDto commentDto = commentService.createComment(commentDtoNew, postId);
        String msg = "Comment "+commentDto.getId()+" created successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPost(@PathVariable int postId)
    {
        List<CommentDto> commentDtos = commentService.getCommentsByPost(postId);
        return commentDtos;
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<?> updateComment(@Valid @RequestBody CommentDto commentDtoNew, @PathVariable int commentId)
    {
        CommentDto commentDto = commentService.updateComment(commentDtoNew, commentId);
        String msg = "Comment "+commentDto.getId()+" updated successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId)
    {
        CommentDto commentDto = commentService.deleteComment(commentId);
        String msg = "Comment "+commentDto.getId()+" deleted successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", commentDto), HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments")
    public ResponseEntity<?> deleteCommentsByPost(@PathVariable int postId)
    {
        List<CommentDto> commentDto = commentService.deleteCommentsByPost(postId);
        String msg = "All comments of post "+postId+" are deleted successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", commentDto), HttpStatus.OK);
    }
}
