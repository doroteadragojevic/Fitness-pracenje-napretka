package fer.fpn.service;

import fer.fpn.DTO.TrainingDTO;
import fer.fpn.dao.Training;
import fer.fpn.dao.TrainingExercise;
import fer.fpn.dao.UserFPN;
import fer.fpn.repository.TrainingExerciseRepository;
import fer.fpn.repository.TrainingRepository;
import fer.fpn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    @Autowired
    TrainingRepository trainingRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TrainingExerciseRepository trainingExerciseRepository;


    public void newTraining(TrainingDTO training) {
        UserFPN user = userRepository.getReferenceById(training.getUserId());
        trainingRepository.save(new Training(training.getTitle(), training.getDescription(), user));
    }

    public TrainingDTO getTrainingById(Long idTraining) {
        return TrainingDTO.toDto(trainingRepository.getReferenceById(idTraining));
    }

    public TrainingDTO updateTraining(TrainingDTO training) {
        Training t = trainingRepository.getReferenceById(training.getIdTraining());
        t.setDescription(training.getDescription());
        t.setTitle(training.getTitle());
        UserFPN user = userRepository.getReferenceById(training.getUserId());
        t.setUser(user);
        return TrainingDTO.toDto(trainingRepository.save(t));
    }

    public void deleteTraining(Long idTraining) {
        trainingExerciseRepository.deleteAllById(trainingExerciseRepository.findAll().stream().filter(te -> te.getTraining().getIdTraining() == idTraining).map(TrainingExercise::getId).toList());
        trainingRepository.deleteById(idTraining);
    }

    public List<TrainingDTO> getAllTrainings() {
        return trainingRepository.findAll().stream().map(TrainingDTO::toDto).toList();
    }
}
