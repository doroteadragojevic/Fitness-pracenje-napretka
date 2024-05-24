package fer.fpn.repository;

import fer.fpn.dao.TrainingExercise;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TrainingExerciseRepository extends JpaRepository<TrainingExercise, Long>{
	 Optional<TrainingExercise> findFirstByIdLessThanOrderByIdDesc(Long id);
	  Optional<TrainingExercise> findFirstByIdGreaterThanOrderByIdAsc(Long id);
}
