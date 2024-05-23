package fer.fpn.controller;

import fer.fpn.DTO.ExerciseDTO;
import fer.fpn.DTO.TrainingExerciseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import fer.fpn.service.TrainingExerciseService;

@Controller
@RequestMapping("/trainingExercise")
public class TrainingExerciseController {
	@Autowired
	private TrainingExerciseService trainingExerciseService;

	@GetMapping("/")
	public String trainings(Model model) {
		model.addAttribute("trainingExercises", trainingExerciseService.getAllTrainingExercises());
		return "trainingexercises";
	}

	@GetMapping("/new-training-exercise")
	public String newExercise(Model model){
		model.addAttribute("trainingExercise", new TrainingExerciseDTO());
		return "createTrainingExerciseForm";
	}

	@PostMapping("/new-training-exercise")
	public String newExercise(@ModelAttribute("trainingExercise") TrainingExerciseDTO exercise){
		trainingExerciseService.newTrainingExercise(exercise);
		return "redirect:/trainingExercise/";
	}

	@GetMapping("/{trainingExerciseId}")
	public String getExercise(@PathVariable Long trainingExerciseId, Model model){
		model.addAttribute("trainingExercise", trainingExerciseService.getTrainingExerciseById(trainingExerciseId));
		return "trainingExercise";
	}

	@GetMapping("/update/{trainingExerciseId}")
	public String updateExercise(@PathVariable Long trainingExerciseId, Model model){
		model.addAttribute("trainingExercise", trainingExerciseService.getTrainingExerciseById(trainingExerciseId));
		return "trainingExerciseUpdate";
	}

	@PostMapping("/update")
	public String updateExercise(@ModelAttribute("trainingExercise") TrainingExerciseDTO exercise){
		TrainingExerciseDTO u = trainingExerciseService.updateTrainingExercise(exercise);
		return "redirect:/trainingExercise/" + u.getId();
	}

	@GetMapping("/delete/{trainingExerciseId}")
	public String deleteExercise(@PathVariable Long trainingExerciseId){
		trainingExerciseService.deleteTrainingExercise(trainingExerciseId);
		return "redirect:/trainingExercise/";
	}
}
