package fer.fpn;
import fer.fpn.DTO.ExerciseDTO;
import fer.fpn.dao.Exercise;
import fer.fpn.repository.ExerciseRepository;
import fer.fpn.service.ExerciseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExerciseServiceTests {
	@Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private ExerciseService exerciseService;

    @Test
    void testNewExercise() {
        ExerciseDTO exerciseDTO = new ExerciseDTO(null, "Title", "Description", null);
        Exercise exercise = new Exercise("Description", "Title", null);
        exerciseService.newExercise(exerciseDTO);
        verify(exerciseRepository, times(1)).save(exercise);
    }

    @Test
    void testGetExerciseById() {
      
        Long exerciseId = 1L;
        Exercise exercise = new Exercise("Title", "Description", null);
        when(exerciseRepository.findById(exerciseId)).thenReturn(Optional.of(exercise));

    
        ExerciseDTO exerciseDTO = exerciseService.getExerciseById(exerciseId);

        
        assertEquals(exercise.getIdExercise(), exerciseDTO.getIdExercise());
        assertEquals(exercise.getTitle(), exerciseDTO.getTitle());
        assertEquals(exercise.getDescription(), exerciseDTO.getDescription());
    }
    @Test
    void testGetAllExercises() {
        List<Exercise> exercises = List.of(
                new Exercise("Title1", "Description1", null),
                new Exercise("Title2", "Description2", null)
        );
        when(exerciseRepository.findAll()).thenReturn(exercises);

        List<ExerciseDTO> exerciseDTOs = exerciseService.getAllExercises();

    
        assertEquals(2, exerciseDTOs.size());
        assertEquals(exercises.get(0).getIdExercise(), exerciseDTOs.get(0).getIdExercise());
        assertEquals(exercises.get(0).getTitle(), exerciseDTOs.get(0).getTitle());
        assertEquals(exercises.get(0).getDescription(), exerciseDTOs.get(0).getDescription());
        assertEquals(exercises.get(0).getUser(), exerciseDTOs.get(0).getIdUser());
        assertEquals(exercises.get(1).getIdExercise(), exerciseDTOs.get(1).getIdExercise());
        assertEquals(exercises.get(1).getTitle(), exerciseDTOs.get(1).getTitle());
        assertEquals(exercises.get(1).getDescription(), exerciseDTOs.get(1).getDescription());
        assertEquals(exercises.get(1).getUser(), exerciseDTOs.get(1).getIdUser());
    }

    @Test
    void testDeleteExercise() {
    	Long exerciseId = 1L;
        exerciseService.deleteExercise(exerciseId);
        verify(exerciseRepository, times(1)).deleteById(exerciseId);
    }
  
    @Test
    void testFindPreviousExercise() {
    	Long exerciseId = 1L;
        ExerciseDTO expectedExerciseDTO = new ExerciseDTO(null, "Title", "Description", null); 
        Exercise expectedExercise = new Exercise("Description", "Title", null); 
        when(exerciseRepository.findFirstByIdExerciseLessThanOrderByIdExerciseDesc(exerciseId))
                .thenReturn(Optional.of(expectedExercise));
        ExerciseDTO result = exerciseService.findPreviousExercise(exerciseId);
        assertEquals(expectedExerciseDTO, result);
    }
    
    @Test
    void testFindNextExercise() {
        Long exerciseId = 1L;
        ExerciseDTO expectedExerciseDTO = new ExerciseDTO(null, "Title", "Description", null);
        Exercise expectedExercise = new Exercise("Description", "Title", null); 
        when(exerciseRepository.findFirstByIdExerciseGreaterThanOrderByIdExerciseAsc(exerciseId))
                .thenReturn(Optional.of(expectedExercise));
        ExerciseDTO result = exerciseService.findNextExercise(exerciseId);
        assertEquals(expectedExerciseDTO, result);
    }
}
