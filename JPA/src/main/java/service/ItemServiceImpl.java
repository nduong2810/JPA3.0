package service;

import dao.ItemDaoJPA;
import model.Item;
import java.util.List;

public class ItemServiceImpl implements ItemService {

	private final ItemDaoJPA dao = new ItemDaoJPA();

	@Override
	public List<Item> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Item> findByOwner(String owner) {
		return dao.findByOwner(owner);
	}

	@Override
	public Item findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void create(String name, String image, int quantity, String owner) {
		dao.create(name, image, quantity, owner);
	}

	@Override
	public void update(long id, String name, String image, int quantity) {
		dao.update(id, name, image, quantity);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}
}