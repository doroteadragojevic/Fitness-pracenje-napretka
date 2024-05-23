package fer.fpn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fer.fpn.dao.Exercise;
import fer.fpn.dao.Training;
import fer.fpn.dao.User;

public interface TrainingRepository extends JpaRepository<Training, Long>{

}