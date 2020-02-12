package com.example.h2jpa;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String getIndex(final Model model) {
		//model.addAttribute("users", userRepository.findAllByOrderByNameAsc());
		model.addAttribute("users", userRepository.findAllByOrderByNameDesc());
		return "index";
	}

	@GetMapping("/add")
	public String showSignUpForm(final User user) {
		return "add-user";
	}

	@PostMapping("/add")
	public String addUser(@Valid final User user, final BindingResult result, final Model model) {
		if (result.hasErrors()) {
			return "add-user";
		}
		userRepository.save(user);
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") final UUID id, final Model model) {
		final User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		model.addAttribute("user", user);
		return "update-user";
	}

	@PostMapping("/edit/{id}")
	public String updateUser(@PathVariable("id") final UUID id, @Valid final User user, final BindingResult result, final Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}
		userRepository.save(user);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") final UUID id, final Model model) {
		final User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);
		return "redirect:/";
	}

}
