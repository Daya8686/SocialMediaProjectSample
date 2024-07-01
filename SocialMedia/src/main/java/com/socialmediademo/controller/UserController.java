package com.socialmediademo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socialmediademo.entity.User;
import com.socialmediademo.exceptionhandler.UnableToAddUserException;
import com.socialmediademo.exceptionhandler.UserNotFoundException;
import com.socialmediademo.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public ResponseEntity<List<User>> retriveAllUsers() {
		List<User> allUsers = service.findAll();
		if (allUsers.isEmpty()) {
			throw new UserNotFoundException("Users not found");
		}
		return ResponseEntity.ok().body(allUsers);
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
		
		User userData = service.addUser(user);
		if(userData==null) {
			throw new UnableToAddUserException("Unable to add user");
		}
		URI locationUrl = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(user.getUserId())
					.toUri(); //By this we get the link in header
		return ResponseEntity.created(locationUrl).build();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retriveUserById(@PathVariable Integer id){
		User user = service.findUser(id);
		if(user==null) {
			throw new UserNotFoundException("User not found with this id:"+id);
		}
		//Implementing HATEOAS -> HyperMedia as the Engine of Application System
		//This will proved any URI from controller method
		//To Implement this we need EntityModel and WebMVCLinkBuilder
		EntityModel<User> userDetail= EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
		userDetail.add(link.withRel("all-users"));
		return userDetail;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUserById(@PathVariable int id){
		boolean removeUser = service.removeUser(id);
		if(!removeUser) {
			throw new UserNotFoundException("For Deleting user with id: "+id+" not found.");
		}
		return ResponseEntity.ok().build();
	}

}
