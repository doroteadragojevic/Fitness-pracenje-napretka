package fer.fpn.UnitTests;
import fer.fpn.dao.Exercise;
import fer.fpn.repository.ExerciseRepository;
import fer.fpn.service.ExerciseService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
public class ExerciseRepositoryTest {
	 @Mock
	    private ExerciseRepository exerciseRepository;

	    @InjectMocks
	    private ExerciseService exerciseService;

	    public ExerciseRepositoryTest() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void testFindFirstByIdExerciseLessThanOrderByIdExerciseDesc() {
	        Long exerciseId = 2L;
	        Exercise exercise1 = new Exercise();
	        exercise1.setIdExercise(1L);

	        when(exerciseRepository.findFirstByIdExerciseLessThanOrderByIdExerciseDesc(exerciseId))
	                .thenReturn(Optional.of(exercise1));

	        Optional<Exercise> foundExercise = exerciseRepository.findFirstByIdExerciseLessThanOrderByIdExerciseDesc(exerciseId);

	        assertTrue(foundExercise.isPresent());
	        assertEquals(1L, foundExercise.get().getIdExercise());
	    }

	    @Test
	    void testFindFirstByIdExerciseGreaterThanOrderByIdExerciseAsc() {
	        Long exerciseId = 1L;
	        Exercise exercise2 = new Exercise();
	        exercise2.setIdExercise(2L);

	        when(exerciseRepository.findFirstByIdExerciseGreaterThanOrderByIdExerciseAsc(exerciseId))
	                .thenReturn(Optional.of(exercise2));

	        Optional<Exercise> foundExercise = exerciseRepository.findFirstByIdExerciseGreaterThanOrderByIdExerciseAsc(exerciseId);

	        assertTrue(foundExercise.isPresent());
	        assertEquals(2L, foundExercise.get().getIdExercise());
	    }
}
