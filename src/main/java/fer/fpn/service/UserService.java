package fer.fpn.service;

import fer.fpn.DTO.UserDTO;
import fer.fpn.dao.UserFPN;
import fer.fpn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void newUser(UserDTO user) {
        userRepository.save(new UserFPN(user.getRole(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getDailyCalGoal(), null));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserDTO::toDto).toList();
    }

    public UserDTO getUserById(Long userId) {
        return UserDTO.toDto(userRepository.getReferenceById(userId));
    }

    public UserDTO updateUser(UserDTO user) {
        UserFPN u = userRepository.getReferenceById(user.getUserId());
        u.setRole(user.getRole());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        return UserDTO.toDto(userRepository.save(u));
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
