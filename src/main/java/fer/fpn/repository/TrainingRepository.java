package fer.fpn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fer.fpn.dao.Training;

public interface TrainingRepository extends JpaRepository<Training, Long>{
Optional<Training> findFirstByIdTrainingLessThanOrderByIdTrainingDesc(Long idTraining);
Optional<Training> findFirstByIdTrainingGreaterThanOrderByIdTrainingAsc(Long idTraining);
}