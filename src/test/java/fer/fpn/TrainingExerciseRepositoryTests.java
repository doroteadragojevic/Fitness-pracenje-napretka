package fer.fpn;
import fer.fpn.dao.TrainingExercise;
import fer.fpn.repository.TrainingExerciseRepository;
import fer.fpn.service.TrainingExerciseService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
public class TrainingExerciseRepositoryTests {
	@Mock
    private TrainingExerciseRepository trainingExerciseRepository;

    @InjectMocks
    private TrainingExerciseService trainingExerciseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindFirstByIdLessThanOrderByIdDesc() {
        Long trainingExerciseId = 2L;
        TrainingExercise trainingExercise1 = new TrainingExercise();
        trainingExercise1.setId(1L);

        when(trainingExerciseRepository.findFirstByIdLessThanOrderByIdDesc(trainingExerciseId))
                .thenReturn(Optional.of(trainingExercise1));

        Optional<TrainingExercise> foundTrainingExercise = trainingExerciseRepository.findFirstByIdLessThanOrderByIdDesc(trainingExerciseId);

        assertTrue(foundTrainingExercise.isPresent());
        assertEquals(1L, foundTrainingExercise.get().getId());
    }

    @Test
    void testFindFirstByIdGreaterThanOrderByIdAsc() {
        Long trainingExerciseId = 1L;
        TrainingExercise trainingExercise2 = new TrainingExercise();
        trainingExercise2.setId(2L);

        when(trainingExerciseRepository.findFirstByIdGreaterThanOrderByIdAsc(trainingExerciseId))
                .thenReturn(Optional.of(trainingExercise2));

        Optional<TrainingExercise> foundTrainingExercise = trainingExerciseRepository.findFirstByIdGreaterThanOrderByIdAsc(trainingExerciseId);

        assertTrue(foundTrainingExercise.isPresent());
        assertEquals(2L, foundTrainingExercise.get().getId());
    }
}
