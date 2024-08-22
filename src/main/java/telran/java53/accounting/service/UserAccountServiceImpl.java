package telran.java53.accounting.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import telran.java53.accounting.dao.UserRepository;
import telran.java53.accounting.dto.RolesDto;
import telran.java53.accounting.dto.UserDto;
import telran.java53.accounting.dto.UserEditDto;
import telran.java53.accounting.dto.UserRegisterDto;

@Component
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService{
	
	final UserRepository userRepository;
	final ModelMapper modelMapper;
	
	@Override
	public UserDto register(UserRegisterDto userRegisterDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUser(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto removeUser(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updateUser(String login, UserEditDto userEditDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RolesDto changeRolesList(String login, String role, boolean isAddRoles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changePassword(String login, String newPassword) {
		// TODO Auto-generated method stub
		
	}

}
