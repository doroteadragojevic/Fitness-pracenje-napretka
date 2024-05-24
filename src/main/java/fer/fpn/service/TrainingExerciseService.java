package fer.fpn.service;

import fer.fpn.DTO.TrainingExerciseDTO;
import fer.fpn.dao.Exercise;
import fer.fpn.dao.TrainingExercise;
import fer.fpn.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.fpn.repository.TrainingExerciseRepository;

import java.util.List;

@Service
public class TrainingExerciseService {

    @Autowired
    TrainingExerciseRepository trainingExerciseRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    public List<TrainingExerciseDTO> getAllTrainingExercises() {
        return trainingExerciseRepository.findAll().stream().map(TrainingExerciseDTO::toDto).toList();
    }

    public void newTrainingExercise(TrainingExerciseDTO exercise) {
        Exercise exercise1 = exerciseRepository.getReferenceById(exercise.getIdExercise());
        trainingExerciseRepository.save(new TrainingExercise(exercise1, exercise.getReps(), exercise.getSets(), exercise.getWeight(), null));
    }

    public TrainingExerciseDTO getTrainingExerciseById(Long trainingExerciseId) {
        return TrainingExerciseDTO.toDto(trainingExerciseRepository.getReferenceById(trainingExerciseId));
    }

    public TrainingExerciseDTO updateTrainingExercise(TrainingExerciseDTO exercise) {
        Exercise exercise1 = exerciseRepository.getReferenceById(exercise.getIdExercise());
        TrainingExercise te = trainingExerciseRepository.getReferenceById(exercise.getId());
        te.setExercise(exercise1);
        te.setReps(exercise.getReps());
        te.setSets(exercise.getSets());
        te.setWeight(exercise.getWeight());
        return TrainingExerciseDTO.toDto(trainingExerciseRepository.save(te));
    }

    public void deleteTrainingExercise(Long trainingExerciseId) {
        trainingExerciseRepository.deleteById(trainingExerciseId);
    }

    public List<TrainingExerciseDTO> getTrainingExercisesFromListId(List<Long> ids) {
        return trainingExerciseRepository.findAll().stream().filter(t -> ids.contains(t.getId())).map(TrainingExerciseDTO::toDto).toList();
    }
}
