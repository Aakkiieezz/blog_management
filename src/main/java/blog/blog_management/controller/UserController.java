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
import blog.blog_management.payload.UserDto;
import blog.blog_management.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDtoNew)
    {
        UserDto userDto = userService.createUser(userDtoNew);
        String msg = "User "+userDto.getId()+" created successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", userDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable int id)
    {
        UserDto userDto = userService.getUser(id);
        return userDto;
    }

    @GetMapping("/")
    public List<UserDto> getUsers()
    {
        List<UserDto> userDtos = userService.getUsers();
        return userDtos;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDto userDtoNew, @PathVariable int id)
    {
        UserDto userDto = userService.updateUser(userDtoNew, id);
        String msg = "User "+id+" updated successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", userDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id)
    {
        UserDto userDto = userService.deleteUser(id);
        String msg = "User "+id+" deleted successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", userDto), HttpStatus.OK);
    }
}
