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
    private UserRepository userRepo;

    public User createUser(User user)
    {
        userRepo.save(user);
        return user;
    }

    public User getUser(int id)
    {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        return user;
    }

    public List<User> getUsers()
    {
        List<User> users = userRepo.findAll();
        return users;
    }

    public User updateUser(User userNew, int id)
    {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        user.setName(userNew.getName());
        user.setEmail(userNew.getEmail());
        user.setPassword(userNew.getPassword());
        userRepo.save(user);
        return user;
    }

    public User deleteUser(int id)
    {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        userRepo.delete(user);
        return user;
    }
}
