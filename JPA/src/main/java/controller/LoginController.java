package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import model.User;
import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	private final UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User u = userService.findByUsernameAndPassword(username, password);
		if (u == null) {
			resp.sendRedirect(req.getContextPath() + "/login?err=1");
			return;
		}

		HttpSession session = req.getSession(true);
		session.setAttribute("authUserId", u.getId());
		session.setAttribute("authUser", u.getUserName());
		session.setAttribute("authRole", u.getRoleName()); // "ADMIN" | "MANAGER" | "USER"

		String ctx = req.getContextPath();
		// Tránh 404: tạm thời đưa ADMIN về /items (trang chắc chắn có)
		switch (u.getRoleName()) {
		case "ADMIN" -> resp.sendRedirect(ctx + "/items");
		case "MANAGER" -> resp.sendRedirect(ctx + "/items");
		default -> resp.sendRedirect(ctx + "/items");
		}
	}
}