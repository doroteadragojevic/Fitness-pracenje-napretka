package fer.fpn;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import fer.fpn.DTO.ExerciseDTO;
import fer.fpn.controller.ExerciseController;
import fer.fpn.service.ExerciseService;

public class ExerciseControllerTests {
	  private ExerciseService exerciseService;
	    private ExerciseController exerciseController;
	    private Model model;

	    @BeforeEach
	    void setUp() {
	        exerciseService = mock(ExerciseService.class);
	        exerciseController = new ExerciseController();
	        exerciseController.exerciseService = exerciseService;
	        model = new BindingAwareModelMap();
	    }

	    @Test
	    void testExercises() {
	        when(exerciseService.getAllExercises()).thenReturn(Collections.emptyList());

	        String viewName = exerciseController.exercises(model);

	        assertEquals("exercises", viewName);
	        assertEquals(Collections.emptyList(), model.getAttribute("exercises"));
	    }

	    @Test
	    void testNewExercise() {
	        String viewName = exerciseController.newExercise(model);

	        assertEquals("createExerciseForm", viewName);
	        assertEquals(new ExerciseDTO(), model.getAttribute("exercise"));
	    }

	    @Test
	    void testNewExercisePost() {
	        ExerciseDTO exercise = new ExerciseDTO();
	        String viewName = exerciseController.newExercise(exercise);

	        assertEquals("redirect:/exercise/", viewName);
	    }

	    @Test
	    void testGetExercise() {
	        Long exerciseId = 1L;
	        ExerciseDTO exerciseDTO = new ExerciseDTO();
	        exerciseDTO.setIdExercise(exerciseId);

	        when(exerciseService.getExerciseById(exerciseId)).thenReturn(exerciseDTO);
	        when(exerciseService.findPreviousExercise(exerciseId)).thenReturn(null);
	        when(exerciseService.findNextExercise(exerciseId)).thenReturn(null);

	        String viewName = exerciseController.getExercise(exerciseId, model);

	        assertEquals("exercise", viewName);
	        assertEquals(exerciseDTO, model.getAttribute("exercise"));
	        assertEquals(null, model.getAttribute("prevExerciseId"));
	        assertEquals(null, model.getAttribute("nextExerciseId"));
	    }

	    @Test
	    void testUpdateExerciseGet() {
	        Long exerciseId = 1L;
	        ExerciseDTO exerciseDTO = new ExerciseDTO();
	        exerciseDTO.setIdExercise(exerciseId);

	        when(exerciseService.getExerciseById(exerciseId)).thenReturn(exerciseDTO);

	        String viewName = exerciseController.updateExercise(exerciseId, model);

	        assertEquals("exerciseUpdate", viewName);
	        assertEquals(exerciseDTO, model.getAttribute("exercise"));
	    }

	    @Test
	    void testUpdateExercisePost() {
	        ExerciseDTO exercise = new ExerciseDTO();
	        exercise.setIdExercise(1L);

	        ExerciseDTO updatedExercise = new ExerciseDTO();
	        updatedExercise.setIdExercise(1L);

	        when(exerciseService.updateExercise(exercise)).thenReturn(updatedExercise);

	        String viewName = exerciseController.updateExercise(exercise);

	        assertEquals("redirect:/exercise/" + updatedExercise.getIdExercise(), viewName);
	    }

	    @Test
	    void testDeleteExercise() {
	        Long exerciseId = 1L;
	        String viewName = exerciseController.deleteExercise(exerciseId);

	        assertEquals("redirect:/exercise/", viewName);
	    }
}
