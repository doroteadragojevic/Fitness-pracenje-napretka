package fer.fpn;

import fer.fpn.DTO.TrainingDTO;
import fer.fpn.controller.TrainingController;
import fer.fpn.dao.Training;
import fer.fpn.dao.TrainingExercise;
import fer.fpn.dao.UserFPN;
import fer.fpn.repository.TrainingExerciseRepository;
import fer.fpn.repository.TrainingRepository;
import fer.fpn.repository.UserRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class TrainingIntegrationTests {
	 @Autowired
	    private MockMvc mockMvc;
	 
	 @Autowired
	    private TrainingController trainingController;

	    @Autowired
	    private TrainingRepository trainingRepository;

	    @Autowired
	    private TrainingExerciseRepository trainingExerciseRepository;

	    @Autowired
	    private UserRepository userRepository;

	    private UserFPN user;

	    @BeforeAll
	    public void setUp() {
	    	trainingExerciseRepository.deleteAll();
	        trainingRepository.deleteAll();
	        userRepository.deleteAll();

	        user = new UserFPN();
	        user.setName("John");
	        user.setSurname("Doe");
	        user = userRepository.save(user);

	        Training training1 = new Training("Training 1", "Description 1", user);
	        Training training2 = new Training("Training 2", "Description 2", user);
	        trainingRepository.saveAll(List.of(training1, training2));
	    }

	    @Test
	    public void testTrainings() throws Exception {
	        mockMvc.perform(get("/training/"))
	                .andExpect(status().isOk())
	                .andExpect(model().attributeExists("trainings"))
	                .andExpect(view().name("trainings"));
	    }

	    @Test
	    public void testNewTrainingForm() throws Exception {
	        mockMvc.perform(get("/training/new-training"))
	                .andExpect(status().isOk())
	                .andExpect(model().attributeExists("training"))
	                .andExpect(model().attributeExists("users"))
	                .andExpect(view().name("createTrainingForm"));
	    }

	    @Test
	    public void testCreateNewTraining() throws Exception {
	    	   TrainingDTO trainingDTO = new TrainingDTO();
	    	    trainingDTO.setTitle("Training 3");
	    	    trainingDTO.setDescription("Description 3");
	    	    trainingDTO.setUserId(user.getUserId());

	    	    String redirectUrl = trainingController.newTraining(trainingDTO);
	    	    assertThat(redirectUrl).isEqualTo("redirect:/training/"); 
	    }

	    @Test
	    public void testGetTraining() throws Exception {
	        Training training = trainingRepository.findAll().get(0);

	        mockMvc.perform(get("/training/" + training.getIdTraining()))
	                .andExpect(status().isOk())
	                .andExpect(model().attributeExists("training"))
	                .andExpect(model().attributeExists("tes"))
	                .andExpect(view().name("training"));
	    }

	    @Test
	    public void testUpdateTrainingForm() throws Exception {
	        Training training = trainingRepository.findAll().get(0);

	        mockMvc.perform(get("/training/update/" + training.getIdTraining()))
	                .andExpect(status().isOk())
	                .andExpect(model().attributeExists("training"))
	                .andExpect(model().attributeExists("users"))
	                .andExpect(view().name("trainingUpdate"));
	    }

	    @Test
	    public void testUpdateTraining() throws Exception {
	        Training training = trainingRepository.findAll().get(0);

	        mockMvc.perform(post("/training/update")
	                .param("idTraining", training.getIdTraining().toString())
	                .param("title", "Updated Training")
	                .param("description", "Updated Description")
	                .param("userId", user.getUserId().toString()))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/training/" + training.getIdTraining()));

	        Training updatedTraining = trainingRepository.findById(training.getIdTraining()).get();
	        assert(updatedTraining.getTitle().equals("Updated Training"));
	        assert(updatedTraining.getDescription().equals("Updated Description"));
	    }

	    @Test
	    public void testDeleteTraining() throws Exception {
	        Training training = trainingRepository.findAll().get(0);

	        mockMvc.perform(get("/training/delete/" + training.getIdTraining()))
	                .andExpect(status().is3xxRedirection())
	                .andExpect(redirectedUrl("/training/"));

	        List<Training> trainings = trainingRepository.findAll();
	        assert(trainings.size() == 1);
	    }
	    
}
