package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import model.User;
import service.ProfileService;
import service.ProfileServiceImpl;

@WebServlet(urlPatterns = { "/profile" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB -> lưu tạm bộ nhớ trước khi đổ ra đĩa
		maxFileSize = 5L * 1024 * 1024, // 5MB
		maxRequestSize = 10L * 1024 * 1024 // 10MB
)
public class ProfileController extends HttpServlet {

	private final ProfileService profileService = new ProfileServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession s = req.getSession(false);
		if (s == null || s.getAttribute("authUserId") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		long userId = ((Number) s.getAttribute("authUserId")).longValue();
		User u = profileService.findById(userId);
		req.setAttribute("user", u);
		req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession s = req.getSession(false);
		if (s == null || s.getAttribute("authUserId") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		long userId = ((Number) s.getAttribute("authUserId")).longValue();

		String fullName = req.getParameter("fullName");
		String phone = req.getParameter("phone");

		// Xử lý file upload (optional)
		Part avatarPart = null;
		try {
			avatarPart = req.getPart("avatar");
		} catch (Exception ignored) {
		}

		String avatarUrl = null;
		if (avatarPart != null && avatarPart.getSize() > 0) {
			String fileName = Path.of(avatarPart.getSubmittedFileName()).getFileName().toString();
			String ext = "";
			int dot = fileName.lastIndexOf('.');
			if (dot >= 0)
				ext = fileName.substring(dot); // .png/.jpg...

			// Tạo tên file mới theo userId + timestamp
			String newName = "u" + userId + "_" + System.currentTimeMillis() + ext;

			// Lưu vào thư mục /uploads/avatars trong webapp (có thể đổi nếu muốn lưu ngoài)
			String uploadRoot = getServletContext().getRealPath("/uploads/avatars");
			Files.createDirectories(Paths.get(uploadRoot));
			File target = new File(uploadRoot, newName);

			try (InputStream in = avatarPart.getInputStream(); OutputStream out = new FileOutputStream(target)) {
				in.transferTo(out);
			}

			// URL để browser truy cập
			avatarUrl = req.getContextPath() + "/uploads/avatars/" + newName;
		}

		profileService.updateProfile(userId, fullName, phone, avatarUrl);

		resp.sendRedirect(req.getContextPath() + "/profile?ok=1");
	}
}
