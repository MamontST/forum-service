package telran.java53.accounting.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import org.springframework.http.HttpStatus;

import lombok.RequiredArgsConstructor;
import telran.java53.accounting.dto.RolesDto;
import telran.java53.accounting.dto.UserDto;
import telran.java53.accounting.dto.UserEditDto;
import telran.java53.accounting.dto.UserRegisterDto;
import telran.java53.accounting.service.UserAccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {

	final UserAccountService userAccountService;

	@PostMapping("/register")
	public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
		return userAccountService.register(userRegisterDto);
	}

	@GetMapping("/user/{user}")
	public UserDto getUser(@PathVariable("user") String login) {
		return userAccountService.getUser(login);
	}

	@PostMapping("/login")
	public UserDto getLogin(Principal principal) {
		return userAccountService.getUser(principal.getName());
	}

	@DeleteMapping("/user/{user}")
	public UserDto removeUser(@PathVariable("user") String login) {

		return userAccountService.removeUser(login);
	}

	@PutMapping("/user/{user}")
	public UserDto updateUser(@PathVariable("user") String login, @RequestBody UserEditDto userEditDto) {

		return userAccountService.updateUser(login, userEditDto);
	}

	@PutMapping("/user/{user}/role/{role}")
	public RolesDto addRole(@PathVariable("user") String login, @PathVariable String role) {
		return userAccountService.changeRolesList(login, role, true);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public RolesDto removeRole(@PathVariable("user") String login, @PathVariable String role) {
		return userAccountService.changeRolesList(login, role, false);
	}
	
	@PutMapping("/password")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void changePassword(Principal principal, @RequestHeader("X-Password") String newPassword) {
		userAccountService.changePassword(principal.getName(), newPassword);
	}

}
