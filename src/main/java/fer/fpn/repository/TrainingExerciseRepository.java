package fer.fpn.repository;

import fer.fpn.dao.TrainingExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import fer.fpn.dao.Training;

public interface TrainingExerciseRepository extends JpaRepository<TrainingExercise, Long>{

}
