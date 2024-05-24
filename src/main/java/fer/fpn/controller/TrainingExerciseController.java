package fer.fpn.controller;

import fer.fpn.DTO.TrainingExerciseDTO;
import fer.fpn.service.ExerciseService;
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

	@Autowired
	private ExerciseService exerciseService;


	@GetMapping("/new-training-exercise/{idTraining}")
	public String newExercise(@PathVariable Long idTraining, Model model){
		model.addAttribute("trainingExercise", new TrainingExerciseDTO(idTraining));
		model.addAttribute("exercises", exerciseService.getAllExercises());
		return "createTrainingExerciseForm";
	}

	@PostMapping("/new-training-exercise")
	public String newExercise(@ModelAttribute("trainingExercise") TrainingExerciseDTO exercise){

		trainingExerciseService.newTrainingExercise(exercise);
		return "redirect:/training/" + exercise.getIdTraining();
	}

	@GetMapping("/{trainingExerciseId}")
	public String getExercise(@PathVariable Long trainingExerciseId, Model model){
		TrainingExerciseDTO currentTrainingExercise = trainingExerciseService.getTrainingExerciseById(trainingExerciseId);
	    TrainingExerciseDTO prevTrainingExercise = trainingExerciseService.findPreviousTrainingExercise(trainingExerciseId);
	    TrainingExerciseDTO nextTrainingExercise = trainingExerciseService.findNextTrainingExercise(trainingExerciseId);

	    model.addAttribute("trainingExercise", currentTrainingExercise);
	    model.addAttribute("prevTrainingExerciseId", prevTrainingExercise != null ? prevTrainingExercise.getId() : null);
	    model.addAttribute("nextTrainingExerciseId", nextTrainingExercise != null ? nextTrainingExercise.getId() : null);

	    return "trainingExercise";
	}

	@GetMapping("/update/{trainingExerciseId}")
	public String updateExercise(@PathVariable Long trainingExerciseId, Model model){
		model.addAttribute("trainingExercise", trainingExerciseService.getTrainingExerciseById(trainingExerciseId));
		model.addAttribute("exercises", exerciseService.getAllExercises());
		return "trainingExerciseUpdate";
	}

	@PostMapping("/update")
	public String updateExercise(@ModelAttribute("trainingExercise") TrainingExerciseDTO exercise){
		trainingExerciseService.updateTrainingExercise(exercise);
		return "redirect:/training/" + exercise.getIdTraining();
	}

	@GetMapping("/delete/{trainingExerciseId}/{idTraining}")
	public String deleteExercise(@PathVariable Long trainingExerciseId, @PathVariable Long idTraining){
		trainingExerciseService.deleteTrainingExercise(trainingExerciseId);
		return "redirect:/training/" + idTraining;
	}
}
