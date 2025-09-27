
package controller.items;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import model.Item;
import service.ItemService;
import service.ItemServiceImpl;

@WebServlet(urlPatterns = { "/items", "/items/create", "/items/update", "/items/delete" })
public class ItemsController extends HttpServlet {

	private final ItemService itemService = new ItemServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession s = req.getSession(false);
		String role = (String) s.getAttribute("authRole");
		String user = (String) s.getAttribute("authUser");

		List<Item> items = ("ADMIN".equals(role) || "MANAGER".equals(role)) ? itemService.findAll()
				: itemService.findByOwner(user);

		req.setAttribute("items", items);
		req.getRequestDispatcher("/views/items/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = req.getServletPath();
		HttpSession s = req.getSession(false);
		String role = (String) s.getAttribute("authRole");
		String user = (String) s.getAttribute("authUser");

		if ("/items/create".equals(path)) {
			String name = req.getParameter("name");
			String image = req.getParameter("image");
			int quantity = Integer.parseInt(req.getParameter("quantity"));

			// ✅ Luôn gán owner = người đang đăng nhập (mọi role)
			String owner = user;

			itemService.create(name, image, quantity, owner);
			resp.sendRedirect(req.getContextPath() + "/items");
			return;
		}

		if ("/items/update".equals(path)) {
			long id = Long.parseLong(req.getParameter("id"));
			Item it = itemService.findById(id);
			if (it == null) {
				resp.sendError(404);
				return;
			}

			boolean can = "ADMIN".equals(role) || "MANAGER".equals(role) || user.equals(it.getOwner());
			if (!can) {
				resp.sendError(403);
				return;
			}

			String name = req.getParameter("name");
			String image = req.getParameter("image");
			int quantity = Integer.parseInt(req.getParameter("quantity"));

			itemService.update(id, name, image, quantity);
			resp.sendRedirect(req.getContextPath() + "/items");
			return;
		}

		if ("/items/delete".equals(path)) {
			long id = Long.parseLong(req.getParameter("id"));
			Item it = itemService.findById(id);
			if (it == null) {
				resp.sendError(404);
				return;
			}

			boolean can = "ADMIN".equals(role) || "MANAGER".equals(role) || user.equals(it.getOwner());
			if (!can) {
				resp.sendError(403);
				return;
			}

			itemService.delete(id);
			resp.sendRedirect(req.getContextPath() + "/items");
		}
	}
}
