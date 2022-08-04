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
import blog.blog_management.entity.User;
import blog.blog_management.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody User userNew)
    {
        User user = userService.createUser(userNew);
        String msg = "User "+user.getId()+" created successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id)
    {
        User user = userService.getUser(id);
        return user;
    }

    @GetMapping("/")
    public List<User> getUsers()
    {
        List<User> users = userService.getUsers();
        return users;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User userNew, @PathVariable int id)
    {
        User user = userService.updateUser(userNew, id);
        String msg = "User "+id+" updated successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id)
    {
        User user = userService.deleteUser(id);
        String msg = "User "+id+" deleted successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", user), HttpStatus.OK);
    }
}
