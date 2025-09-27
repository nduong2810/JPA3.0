package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = { "/waiting" })
public class WaitingController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "Vui lòng kiểm tra email/SMS để lấy mã OTP.");
		req.getRequestDispatcher("/views/hello.jsp").forward(req, resp);
	}
}