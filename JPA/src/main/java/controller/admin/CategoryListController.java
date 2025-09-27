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
import java.util.List;

@WebServlet(urlPatterns = { "/admin/category-list" })
public class CategoryListController extends HttpServlet {

	private final CategoryService categoryService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Category> categories = categoryService.findAll();
		req.setAttribute("categories", categories);
		req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
	}
}