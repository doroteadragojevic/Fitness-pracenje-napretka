package fer.fpn;
import fer.fpn.DTO.TrainingExerciseDTO;
import fer.fpn.dao.Exercise;
import fer.fpn.dao.Training;
import fer.fpn.dao.TrainingExercise;
import fer.fpn.repository.ExerciseRepository;
import fer.fpn.repository.TrainingExerciseRepository;
import fer.fpn.repository.TrainingRepository;
import fer.fpn.service.TrainingExerciseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainingExerciseServiceTests {

	@Mock
    private TrainingExerciseRepository trainingExerciseRepository;

    @Mock
    private ExerciseRepository exerciseRepository;

    @Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingExerciseService trainingExerciseService;

    @Test
    void testNewTrainingExercise() {
        TrainingExerciseDTO trainingExerciseDTO = new TrainingExerciseDTO();
        trainingExerciseDTO.setId(1L);
        trainingExerciseDTO.setIdExercise(1L);
        trainingExerciseDTO.setIdTraining(1L);
        trainingExerciseDTO.setReps(10);
        trainingExerciseDTO.setSets(3);
        trainingExerciseDTO.setWeight(20.5f);

        Exercise exercise = new Exercise();
        exercise.setIdExercise(1L);
        when(exerciseRepository.getReferenceById(1L)).thenReturn(exercise);

        Training training = new Training();
        training.setIdTraining(1L);
        when(trainingRepository.getReferenceById(1L)).thenReturn(training);

        trainingExerciseService.newTrainingExercise(trainingExerciseDTO);

        verify(trainingExerciseRepository).save(new TrainingExercise(exercise, 10, 3, 20.5f, training));
    }
    
    @Test
    void testGetTrainingExerciseById() {
        Long trainingExerciseId = 1L;
        Long exerciseId = 2L; 
        TrainingExerciseDTO expectedTrainingExerciseDTO = new TrainingExerciseDTO();
        expectedTrainingExerciseDTO.setId(trainingExerciseId);
        Exercise exercise = new Exercise();
        exercise.setIdExercise(exerciseId);
        TrainingExercise trainingExercise = new TrainingExercise();
        trainingExercise.setId(trainingExerciseId);
        trainingExercise.setExercise(exercise);
        when(trainingExerciseRepository.findById(trainingExerciseId)).thenReturn(Optional.of(trainingExercise));
        TrainingExerciseDTO result = trainingExerciseService.getTrainingExerciseById(trainingExerciseId);
        assertNotNull(result);
        assertEquals(trainingExerciseId, result.getId());
    }
    
   
    @Test
    void testDeleteTrainingExercise() {
        Long trainingExerciseId = 1L;
        trainingExerciseService.deleteTrainingExercise(trainingExerciseId);
        verify(trainingExerciseRepository, times(1)).deleteById(trainingExerciseId);
    }

}
