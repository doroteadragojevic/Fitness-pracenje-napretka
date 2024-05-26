package fer.fpn.UnitTests;

import fer.fpn.repository.UserRepository;
import fer.fpn.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import fer.fpn.DTO.UserDTO;
import fer.fpn.dao.UserFPN;

public class UserServiceTest {
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
  
        Long userId = 1L;
        UserFPN expectedUser = new UserFPN();
        expectedUser.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

       
        UserDTO result = userService.getUserById(userId);

    
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
    }
    
    @Test
    void testNewUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setRole("Role");
        userDTO.setName("Name");
        userDTO.setSurname("Surname");
        userDTO.setEmail("email@example.com");
        userDTO.setPassword("password");
        userDTO.setDailyCalGoal(2000f);
        userService.newUser(userDTO);
        verify(userRepository, times(1)).save(any(UserFPN.class));
    }
    
    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new UserFPN()));
        List<UserDTO> result = userService.getAllUsers();
        assertNotNull(result);
        assertEquals(1, result.size());
    }
    @Test
    void testFindPreviousUser() {
        Long userId = 1L;
        UserFPN expectedUser = new UserFPN();
        expectedUser.setUserId(userId);

        when(userRepository.findFirstByUserIdLessThanOrderByUserIdDesc(userId)).thenReturn(Optional.of(expectedUser));
        UserDTO result = userService.findPreviousUser(userId);
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
    }
    
    @Test
    void testFindNextUser() {
    
        Long userId = 1L;
        UserFPN expectedUser = new UserFPN();
        expectedUser.setUserId(userId);

        when(userRepository.findFirstByUserIdGreaterThanOrderByUserIdAsc(userId)).thenReturn(Optional.of(expectedUser));
        UserDTO result = userService.findNextUser(userId);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
    }
    
    @Test
    void testDeleteUser() {
        Long userId = 1L;
        userService.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }
}
