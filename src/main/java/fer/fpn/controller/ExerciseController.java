package fer.fpn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fer.fpn.service.ExerciseService;
@RestController
@RequestMapping("/exercise")
public class ExerciseController {
		@Autowired
		private ExerciseService exerciseService;
	
}
