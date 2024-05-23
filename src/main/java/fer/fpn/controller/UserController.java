package fer.fpn.controller;

import fer.fpn.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fer.fpn.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String users() {
		return "users";
	}

	@GetMapping("/new-user")
	public String newUser(Model model){
		model.addAttribute("user", new UserDTO());
		return "createUserForm";
	}

	@PostMapping("/new-user")
	public String newUser(@ModelAttribute("user") UserDTO user){
		userService.newUser(user);
		return "redirect:/user/";
	}
}
