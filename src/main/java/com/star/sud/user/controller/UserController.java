/**
 * 
 */
package com.star.sud.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.sud.exception.UserNotFoundException;
import com.star.sud.user.dto.UserDto;
import com.star.sud.user.response.UserApiResponse;
import com.star.sud.user.service.AuthenticationFacadeService;
import com.star.sud.user.service.UserService;

/**
 * @author Sudarshan
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_USER = "ROLE_USER";

	public enum Status {
		SUCCESS, FAILED
	}

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationFacadeService authenticationFacadeService;

	@Secured({ ROLE_ADMIN })
	@GetMapping
	public UserApiResponse listUsers() {
		log.info(String.format("received request to list user %s",
				authenticationFacadeService.getAuthentication().getPrincipal()));
		return new UserApiResponse(HttpStatus.OK, Status.SUCCESS.name(), userService.getUsers());
	}

	@Secured({ ROLE_ADMIN })
	@PostMapping
	public UserApiResponse createUser(@RequestBody UserDto user) throws Exception {
		log.info(String.format("received request to create user %s",
				authenticationFacadeService.getAuthentication().getPrincipal()));
		return new UserApiResponse(HttpStatus.OK, Status.SUCCESS.name(), userService.createUser(user));
	}

	@Secured({ ROLE_ADMIN, ROLE_USER })
	@GetMapping(value = "/{id}")
	public UserApiResponse getUser(@PathVariable long id) throws UserNotFoundException {
		log.info(String.format("received request to update user %s",
				authenticationFacadeService.getAuthentication().getPrincipal()));
		return new UserApiResponse(HttpStatus.OK, Status.SUCCESS.name(), userService.findOne(id));
	}

	@Secured({ ROLE_ADMIN })
	@DeleteMapping(value = "/{id}")
	public UserApiResponse deleteUsers(@PathVariable(value = "id") Long id) {
		log.info(String.format("received request to delete user %s",
				authenticationFacadeService.getAuthentication().getPrincipal()));
		userService.delete(id);
		return new UserApiResponse(HttpStatus.OK, Status.SUCCESS.name());
	}

}
