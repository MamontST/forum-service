package telran.java53.security.filter;

import java.io.IOException;
import java.security.Principal;
import java.util.Set;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import telran.java53.accounting.dao.UserRepository;
import telran.java53.accounting.model.Role;
import telran.java53.accounting.model.User;

@Component
@RequiredArgsConstructor
@Order(20)
public class AdminManagingRolesFilter implements Filter {
	final UserRepository userRepository;

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String path = request.getServletPath();
		String method = request.getMethod();

		Principal principal = request.getUserPrincipal();

		if (principal != null) {

			String login = principal.getName();
			
			boolean isOwner = path.contains(login);
			request.setAttribute("isOwner", isOwner);
			request.setAttribute("login", login);
			
			User user = userRepository.findById(login).orElseThrow(RuntimeException::new);
			Set<Role> roles = user.getRoles();

			if (login != null) {
				if (path.matches("/account/user/[^/]+") && method.equalsIgnoreCase("DELETE")) {
					if (roles.contains(Role.ADMINISTRATOR) || (Boolean) request.getAttribute("isOwner")) {
						chain.doFilter(request, response);
						return;
					} else {
						response.sendError(403);
						return;
					}
				}

				if (path.matches("/account/user/[^/]+/role/[^/]+")
						&& (method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("DELETE"))) {
					if (roles.contains(Role.ADMINISTRATOR)) {
						chain.doFilter(request, response);
						return;
					} else {
						response.sendError(403);
						return;
					}
				}
			}

		}
		chain.doFilter(request, response);
	}
}
