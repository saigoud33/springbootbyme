package com.rest.restsfull.restfullwebservice.user;

import java.net.URI;
import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.restsfull.restfullwebservice.user.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> findAll() {
		return service.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		User findUser = service.findOne(id);
		if (findUser == null) {
			throw new UserNotFoundException(" ID  - " + id);
		}
		EntityModel<User> model = EntityModel.of(findUser);

		WebMvcLinkBuilder lilnkToUsers = linkTo(methodOn(this.getClass()).findAll());
		model.add(lilnkToUsers.withRel("all-users"));
		return model;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> save(@RequestBody User user) {
		System.out.println("Saving to DB....");
		User saved = service.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(saved.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	public void deleteUser(@PathVariable int id) {
		
	}
}
