
package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.CategoryService;
import service.CategoryServiceImpl;

import java.io.IOException;

@WebServlet(urlPatterns = { "/admin/category-delete" })
public class CategoryDeleteController extends HttpServlet {

	private final CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idStr = req.getParameter("id");
		if (idStr != null) {
			int id = Integer.parseInt(idStr);
			categoryService.delete(id); // đúng chữ ký: delete(int)
		}

		resp.sendRedirect(req.getContextPath() + "/admin/category-list");
	}
}
