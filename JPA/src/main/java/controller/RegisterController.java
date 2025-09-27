package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private final UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String fullName = req.getParameter("fullName");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		String confirm = req.getParameter("confirm");

		if (username == null || username.isBlank() || password == null || password.isBlank() || email == null
				|| email.isBlank() || phone == null || phone.isBlank()) {
			resp.sendRedirect(req.getContextPath() + "/register?err=invalid");
			return;
		}
		if (!password.equals(confirm)) {
			resp.sendRedirect(req.getContextPath() + "/register?err=confirm");
			return;
		}
		if (userService.existsByUsername(username)) {
			resp.sendRedirect(req.getContextPath() + "/register?err=existsUser");
			return;
		}
		if (userService.existsByEmail(email)) {
			resp.sendRedirect(req.getContextPath() + "/register?err=existsEmail");
			return;
		}
		if (userService.existsByPhone(phone)) {
			resp.sendRedirect(req.getContextPath() + "/register?err=existsPhone");
			return;
		}

		// roleId = 3 (USER)
		userService.registerUser(username, fullName, email, phone, password, 3);

		resp.sendRedirect(req.getContextPath() + "/login?registered=1");
	}
}