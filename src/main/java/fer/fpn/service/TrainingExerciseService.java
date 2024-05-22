package fer.fpn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fer.fpn.repository.TrainingExerciseRepository;

@Service
public class TrainingExerciseService {

    @Autowired
    TrainingExerciseRepository trainingExerciseRepository;

}
