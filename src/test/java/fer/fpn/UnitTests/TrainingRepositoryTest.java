package fer.fpn.UnitTests;
import fer.fpn.dao.Training;
import fer.fpn.repository.TrainingRepository;
import fer.fpn.service.TrainingService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class TrainingRepositoryTest {
	@Mock
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingService trainingService;

    public TrainingRepositoryTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindFirstByIdTrainingLessThanOrderByIdTrainingDesc() {
        Long trainingId = 2L;
        Training training1 = new Training();
        training1.setIdTraining(1L);

        when(trainingRepository.findFirstByIdTrainingLessThanOrderByIdTrainingDesc(trainingId))
                .thenReturn(Optional.of(training1));
        Optional<Training> foundTraining = trainingRepository.findFirstByIdTrainingLessThanOrderByIdTrainingDesc(trainingId);
        assertTrue(foundTraining.isPresent());
        assertEquals(1L, foundTraining.get().getIdTraining());
    }

    @Test
    void testFindFirstByIdTrainingGreaterThanOrderByIdTrainingAsc() {
        Long trainingId = 1L;
        Training training2 = new Training();
        training2.setIdTraining(2L);

        when(trainingRepository.findFirstByIdTrainingGreaterThanOrderByIdTrainingAsc(trainingId))
                .thenReturn(Optional.of(training2));
        Optional<Training> foundTraining = trainingRepository.findFirstByIdTrainingGreaterThanOrderByIdTrainingAsc(trainingId);
        assertTrue(foundTraining.isPresent());
        assertEquals(2L, foundTraining.get().getIdTraining());
    }
}
