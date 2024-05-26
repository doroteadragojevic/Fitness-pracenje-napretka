package fer.fpn.UnitTests;
import fer.fpn.DTO.TrainingDTO;
import fer.fpn.dao.Training;
import fer.fpn.dao.TrainingExercise;
import fer.fpn.dao.UserFPN;
import fer.fpn.repository.TrainingExerciseRepository;
import fer.fpn.repository.TrainingRepository;
import fer.fpn.repository.UserRepository;
import fer.fpn.service.TrainingService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {

    @Mock
    private TrainingRepository trainingRepository;

    @Mock
    private UserRepository userRepository;
    @Mock
    private TrainingExerciseRepository trainingExerciseRepository;

    @InjectMocks
    private TrainingService trainingService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetTrainingById() {
        Long trainingId = 1L;
        TrainingDTO expectedTraining = new TrainingDTO();
        expectedTraining.setIdTraining(trainingId);
        UserFPN user = new UserFPN();
        user.setUserId(null);
        Training training = new Training();
        training.setIdTraining(trainingId);
        training.setUser(user);
        when(trainingRepository.findById(trainingId)).thenReturn(Optional.of(training));
        TrainingDTO result = trainingService.getTrainingById(trainingId);
        assertNotNull(result);
        assertEquals(trainingId, result.getIdTraining());
        assertNull(result.getUserId()); 
    }

    @Test
    void testNewTraining() {
        TrainingDTO trainingDTO = new TrainingDTO();
        trainingDTO.setTitle("Test Training");
        trainingDTO.setDescription("Test Description");
        Long userId = 1L;
        trainingDTO.setUserId(userId);

        UserFPN user = new UserFPN();
        user.setUserId(userId);
        when(userRepository.getReferenceById(userId)).thenReturn(user);
        trainingService.newTraining(trainingDTO);
        assertNotNull(trainingDTO);
    }
    
    
    @Test
    void testFindPreviousTraining() {
        Long trainingId = 1L;
        TrainingDTO expectedTraining = new TrainingDTO();
        expectedTraining.setIdTraining(trainingId);
        UserFPN user = new UserFPN();
        user.setUserId(null);
        Training training = new Training();
        training.setIdTraining(trainingId);
        training.setUser(user);
        when(trainingRepository.findFirstByIdTrainingLessThanOrderByIdTrainingDesc(trainingId)).thenReturn(Optional.of(training));
        TrainingDTO result = trainingService.findPreviousTraining(trainingId);
        assertNotNull(result);
        assertEquals(trainingId, result.getIdTraining());
        assertNull(result.getUserId()); 
    }
    
    @Test
    void testFindNextTraining() {
        Long trainingId = 1L;
        TrainingDTO expectedTraining = new TrainingDTO();
        expectedTraining.setIdTraining(trainingId);
        UserFPN user = new UserFPN();
        user.setUserId(null);
        Training training = new Training();
        training.setIdTraining(trainingId);
        training.setUser(user);
        when(trainingRepository.findFirstByIdTrainingGreaterThanOrderByIdTrainingAsc(trainingId)).thenReturn(Optional.of(training));
        TrainingDTO result = trainingService.findNextTraining(trainingId);
        assertNotNull(result);
        assertEquals(trainingId, result.getIdTraining());
        assertNull(result.getUserId()); 
    }
    
    @Test
    void testDeleteTraining() {
    	Long trainingId = 1L;
        Training trainingToDelete = new Training();
        trainingToDelete.setIdTraining(trainingId);
        List<TrainingExercise> trainingExercisesToDelete = new ArrayList<>();
        trainingService.deleteTraining(trainingId);
        verify(trainingExerciseRepository, times(1)).deleteAllById(trainingExercisesToDelete.stream().map(TrainingExercise::getId).toList());
        verify(trainingRepository, times(1)).deleteById(trainingId);
     }

}
