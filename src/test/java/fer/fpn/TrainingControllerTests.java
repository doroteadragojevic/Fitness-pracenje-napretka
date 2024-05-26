package fer.fpn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import fer.fpn.DTO.TrainingDTO;
import fer.fpn.controller.TrainingController;
import fer.fpn.dao.UserFPN;
import fer.fpn.repository.TrainingRepository;
import fer.fpn.service.TrainingExerciseService;
import fer.fpn.service.TrainingService;
import fer.fpn.service.UserService;

public class TrainingControllerTests {
	@Mock
    private TrainingService trainingService;
    
    @Mock
    private TrainingRepository trainingRepository;
    
    @Mock
    private UserService userService;
    
    @Mock
    private TrainingExerciseService trainingExerciseService;
    
    @InjectMocks
    private TrainingController trainingController;

    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
        model = new BindingAwareModelMap();
    }

    @Test
    void testGetTrainingById() {
        Long idTraining = 1L;
        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setIdTraining(idTraining);

        when(trainingService.getTrainingById(idTraining)).thenReturn(trainingDTO);
        when(trainingService.findPreviousTraining(idTraining)).thenReturn(null);
        when(trainingService.findNextTraining(idTraining)).thenReturn(null);
        when(trainingExerciseService.getExerciseTrainingsForTraining(idTraining)).thenReturn(new ArrayList<>());

        String viewName = trainingController.getTraining(idTraining, model);
        assertEquals("training", viewName);
        assertEquals(trainingDTO, model.getAttribute("training"));
        assertEquals(null, model.getAttribute("prevTrainingId"));
        assertEquals(null, model.getAttribute("nextTrainingId"));
        assertEquals(new ArrayList<>(), model.getAttribute("tes"));
    }
    
    
    @Test
    void testTrainings() {
        when(trainingService.getAllTrainings()).thenReturn(Collections.emptyList());

        String viewName = trainingController.trainings(model);
        assertEquals("trainings", viewName);
        assertEquals(Collections.emptyList(), model.getAttribute("trainings"));
    }
    
    @Test
    void testNewTraining() {
    	 when(userService.getAllUsers()).thenReturn(Collections.emptyList());

    	    String viewName = trainingController.newTraining(model);
    	    List<UserFPN> users = (List<UserFPN>) model.getAttribute("users");
    	    TrainingDTO trainingDTO = (TrainingDTO) model.getAttribute("training");
   
    	    assertEquals("createTrainingForm", viewName);
    	    assertEquals(new TrainingDTO(), trainingDTO);
    	    assertEquals(Collections.emptyList(), users);
    }
    
    
    @Test
    void testNewTrainingPost() {
        TrainingDTO training = new TrainingDTO();
        String viewName = trainingController.newTraining(training);
        assertEquals("redirect:/training/", viewName);
    }
    
    @Test
    void testUpdateTrainingGet() {
        Long idTraining = 1L;
        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setIdTraining(idTraining);

        when(trainingService.getTrainingById(idTraining)).thenReturn(trainingDTO);
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        String viewName = trainingController.updateTraining(idTraining, model);
        assertEquals("trainingUpdate", viewName);
        assertEquals(trainingDTO, model.getAttribute("training"));
        assertEquals(Collections.emptyList(), model.getAttribute("users"));
    }

    @Test
    void testUpdateTrainingPost() {
        TrainingDTO training = new TrainingDTO();
        training.setIdTraining(1L);

        when(trainingService.updateTraining(training)).thenReturn(training);

        String viewName = trainingController.updateTraining(training);
        assertEquals("redirect:/training/" + training.getIdTraining(), viewName);
    }
    
    @Test
    void testDeleteTraining() {
        Long idTraining = 1L;
        String viewName = trainingController.deleteTraining(idTraining);
        assertEquals("redirect:/training/", viewName);
    }
    
    
    
    
    
    
    
    
    
    
    
    
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

