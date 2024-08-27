package telran.java53.accounting.service;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
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
import telran.java53.accounting.model.User;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService, CommandLineRunner {

	final UserRepository userRepository;
	final ModelMapper modelMapper;

	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		if (userRepository.existsById(userRegisterDto.getLogin())) {
			throw new UserExistsException();
		}
		User user = modelMapper.map(userRegisterDto, User.class);
		String password = BCrypt.hashpw(userRegisterDto.getPassword(), BCrypt.gensalt());
		user.setPassword(password);
		userRepository.save(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto getUser(String login) {
		User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto removeUser(String login) {
		User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		userRepository.delete(user);
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(String login, UserEditDto userEditDto) {
		User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
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
		User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
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
		User user = userRepository.findById(login).orElseThrow(UserNotFoundException::new);
		String password = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		user.setPassword(password);
		userRepository.save(user);

	}

	@Override
	public void run(String... args) throws Exception {
		if(!userRepository.existsById("admin")) {
			String password = BCrypt.hashpw("admin", BCrypt.gensalt());
			User user = new User("admin", "", "", password);
			user.addRole(Role.MODERATOR.name());
			user.addRole(Role.ADMINISTRATOR.name());
			userRepository.save(user);
		}
		
	}

}