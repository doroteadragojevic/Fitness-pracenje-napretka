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
	public String users(Model model) {
		model.addAttribute("users", userService.getAllUsers());
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
	
	@GetMapping("/{userId}")
	public String getUser(@PathVariable Long userId, Model model){
        UserDTO prevUser = userService.findPreviousUser(userId);
        UserDTO nextUser = userService.findNextUser(userId);
		model.addAttribute("user", userService.getUserById(userId));
		model.addAttribute("prevUserId", prevUser != null ? prevUser.getUserId() : null);
        model.addAttribute("nextUserId", nextUser != null ? nextUser.getUserId() : null);

		return "user";
	}

	@GetMapping("/update/{userId}")
	public String updateUser(@PathVariable Long userId, Model model){
		model.addAttribute("user", userService.getUserById(userId));
		return "userUpdate";
	}

	@PostMapping("/update")
	public String updateUser(@ModelAttribute("user") UserDTO user){
		UserDTO u = userService.updateUser(user);
		return "redirect:/user/" + u.getUserId();
	}

	@GetMapping("/delete/{userId}")
	public String deleteUser(@PathVariable Long userId){
		userService.deleteUser(userId);
		return "redirect:/user/";
	}
}
