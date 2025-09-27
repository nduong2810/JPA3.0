package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

import service.UserService;
import service.UserServiceImpl;

@WebServlet(urlPatterns = { "/reset" })
public class ResetPasswordController extends HttpServlet {
	private final UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		if (s == null || s.getAttribute("otpVerifiedUserId") == null) {
			resp.sendRedirect(req.getContextPath() + "/forgot?err=session");
			return;
		}
		req.getRequestDispatcher("/views/reset.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		if (s == null) {
			resp.sendRedirect(req.getContextPath() + "/forgot");
			return;
		}
		Long userId = (Long) s.getAttribute("otpVerifiedUserId");
		if (userId == null) {
			resp.sendRedirect(req.getContextPath() + "/forgot");
			return;
		}

		String pass = req.getParameter("password");
		String conf = req.getParameter("confirm");
		if (pass == null || pass.isBlank() || !pass.equals(conf)) {
			resp.sendRedirect(req.getContextPath() + "/reset?err=confirm");
			return;
		}

		userService.updatePassword(userId, pass); // nếu hash: hash trước khi truyền

		// dọn session OTP
		s.removeAttribute("otpUserId");
		s.removeAttribute("otpCode");
		s.removeAttribute("otpExpiry");
		s.removeAttribute("otpVerifiedUserId");

		resp.sendRedirect(req.getContextPath() + "/login?reset=1");
	}
}