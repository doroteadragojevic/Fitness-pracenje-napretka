package fer.fpn;
import fer.fpn.DTO.UserDTO;
import fer.fpn.dao.UserFPN;
import fer.fpn.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasProperty;
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserIntegrationTests {
	
	 @Autowired
	    private MockMvc mockMvc;

	    @Autowired
	    private UserRepository userRepository;

	    @Test
	    public void testCreateNewUser() throws Exception {
	        UserDTO newUser = new UserDTO(null, null, "Ana", "Cepic", "a.c@gmail.com", null, 1500f, null, null);

	        mockMvc.perform(post("/user/new-user")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("name", newUser.getName())
	                .param("surname", newUser.getSurname())
	                .param("email", newUser.getEmail())
	                .param("password", newUser.getPassword())
	                .param("dailyCalGoal", String.valueOf(newUser.getDailyCalGoal())))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/user/"));
	    }

	    @Test
	    public void testUpdateUser() throws Exception {
	    	UserFPN user1 = new UserFPN("Sportas", "Valentina", "Valic", "valentina.valic14@gmail.com", "lozinka123", 2000f, null);
	    	userRepository.save(user1);
	    	Optional<UserFPN> optionalUser = userRepository.findByEmail("valentina.valic14@gmail.com");
	        assertTrue(optionalUser.isPresent(), "User not found");

	        UserFPN user = optionalUser.get();

	        mockMvc.perform(post("/user/update")
	                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
	                .param("userId", String.valueOf(user.getUserId()))
	                .param("name", "Vale")
	                .param("surname", "Vaaalic")
	                .param("email", user.getEmail())
	                .param("password", user.getPassword())
	                .param("dailyCalGoal", String.valueOf(user.getDailyCalGoal())))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/user/" + user.getUserId()));

	        UserFPN updatedUser = userRepository.findByEmail("valentina.valic14@gmail.com").get();
	        assert(updatedUser.getName().equals("Vale"));
	        assert(updatedUser.getSurname().equals("Vaaalic"));
	    }

	    @Test
	    public void testDeleteUser() throws Exception {
	    	UserFPN user1 = new UserFPN("Sportas", "Valentina", "Valic", "valentina.valic14@gmail.com", "lozinka123", 2000f, null);
	    	userRepository.save(user1);
	        UserFPN user = userRepository.findByEmail("valentina.valic13@gmail.com").get();

	        mockMvc.perform(get("/user/delete/" + user.getUserId()))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/user/"));
	    }
	    
	    @Test
	    public void testGetUser() throws Exception {
	        UserFPN user = userRepository.save(new UserFPN("Trener", "Luka", "Matejic", "luka.matejic@gyms4you.com", "pass123", 2500f, null));

	        mockMvc.perform(get("/user/" + user.getUserId()))
	                .andExpect(status().isOk())
	                .andExpect(model().attributeExists("user"))
	                .andExpect(model().attribute("user", hasProperty("name", is("Luka"))))
	                .andExpect(model().attribute("user", hasProperty("surname", is("Matejic"))))
	                .andExpect(model().attribute("user", hasProperty("email", is("luka.matejic@gyms4you.com"))))
	                .andExpect(view().name("user"));
	    }


}
