package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Category;
import service.CategoryService;
import service.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = { "/admin/category-edit" })
public class CategoryEditController extends HttpServlet {

	private final CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idStr = req.getParameter("id");
		if (idStr == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/category-list");
			return;
		}

		int id = Integer.parseInt(idStr); // service của bạn dùng int
		Category c = categoryService.findById(id); // OK
		if (c == null) {
			resp.sendRedirect(req.getContextPath() + "/admin/category-list");
			return;
		}

		req.setAttribute("category", c);
		req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id")); // vẫn giữ int cho service
		String name = req.getParameter("name");
		if (name == null || name.isBlank()) {
			resp.sendRedirect(req.getContextPath() + "/admin/category-edit?id=" + id + "&err=1");
			return;
		}

		Category c = new Category();
		// ⚠️ setId nhận Long nên ép kiểu:
		c.setId(Long.valueOf(id));
		c.setName(name);

		categoryService.update(c); // chữ ký update(Category) của bạn
		resp.sendRedirect(req.getContextPath() + "/admin/category-list");
	}
}