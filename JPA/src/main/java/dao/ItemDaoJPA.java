package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.Item;
import utils.JPAConfig;

import java.util.List;

public class ItemDaoJPA {

	public List<Item> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.createQuery("SELECT i FROM Item i ORDER BY i.id DESC", Item.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Item> findByOwner(String owner) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Item> q = em.createQuery("SELECT i FROM Item i WHERE i.owner = :o ORDER BY i.id DESC",
					Item.class);
			q.setParameter("o", owner);
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public Item findById(long id) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Item.class, id);
		} finally {
			em.close();
		}
	}

	public void create(String name, String image, int quantity, String owner) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Item it = new Item();
			it.setName(name);
			it.setImage(image);
			it.setQuantity(quantity);
			it.setOwner(owner);
			em.persist(it);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void update(long id, String name, String image, int quantity) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Item it = em.find(Item.class, id);
			if (it != null) {
				it.setName(name);
				it.setImage(image);
				it.setQuantity(quantity);
				em.merge(it);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(long id) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Item it = em.find(Item.class, id);
			if (it != null)
				em.remove(it);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
}