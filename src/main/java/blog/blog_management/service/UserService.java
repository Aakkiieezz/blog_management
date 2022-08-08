package blog.blog_management.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import blog.blog_management.entity.User;
import blog.blog_management.exception.ResourceNotFoundException;
import blog.blog_management.payload.EntityDtoConversion;
import blog.blog_management.payload.UserDto;
import blog.blog_management.repository.UserRepository;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private EntityDtoConversion converter;

    public UserDto createUser(UserDto userDto)
    {
        User user = converter.dtoToUser(userDto);
        userRepo.save(user);
        userDto = converter.userToDto(user);
        return userDto;
    }

    public UserDto getUser(int id)
    {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        UserDto userDto = converter.userToDto(user);
        return userDto;
    }

    public List<UserDto> getUsers()
    {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.converter.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    public UserDto updateUser(UserDto userDto, int id)
    {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        //check in video if below lines are correct
        userDto.setId(id);
        user = converter.dtoToUser(userDto);
        userRepo.save(user);
        return userDto;
    }

    public UserDto deleteUser(int id)
    {
        User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        userRepo.delete(user);
        UserDto userDto = converter.userToDto(user);
        return userDto;
    }
}
