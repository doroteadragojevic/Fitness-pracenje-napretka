package fer.fpn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fer.fpn.service.TrainingExerciseService;

@RestController
@RequestMapping("/trainingExercise")
public class TrainingExerciseController {
	@Autowired
	private TrainingExerciseService trainingExerciseService;
}
