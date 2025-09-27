package service;

import model.Item;
import java.util.List;

public interface ItemService {
	List<Item> findAll();

	List<Item> findByOwner(String owner);

	Item findById(long id);

	void create(String name, String image, int quantity, String owner);

	void update(long id, String name, String image, int quantity);

	void delete(long id);
}