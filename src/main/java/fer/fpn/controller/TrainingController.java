package fer.fpn.controller;

import fer.fpn.DTO.TrainingDTO;
import fer.fpn.service.TrainingExerciseService;
import fer.fpn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fer.fpn.service.TrainingService;

@Controller
@RequestMapping("/training")
public class TrainingController {
	@Autowired
	public TrainingService trainingService;

	@Autowired
	public UserService userService;

	@Autowired
	public TrainingExerciseService trainingExerciseService;

	@GetMapping("/")
	public String trainings(Model model) {
		model.addAttribute("trainings", trainingService.getAllTrainings());
		return "trainings";
	}

	@GetMapping("/new-training")
	public String newTraining(Model model){
		model.addAttribute("training", new TrainingDTO());
		model.addAttribute("users", userService.getAllUsers());
		return "createTrainingForm";
	}

	@PostMapping("/new-training")
	public String newTraining(@ModelAttribute("training") TrainingDTO training){

		trainingService.newTraining(training);
		return "redirect:/training/";
	}

	@GetMapping("/{idTraining}")
	public String getTraining(@PathVariable Long idTraining, Model model){
		TrainingDTO previousTraining = trainingService.findPreviousTraining(idTraining);
	    TrainingDTO nextTraining = trainingService.findNextTraining(idTraining);
		TrainingDTO training = trainingService.getTrainingById(idTraining);
		model.addAttribute("training", training);
		model.addAttribute("tes", trainingExerciseService.getExerciseTrainingsForTraining(idTraining));
		model.addAttribute("prevTrainingId", previousTraining != null ? previousTraining.getIdTraining() : null);
	    model.addAttribute("nextTrainingId", nextTraining != null ? nextTraining.getIdTraining() : null);
		return "training";
	}

	@GetMapping("/update/{idTraining}")
	public String updateTraining(@PathVariable Long idTraining, Model model){
		model.addAttribute("training", trainingService.getTrainingById(idTraining));
		model.addAttribute("users", userService.getAllUsers());
		return "trainingUpdate";
	}

	@PostMapping("/update")
	public String updateTraining(@ModelAttribute("training") TrainingDTO training){
		TrainingDTO u = trainingService.updateTraining(training);
		return "redirect:/training/" + u.getIdTraining();
	}

	@GetMapping("/delete/{idTraining}")
	public String deleteTraining(@PathVariable Long idTraining){
		trainingService.deleteTraining(idTraining);
		return "redirect:/training/";
	}
}
