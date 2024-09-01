package telran.java53.security;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java53.accounting.dao.UserRepository;
import telran.java53.accounting.dto.exceptions.PasswordExpiredException;
import telran.java53.accounting.model.UserAccount;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
	final UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount userAccount = repository.findById(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));

		if (userAccount.getPasswordLastChanged() != null
				&& ChronoUnit.DAYS.between(userAccount.getPasswordLastChanged(), LocalDateTime.now()) > 60) {
			throw new PasswordExpiredException("Password expired, please change your password.");
		}
		
		Collection<String> authorities = userAccount.getRoles().stream().map(r -> "ROLE_" + r.name()).toList();
		return new User(username, userAccount.getPassword(), AuthorityUtils.createAuthorityList(authorities));
	}

}
