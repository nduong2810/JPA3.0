package filter;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Set;

public class SecurityFilter implements Filter {

	private static final Set<String> PUBLIC = Set.of("/login", "/register", "/forgot", "/verify", "/reset", "/waiting",
			"/assets/", "/static/", "/resources/", "/webjars/");

	private boolean isPublic(String path) {
		if (path == null || "/".equals(path))
			return true;
		for (String p : PUBLIC)
			if (path.equals(p) || path.startsWith(p))
				return true;
		return false;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String ctx = request.getContextPath();
		String path = request.getRequestURI().substring(ctx.length());

		if (isPublic(path)) {
			chain.doFilter(req, res);
			return;
		}

		HttpSession s = request.getSession(false);
		String user = (s == null) ? null : (String) s.getAttribute("authUser");
		String role = (s == null) ? null : (String) s.getAttribute("authRole");

		if (user == null || role == null) {
			response.sendRedirect(ctx + "/login");
			return;
		}

		if (path.startsWith("/admin") && !"ADMIN".equals(role)) {
			response.sendError(403);
			return;
		}
		if (path.startsWith("/manager") && "USER".equals(role)) {
			response.sendError(403);
			return;
		}

		chain.doFilter(req, res);
	}
}