package com.rest.restsfull.restfullwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.restsfull.restfullwebservice.user.exception.UserNotFoundException;

@RestController
public class UserJpaResource {
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PostRepository postRepo;

	@GetMapping("/jpa/users")
	public List<User> findAll() {
		return userRepo.findAll();
	}

	@GetMapping("/jpa/users/{id}")
	public Optional<User> getUser(@PathVariable int id) {
		return userRepo.findById(id);
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> save(@RequestBody User user) {
		User saved = userRepo.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(saved.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepo.deleteById(id);
	}

	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> getAllUserPosts(@PathVariable int id) {
		Optional<User> findById = userRepo.findById(id);

		if (!findById.isPresent())
			throw new UserNotFoundException(" ID  - " + id);

		return findById.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {

		Optional<User> findById = userRepo.findById(id);

		if (!findById.isPresent())
			throw new UserNotFoundException(" ID  - " + id);

		User user = findById.get();
		post.setUser(user);
		
		postRepo.save(post);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(post.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}
}
