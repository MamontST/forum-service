package telran.java53.accounting.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	public UserDto getLogin(@RequestHeader("Authorization") String login) {
		return userAccountService.getUser(login);
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
		
		boolean isAddRoles = true;
		return userAccountService.changeRolesList(login, role, isAddRoles);
	}

	@DeleteMapping("/user/{user}/role/{role}")
	public RolesDto removeRole(@PathVariable("user") String login, @PathVariable String role) {
		
		boolean isAddRoles = false;
		return userAccountService.changeRolesList(login, role, isAddRoles);
	}
	
	@PutMapping("/password")
	public void changePassword(@RequestHeader("Authorization") String login, @RequestHeader(value = "X-Password") String newPassword) {
		// TODO Auto-generated method stub

	}

}
