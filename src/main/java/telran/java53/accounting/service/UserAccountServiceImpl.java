package telran.java53.accounting.service;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java53.accounting.dao.UserRepository;
import telran.java53.accounting.dto.RolesDto;
import telran.java53.accounting.dto.UserDto;
import telran.java53.accounting.dto.UserEditDto;
import telran.java53.accounting.dto.UserRegisterDto;
import telran.java53.accounting.dto.exceptions.UserExistsException;
import telran.java53.accounting.dto.exceptions.UserNotFoundException;
import telran.java53.accounting.model.Role;
import telran.java53.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService,CommandLineRunner {

	final UserRepository userRepository;
	final ModelMapper modelMapper;
	final PasswordEncoder passwordEncoder;
	@Value("${password.period:30}")
	long passwordPeriod;

	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		if (userRepository.existsById(userRegisterDto.getLogin())) {
			throw new UserExistsException();
		}
		UserAccount user = modelMapper.map(userRegisterDto, UserAccount.class);
		String password = passwordEncoder.encode(userRegisterDto.getPassword());
		user.setPassword(password);
		user.setPasswordExpDate(LocalDate.now().plusDays(passwordPeriod));
		userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUser(String login) {
		UserAccount user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto removeUser(String login) {
		UserAccount user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		userRepository.delete(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UserEditDto userEditDto) {
		UserAccount user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		if (userEditDto.getFirstName() != null) {
			user.setFirstName(userEditDto.getFirstName());
		}
		if (userEditDto.getLastName() != null) {
			user.setLastName(userEditDto.getLastName());
		}
		userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
		UserAccount user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		boolean res;
		if (isAddRole) {
			res = user.addRole(role);
		} else {
			res = user.removeRole(role);
		}
		if (res) {
			userRepository.save(user);
		}
		return modelMapper.map(user, RolesDto.class);
	}

	@Override
	public void changePassword(String login, String newPassword) {
		UserAccount user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		String password = passwordEncoder.encode(newPassword);
		user.setPassword(password);
		user.setPasswordExpDate(LocalDate.now().plusDays(passwordPeriod));
		userRepository.save(user);

	}
	
	@Override
	public void run(String... args) throws Exception {
		if(!userRepository.existsById("admin")) {
			String password = passwordEncoder.encode("admin");
			UserAccount user = new UserAccount("admin", "", "", password);
			user.addRole(Role.MODERATOR.name());
			user.addRole(Role.ADMINISTRATOR.name());
			user.setPasswordExpDate(LocalDate.now().plusDays(passwordPeriod));
			userRepository.save(user);
		}
		
	}

}