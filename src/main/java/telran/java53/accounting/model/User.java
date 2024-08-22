package telran.java53.accounting.model;


import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Document(collection = "users")
@EqualsAndHashCode(of = "login")
@NoArgsConstructor
public class User {
	String login;
	String firstName;
	String lastName;
	String password;
	@Singular
	Set<String> roles;
}
