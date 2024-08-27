package telran.java53.security.model;

import java.security.Principal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserPrincipal implements Principal {

	private String name;
	private Set<String> roles;
	
}
