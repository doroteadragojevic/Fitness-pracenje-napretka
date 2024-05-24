package fer.fpn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fer.fpn.dao.Exercise;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>{
	  Optional<Exercise> findFirstByIdExerciseLessThanOrderByIdExerciseDesc(Long idExercise);
	  Optional<Exercise> findFirstByIdExerciseGreaterThanOrderByIdExerciseAsc(Long idExercise);
}
