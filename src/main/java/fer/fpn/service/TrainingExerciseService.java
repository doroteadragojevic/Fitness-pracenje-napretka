package fer.fpn.service;

import fer.fpn.DTO.TrainingExerciseDTO;
import fer.fpn.dao.Exercise;
import fer.fpn.dao.Training;
import fer.fpn.dao.TrainingExercise;
import fer.fpn.repository.ExerciseRepository;
import fer.fpn.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.fpn.repository.TrainingExerciseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingExerciseService {

    @Autowired
    TrainingExerciseRepository trainingExerciseRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    TrainingRepository trainingRepository;

    public void newTrainingExercise(TrainingExerciseDTO exercise) {
        Exercise exercise1 = exerciseRepository.getReferenceById(exercise.getIdExercise());
        Training training = trainingRepository.getReferenceById(exercise.getIdTraining());
        trainingExerciseRepository.save(new TrainingExercise(exercise1, exercise.getReps(), exercise.getSets(), exercise.getWeight(), training));
    }

    public TrainingExerciseDTO getTrainingExerciseById(Long trainingExerciseId) {
    	Optional<TrainingExercise> trainingExerciseOptional = trainingExerciseRepository.findById(trainingExerciseId);
        return trainingExerciseOptional.map(TrainingExerciseDTO::toDto).orElse(null);
    }
    public TrainingExerciseDTO findPreviousTrainingExercise(Long trainingExerciseId) {
        Optional<TrainingExercise> trainingExerciseOptional = trainingExerciseRepository.findFirstByIdLessThanOrderByIdDesc(trainingExerciseId);
        return trainingExerciseOptional.map(TrainingExerciseDTO::toDto).orElse(null);
    }

    public TrainingExerciseDTO findNextTrainingExercise(Long trainingExerciseId) {
        Optional<TrainingExercise> trainingExerciseOptional = trainingExerciseRepository.findFirstByIdGreaterThanOrderByIdAsc(trainingExerciseId);
        return trainingExerciseOptional.map(TrainingExerciseDTO::toDto).orElse(null);
    }

    public TrainingExerciseDTO updateTrainingExercise(TrainingExerciseDTO exercise) {
        TrainingExercise te = trainingExerciseRepository.getReferenceById(exercise.getId());
        Exercise exercise1 = exerciseRepository.getReferenceById(exercise.getIdExercise());
        te.setSets(exercise.getSets());
        te.setReps(exercise.getReps());
        te.setWeight(exercise.getWeight());
        te.setExercise(exercise1);
        return TrainingExerciseDTO.toDto(trainingExerciseRepository.save(te));
    }

    public void deleteTrainingExercise(Long trainingExerciseId) {
        trainingExerciseRepository.deleteById(trainingExerciseId);
    }

    public List<TrainingExerciseDTO> getExerciseTrainingsForTraining(Long idTraining) {
        return trainingExerciseRepository.findAll().stream().filter(te -> te.getTraining().getIdTraining()==idTraining).sorted((te1, te2) -> te1.getId().compareTo(te2.getId())).map(TrainingExerciseDTO::toDto).toList();
    }
}
