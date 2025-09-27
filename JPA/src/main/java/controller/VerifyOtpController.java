package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = { "/verify" })
public class VerifyOtpController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/verify.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		if (s == null) {
			resp.sendRedirect(req.getContextPath() + "/forgot");
			return;
		}

		String input = req.getParameter("code");
		String saved = (String) s.getAttribute("otpCode");
		Long exp = (Long) s.getAttribute("otpExpiry");
		Long uid = (Long) s.getAttribute("otpUserId");

		boolean ok = saved != null && saved.equals(input) && exp != null && System.currentTimeMillis() <= exp
				&& uid != null;

		if (!ok) {
			resp.sendRedirect(req.getContextPath() + "/verify?err=1");
			return;
		}

		s.setAttribute("otpVerifiedUserId", uid);
		resp.sendRedirect(req.getContextPath() + "/reset");
	}
}