package fer.fpn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import fer.fpn.service.TrainingService;

import java.util.List;

@Controller
@RequestMapping("/training")
public class TrainingController {
	@Autowired
	private TrainingService trainingService;

	@GetMapping("/")
	public String trainings() {
		return "training";
	}

	@GetMapping("/new-training")
	public String newTraining(){
		return "createTraining";
	}
}
