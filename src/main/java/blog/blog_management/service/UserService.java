package blog.blog_management.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import blog.blog_management.entity.User;
import blog.blog_management.exception.ResourceNotFoundException;
import blog.blog_management.repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    private UserRepository repo;

    public User createUser(User user)
    {
        repo.save(user);
        return user;
    }

    public User updateUser(User userNew, int id)
    {
        User user = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        user.setName(userNew.getName());
        user.setEmail(userNew.getEmail());
        user.setPassword(userNew.getPassword());
        repo.save(user);
        return user;
    }

    public User getUser(int id)
    {
        User user = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        return user;
    }

    public List<User> getUsers()
    {
        List<User> users = repo.findAll();
        return users;
    }

    public User deleteUser(int id)
    {
        User user = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        repo.delete(user);
        return user;
    }
}
