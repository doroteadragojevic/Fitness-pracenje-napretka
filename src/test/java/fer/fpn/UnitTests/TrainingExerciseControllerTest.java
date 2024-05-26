package fer.fpn.UnitTests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import fer.fpn.DTO.TrainingExerciseDTO;
import fer.fpn.service.ExerciseService;
import fer.fpn.service.TrainingExerciseService;
import fer.fpn.controller.TrainingExerciseController;
public class TrainingExerciseControllerTest {
	 @InjectMocks
	    private TrainingExerciseController trainingExerciseController;

	    @Mock
	    private TrainingExerciseService trainingExerciseService;

	    @Mock
	    private ExerciseService exerciseService;

	    @Mock
	    private BindingResult bindingResult;

	    @Mock
	    private Model model;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	   
	    
	    @Test
	    void NewExerciseGet() {
	    	Long idTraining = 1L;
	        when(exerciseService.getAllExercises()).thenReturn(Collections.emptyList());

	        String viewName = trainingExerciseController.newExercise(idTraining, model);

	        assertEquals("createTrainingExerciseForm", viewName);
	        verify(model).addAttribute("trainingExercise", new TrainingExerciseDTO(idTraining));
	        verify(model).addAttribute("exercises", Collections.emptyList());
	    }
	    

	    @Test
	    void testNewExerciseSuccessPost() {
	        TrainingExerciseDTO exercise = new TrainingExerciseDTO(1L);
	        exercise.setReps(10);
	        exercise.setSets(3);
	        exercise.setWeight(100f);
	        when(bindingResult.hasErrors()).thenReturn(false);

	        String viewName = trainingExerciseController.newExercise(exercise, bindingResult, model);

	        assertEquals("redirect:/training/1", viewName);
	        verify(trainingExerciseService).newTrainingExercise(exercise);
	    }
	    
	    @Test
	    void testNewExerciseWithInvalidSetsPost() {
	        TrainingExerciseDTO exercise = new TrainingExerciseDTO(1L);
	        exercise.setReps(10);
	        exercise.setSets(0);
	        when(bindingResult.hasErrors()).thenReturn(false);
	        when(exerciseService.getAllExercises()).thenReturn(Collections.emptyList());

	        String viewName = trainingExerciseController.newExercise(exercise, bindingResult, model);

	        assertEquals("createTrainingExerciseForm", viewName);
	        verify(bindingResult).rejectValue("sets", "error.sets", "Broj serija mora biti veći od 0");
	        verify(model).addAttribute("exercises", Collections.emptyList());
	    }
	    
	    @Test
	    void testGetExercise() {
	        Long trainingExerciseId = 1L;
	        TrainingExerciseDTO currentExercise = new TrainingExerciseDTO();
	        currentExercise.setId(trainingExerciseId);
	        TrainingExerciseDTO prevExercise = new TrainingExerciseDTO();
	        prevExercise.setId(0L);
	        TrainingExerciseDTO nextExercise = new TrainingExerciseDTO();
	        nextExercise.setId(2L);

	        when(trainingExerciseService.getTrainingExerciseById(trainingExerciseId)).thenReturn(currentExercise);
	        when(trainingExerciseService.findPreviousTrainingExercise(trainingExerciseId)).thenReturn(prevExercise);
	        when(trainingExerciseService.findNextTrainingExercise(trainingExerciseId)).thenReturn(nextExercise);

	        String viewName = trainingExerciseController.getExercise(trainingExerciseId, model);

	        assertEquals("trainingExercise", viewName);
	        verify(model).addAttribute("trainingExercise", currentExercise);
	        verify(model).addAttribute("prevTrainingExerciseId", prevExercise.getId());
	        verify(model).addAttribute("nextTrainingExerciseId", nextExercise.getId());
	    }
	    @Test
	    void testUpdateExerciseGet() {
	        Long trainingExerciseId = 1L;
	        TrainingExerciseDTO trainingExerciseDTO = new TrainingExerciseDTO();
	        trainingExerciseDTO.setId(trainingExerciseId);

	        when(trainingExerciseService.getTrainingExerciseById(trainingExerciseId)).thenReturn(trainingExerciseDTO);
	        when(exerciseService.getAllExercises()).thenReturn(Collections.emptyList());

	        String viewName = trainingExerciseController.updateExercise(trainingExerciseId, model);

	        assertEquals("trainingExerciseUpdate", viewName);
	        verify(model).addAttribute("trainingExercise", trainingExerciseDTO);
	        verify(model).addAttribute("exercises", Collections.emptyList());
	    }
	    
	    @Test
	    void testUpdateExerciseSuccessPost() {
	        TrainingExerciseDTO exercise = new TrainingExerciseDTO();
	        exercise.setIdTraining(1L);
	        exercise.setReps(10);
	        exercise.setSets(3);
	        exercise.setWeight(100f);
	        when(bindingResult.hasErrors()).thenReturn(false);

	        String viewName = trainingExerciseController.updateExercise(exercise, bindingResult, model);

	        assertEquals("redirect:/training/" + exercise.getIdTraining(), viewName);
	        verify(trainingExerciseService).updateTrainingExercise(exercise);
	    }
	    @Test
	    void testUpdateExerciseWithInvalidWeightPost() {
	        TrainingExerciseDTO exercise = new TrainingExerciseDTO();
	        exercise.setReps(10);
	        exercise.setSets(3);
	        exercise.setWeight(301f);
	        when(bindingResult.hasErrors()).thenReturn(false);
	        when(exerciseService.getAllExercises()).thenReturn(Collections.emptyList());

	        String viewName = trainingExerciseController.updateExercise(exercise, bindingResult, model);

	        assertEquals("trainingExerciseUpdate", viewName);
	        verify(bindingResult).rejectValue("weight", "error.weight", "Kilaža mora biti između 0 i 300");
	        verify(model).addAttribute("exercises", Collections.emptyList());
	        verifyNoInteractions(trainingExerciseService);
	    }
	    
	    @Test
	    void testDeleteExercise() {
	        Long trainingExerciseId = 1L;
	        Long idTraining = 2L;

	        String viewName = trainingExerciseController.deleteExercise(trainingExerciseId, idTraining);

	        assertEquals("redirect:/training/2", viewName);
	        verify(trainingExerciseService).deleteTrainingExercise(trainingExerciseId);
	    }
}
