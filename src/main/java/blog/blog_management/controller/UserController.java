package blog.blog_management.controller;

import java.util.List;
import java.util.Map;
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
    private UserService service;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody User userNew)
    {
        User user = service.createUser(userNew);
        String msg = "User "+user.getId()+" created successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id)
    {
        return service.getUser(id);
    }

    @GetMapping("/")
    public List<User> getUsers()
    {
        return service.getUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User userNew, @PathVariable int id)
    {
        User user = service.updateUser(userNew, id);
        String msg = "User "+id+" updated successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", user), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id)
    {
        User user = service.deleteUser(id);
        String msg = "User "+id+" deleted successfully";
        return new ResponseEntity<>(Map.of("Message", msg, "Data", user), HttpStatus.CREATED);
    }
}
