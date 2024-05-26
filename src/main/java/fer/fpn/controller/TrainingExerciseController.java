package fer.fpn.controller;

import fer.fpn.DTO.TrainingExerciseDTO;
import fer.fpn.service.ExerciseService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import fer.fpn.service.TrainingExerciseService;

@Controller
@RequestMapping("/trainingExercise")
public class TrainingExerciseController {
	@Autowired
	public TrainingExerciseService trainingExerciseService;

	@Autowired
	public ExerciseService exerciseService;


	@GetMapping("/new-training-exercise/{idTraining}")
	public String newExercise(@PathVariable Long idTraining, Model model){
		model.addAttribute("trainingExercise", new TrainingExerciseDTO(idTraining));
		model.addAttribute("exercises", exerciseService.getAllExercises());
		return "createTrainingExerciseForm";
	}

	@PostMapping("/new-training-exercise")
	public String newExercise(@Valid @ModelAttribute("trainingExercise") TrainingExerciseDTO exercise, BindingResult result, Model model){
		  
			  if (result.hasErrors()) {
			        model.addAttribute("exercises", exerciseService.getAllExercises());
			        return "createTrainingExerciseForm";
			    } else if (exercise.getReps() <= 0) {
			        result.rejectValue("reps", "error.reps", "Broj ponavljanja mora biti veći od 0");
			        model.addAttribute("exercises", exerciseService.getAllExercises());
			        return "createTrainingExerciseForm";
			    } else if (exercise.getSets() <= 0) {
			        result.rejectValue("sets", "error.sets", "Broj serija mora biti veći od 0");
			        model.addAttribute("exercises", exerciseService.getAllExercises());
			        return "createTrainingExerciseForm";
			    } else if (exercise.getWeight() < 0 || exercise.getWeight() > 300) {
			        result.rejectValue("weight", "error.weight", "Kilaža mora biti između 0 i 300");
			        model.addAttribute("exercises", exerciseService.getAllExercises());
			        return "createTrainingExerciseForm";
			    
		    } else {
		trainingExerciseService.newTrainingExercise(exercise);
		return "redirect:/training/" + exercise.getIdTraining();
		    }
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
	public String updateExercise(@PathVariable Long trainingExerciseId, Model model) {
		model.addAttribute("trainingExercise", trainingExerciseService.getTrainingExerciseById(trainingExerciseId));
		model.addAttribute("exercises", exerciseService.getAllExercises());
		return "trainingExerciseUpdate";
	}

	@PostMapping("/update")
	public String updateExercise(@Valid @ModelAttribute("trainingExercise") TrainingExerciseDTO exercise, BindingResult result, Model model){
		if (result.hasErrors()) {
	        model.addAttribute("exercises", exerciseService.getAllExercises());
	        return "trainingExerciseUpdate";
	    } else if (exercise.getReps() <= 0) {
	        result.rejectValue("reps", "error.reps", "Broj ponavljanja mora biti veći od 0");
	        model.addAttribute("exercises", exerciseService.getAllExercises());
	        return "trainingExerciseUpdate";
	    } else if (exercise.getSets() <= 0) {
	        result.rejectValue("sets", "error.sets", "Broj serija mora biti veći od 0");
	        model.addAttribute("exercises", exerciseService.getAllExercises());
	        return "trainingExerciseUpdate";
	    } else if (exercise.getWeight() < 0 || exercise.getWeight() > 300) {
	        result.rejectValue("weight", "error.weight", "Kilaža mora biti između 0 i 300");
	        model.addAttribute("exercises", exerciseService.getAllExercises());
	        return "trainingExerciseUpdate";
	    }else {
		trainingExerciseService.updateTrainingExercise(exercise);
		return "redirect:/training/" + exercise.getIdTraining();
	    }
	}

	@GetMapping("/delete/{trainingExerciseId}/{idTraining}")
	public String deleteExercise(@PathVariable Long trainingExerciseId, @PathVariable Long idTraining){
		trainingExerciseService.deleteTrainingExercise(trainingExerciseId);
		return "redirect:/training/" + idTraining;
	}
}
