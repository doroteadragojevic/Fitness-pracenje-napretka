package fer.fpn.UnitTests;
import fer.fpn.DTO.UserDTO;
import fer.fpn.controller.UserController;
import fer.fpn.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class UserControllerTest {
	  @InjectMocks
	    private UserController userController;

	    @Mock
	    private UserService userService;

	    @Mock
	    private Model model;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    @Test
	    void testUsersGet() {
	        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

	        String viewName = userController.users(model);

	        assertEquals("users", viewName);
	        verify(model).addAttribute("users", Collections.emptyList());
	    }
	    
	    @Test
	    void testNewUserGet() {
	        String viewName = userController.newUser(model);

	        assertEquals("createUserForm", viewName);
	        verify(model).addAttribute("user", new UserDTO());
	    }
	    
	    @Test
	    void testNewUserPost() {
	        UserDTO user = new UserDTO();
	        String viewName = userController.newUser(user);

	        assertEquals("redirect:/user/", viewName);
	        verify(userService).newUser(user);
	    }
	    
	    @Test
	    void testGetUser() {
	        Long userId = 1L;
	        UserDTO user = new UserDTO();
	        UserDTO prevUser = new UserDTO();
	        prevUser.setUserId(0L);
	        UserDTO nextUser = new UserDTO();
	        nextUser.setUserId(2L);

	        when(userService.getUserById(userId)).thenReturn(user);
	        when(userService.findPreviousUser(userId)).thenReturn(prevUser);
	        when(userService.findNextUser(userId)).thenReturn(nextUser);

	        String viewName = userController.getUser(userId, model);

	        assertEquals("user", viewName);
	        verify(model).addAttribute("user", user);
	        verify(model).addAttribute("prevUserId", prevUser.getUserId());
	        verify(model).addAttribute("nextUserId", nextUser.getUserId());
	    }
	    
	    @Test
	    void testUpdateUserGet() {
	        Long userId = 1L;
	        UserDTO user = new UserDTO();

	        when(userService.getUserById(userId)).thenReturn(user);

	        String viewName = userController.updateUser(userId, model);

	        assertEquals("userUpdate", viewName);
	        verify(model).addAttribute("user", user);
	    }

	    @Test
	    void testUpdateUserPost() {
	        UserDTO user = new UserDTO();
	        user.setUserId(1L);
	        UserDTO updatedUser = new UserDTO();
	        updatedUser.setUserId(1L);

	        when(userService.updateUser(user)).thenReturn(updatedUser);

	        String viewName = userController.updateUser(user);

	        assertEquals("redirect:/user/" + user.getUserId(), viewName);
	        verify(userService).updateUser(user);
	    }

	    @Test
	    void testDeleteUser() {
	        Long userId = 1L;

	        String viewName = userController.deleteUser(userId);

	        assertEquals("redirect:/user/", viewName);
	        verify(userService).deleteUser(userId);
	    }
	    
}
