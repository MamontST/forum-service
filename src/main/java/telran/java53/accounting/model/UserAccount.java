package telran.java53.accounting.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Document(collection = "users")
@EqualsAndHashCode(of = "login")
public class UserAccount {
	@Id
	String login;
	@Setter
	String firstName;
	@Setter
	String lastName;
	@Setter
	String password;
	@Setter
	Set<Role> roles;
	@Setter
	LocalDateTime passwordLastChanged;
	
	public UserAccount() {
		roles = new HashSet<>();
		roles.add(Role.USER);
		passwordLastChanged = LocalDateTime.now();
	}

	public UserAccount(String login, String firstName, String lastName, String password) {
		this();
		this.login = login;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.passwordLastChanged = LocalDateTime.now();
	}
	
	public boolean addRole(String role) {
		return roles.add(Role.valueOf(role.toUpperCase()));
	}

	public boolean removeRole(String role) {
		return roles.remove(Role.valueOf(role.toUpperCase()));
	}
	
	
}
