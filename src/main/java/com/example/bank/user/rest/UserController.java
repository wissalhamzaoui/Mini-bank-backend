package com.example.bank.user.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.user.service.UserDto;
import com.example.bank.user.service.UserService;

@RestController
@CrossOrigin(origins = { "*" })
@RequestMapping("/api/User")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private GridFsTemplate gridFsTemplate;

	@GetMapping("/all")
	public List<UserDto> getAll() {
		return userService.getAll();
	}
	
	@GetMapping("/all/clients")
	public List<UserDto> getAllClients() {
		return userService.getAllClients();
	}

	@GetMapping("/{id}")
	public UserDto get(@PathVariable String id) {
		return userService.getUser(id);
	}

	@PostMapping("/create")
	public UserDto createUser(@RequestBody UserDto UserDto) {
		return userService.createUser(UserDto);
	}

	@PutMapping("/update/{UserId}")
	public UserDto updateUser(@RequestBody UserDto UserDto, @PathVariable String UserId) {

		return userService.updateUser(UserDto, UserId);

	}

	@DeleteMapping("/all")
	public void deleteAll() {
		userService.deleteAll();
	}

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
	}
}
