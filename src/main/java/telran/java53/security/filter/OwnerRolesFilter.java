package telran.java53.security.filter;

import java.io.IOException;
import java.security.Principal;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OwnerRolesFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String path = request.getServletPath();
		String method = request.getMethod();

		Principal principal = request.getUserPrincipal();
		String login = (String) request.getAttribute("login");
		
		if (principal != null) {
			if (login != null) {
				if ((path.matches("/account/user/[^/]+") || (path.matches("/account/password"))) && method.equalsIgnoreCase("PUT")) {
					if ((Boolean) request.getAttribute("isOwner")) {
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
