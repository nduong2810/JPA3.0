package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.security.SecureRandom;

import model.User;
import service.UserService;
import service.UserServiceImpl;
import utils.Mailer; // giả định Mailer có phương thức static send(String to, String subject, String body)

@WebServlet(urlPatterns = { "/forgot" })
public class ForgotController extends HttpServlet {
	private final UserService userService = new UserServiceImpl();
	private static final SecureRandom RND = new SecureRandom();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/forgot.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String phone = req.getParameter("phone");

		if ((email == null || email.isBlank()) && (phone == null || phone.isBlank())) {
			resp.sendRedirect(req.getContextPath() + "/forgot?err=need_input");
			return;
		}

		User u = (email != null && !email.isBlank()) ? userService.findByEmail(email) : null;
		if (u == null && phone != null && !phone.isBlank()) {
			u = userService.findByPhone(phone);
		}

		if (u == null) {
			resp.sendRedirect(req.getContextPath() + "/forgot?err=notfound");
			return;
		}
		if (u.getEmail() == null || u.getEmail().isBlank()) {
			resp.sendRedirect(req.getContextPath() + "/forgot?err=noemail");
			return;
		}

		String code = String.format("%06d", RND.nextInt(1_000_000));

		HttpSession s = req.getSession(true);
		s.setAttribute("otpUserId", u.getId());
		s.setAttribute("otpCode", code);
		s.setAttribute("otpExpiry", System.currentTimeMillis() + 5 * 60 * 1000L);

		// Gửi OTP qua email
		try {
			Mailer.send(u.getEmail(), "OTP đặt lại mật khẩu",
					"Xin chào " + (u.getFullName() != null ? u.getFullName() : u.getUserName())
							+ ",\n\nMã OTP của bạn là: " + code + "\nCó hiệu lực trong 5 phút.\n\nTrân trọng.");
		} catch (Exception e) {
			System.out.println("[DEBUG] OTP for userId=" + u.getId() + " = " + code + " (send mail failed: "
					+ e.getMessage() + ")");
		}

		resp.sendRedirect(req.getContextPath() + "/verify");
	}
}