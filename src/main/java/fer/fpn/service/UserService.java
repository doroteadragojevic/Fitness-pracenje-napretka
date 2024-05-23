package fer.fpn.service;

import fer.fpn.DTO.UserDTO;
import fer.fpn.dao.UserFPN;
import fer.fpn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void newUser(UserDTO user) {
        userRepository.save(new UserFPN(user.getRole(), user.getName(), user.getSurname(), user.getEmail(), user.getPassword(), user.getDailyCalGoal(), null));
    }
}
