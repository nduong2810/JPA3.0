
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

@WebServlet(urlPatterns = { "/admin/category-add" })
public class CategoryAddController extends HttpServlet {

	private final CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		if (name == null || name.isBlank()) {
			resp.sendRedirect(req.getContextPath() + "/admin/category-add?err=1");
			return;
		}

		Category c = new Category();
		c.setName(name);

		categoryService.create(c); // đúng chữ ký: create(Category)
		resp.sendRedirect(req.getContextPath() + "/admin/category-list");
	}
}
