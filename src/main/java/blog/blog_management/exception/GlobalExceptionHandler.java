package blog.blog_management.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> notFound(ResourceNotFoundException ex)
    {
        String msg = ex.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> wrongNamea(MethodArgumentNotValidException exe)
    {
        Map<String, String> wrongName = new HashMap<>();
        exe.getBindingResult().getAllErrors().forEach((error) ->
        {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            wrongName.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(wrongName, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
