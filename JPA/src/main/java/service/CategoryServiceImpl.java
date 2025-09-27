package service;

import java.util.List;
import dao.CategoryDaoJPA;
import model.Category;

public class CategoryServiceImpl implements CategoryService {
	private final CategoryDaoJPA dao = new CategoryDaoJPA();

	public List<Category> findAll() {
		return dao.findAll();
	}

	public Category findById(int id) {
		return dao.findById(id);
	}

	public boolean create(Category c) {
		return dao.create(c);
	}

	public boolean update(Category c) {
		return dao.update(c);
	}

	public boolean delete(int id) {
		return dao.delete(id);
	}
}