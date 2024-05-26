package fer.fpn.UnitTests;
import fer.fpn.dao.UserFPN;
import fer.fpn.repository.UserRepository;
import fer.fpn.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
public class UserRepositoryTest {
	 @Mock
	    private UserRepository userRepository;

	    @InjectMocks
	    private UserService userService;

	    public UserRepositoryTest() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testFindFirstByUserIdLessThanOrderByUserIdDesc() {
	        Long userId = 2L;
	        UserFPN user1 = new UserFPN();
	        user1.setUserId(1L);

	        when(userRepository.findFirstByUserIdLessThanOrderByUserIdDesc(userId))
	                .thenReturn(Optional.of(user1));

	        Optional<UserFPN> foundUser = userRepository.findFirstByUserIdLessThanOrderByUserIdDesc(userId);

	        assertTrue(foundUser.isPresent());
	        assertEquals(1L, foundUser.get().getUserId());
	    }

	    @Test
	    void testFindFirstByUserIdGreaterThanOrderByUserIdAsc() {
	        Long userId = 1L;
	        UserFPN user2 = new UserFPN();
	        user2.setUserId(2L);

	        when(userRepository.findFirstByUserIdGreaterThanOrderByUserIdAsc(userId))
	                .thenReturn(Optional.of(user2));

	        Optional<UserFPN> foundUser = userRepository.findFirstByUserIdGreaterThanOrderByUserIdAsc(userId);

	        assertTrue(foundUser.isPresent());
	        assertEquals(2L, foundUser.get().getUserId());
	    }
}
