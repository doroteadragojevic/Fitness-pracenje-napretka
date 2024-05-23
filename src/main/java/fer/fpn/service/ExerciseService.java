package fer.fpn.service;

import fer.fpn.DTO.ExerciseDTO;
import fer.fpn.dao.Exercise;
import fer.fpn.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    ExerciseRepository exerciseRepository;

    public void newExercise(ExerciseDTO exercise) {
        exerciseRepository.save(new Exercise(exercise.getDescription(), exercise.getTitle(), null));
    }

    public List<ExerciseDTO> getAllExercises() {
        return exerciseRepository.findAll().stream().map(ExerciseDTO::toDto).toList();
    }

    public ExerciseDTO getExerciseById(Long exerciseId) {
        return ExerciseDTO.toDto(exerciseRepository.getReferenceById(exerciseId));
    }

    public ExerciseDTO updateExercise(ExerciseDTO exercise) {
        Exercise e = exerciseRepository.getReferenceById(exercise.getIdExercise());
        e.setTitle(exercise.getTitle());
        e.setDescription(exercise.getDescription());
        return ExerciseDTO.toDto(exerciseRepository.save(e));
    }

    public void deleteExercise(Long exerciseId) {
        exerciseRepository.deleteById(exerciseId);
    }
}
