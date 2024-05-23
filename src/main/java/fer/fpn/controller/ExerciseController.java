package fer.fpn.controller;

import fer.fpn.DTO.ExerciseDTO;
import fer.fpn.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fer.fpn.service.ExerciseService;
@Controller
@RequestMapping("/exercise")
public class ExerciseController {
		@Autowired
		private ExerciseService exerciseService;


	@GetMapping("/")
	public String exercises(Model model) {
		model.addAttribute("exercises", exerciseService.getAllExercises());
		return "exercises";
	}

	@GetMapping("/new-exercise")
	public String newExercise(Model model){
		model.addAttribute("exercise", new ExerciseDTO());
		return "createExerciseForm";
	}

	@PostMapping("/new-exercise")
	public String newExercise(@ModelAttribute("exercise") ExerciseDTO exercise){
		exerciseService.newExercise(exercise);
		return "redirect:/exercise/";
	}

	@GetMapping("/{exerciseId}")
	public String getExercise(@PathVariable Long exerciseId, Model model){
		model.addAttribute("exercise", exerciseService.getExerciseById(exerciseId));
		return "exercise";
	}

	@GetMapping("/update/{exerciseId}")
	public String updateExercise(@PathVariable Long exerciseId, Model model){
		model.addAttribute("exercise", exerciseService.getExerciseById(exerciseId));
		return "exerciseUpdate";
	}

	@PostMapping("/update")
	public String updateExercise(@ModelAttribute("exercise") ExerciseDTO exercise){
		ExerciseDTO u = exerciseService.updateExercise(exercise);
		return "redirect:/exercise/" + u.getIdExercise();
	}

	@GetMapping("/delete/{exerciseId}")
	public String deleteExercise(@PathVariable Long exerciseId){
		exerciseService.deleteExercise(exerciseId);
		return "redirect:/exercise/";
	}
}
