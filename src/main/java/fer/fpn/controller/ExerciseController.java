package fer.fpn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fer.fpn.service.ExerciseService;
@Controller
@RequestMapping("/exercise")
public class ExerciseController {
		@Autowired
		private ExerciseService exerciseService;


	@GetMapping("/")
	public String trainings() {
		return "exercise";
	}
}
