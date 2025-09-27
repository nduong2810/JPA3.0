package service;

import java.util.List;
import model.Category;

public interface CategoryService {
	List<Category> findAll();

	Category findById(int id);

	boolean create(Category c);

	boolean update(Category c);

	boolean delete(int id);
}