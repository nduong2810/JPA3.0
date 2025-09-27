package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import model.User;
import utils.JPAConfig;

public class UserDaoJPA {

	public User findByUsernameAndPassword(String username, String password) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.userName=:u AND u.passWord=:p",
					User.class);
			q.setParameter("u", username);
			q.setParameter("p", password); // nếu dùng hash: truyền vào chuỗi hash
			return q.getResultStream().findFirst().orElse(null);
		} finally {
			em.close();
		}
	}

	public boolean existsByUsername(String username) {
		return findByUsername(username) != null;
	}

	public boolean existsByEmail(String email) {
		return findByEmail(email) != null;
	}

	public boolean existsByPhone(String phone) {
		return findByPhone(phone) != null;
	}

	public User findByUsername(String username) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.userName=:u", User.class);
			q.setParameter("u", username);
			return q.getResultStream().findFirst().orElse(null);
		} finally {
			em.close();
		}
	}

	public User findByEmail(String email) {
		if (email == null)
			return null;
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.email=:e", User.class);
			q.setParameter("e", email);
			return q.getResultStream().findFirst().orElse(null);
		} finally {
			em.close();
		}
	}

	public User findByPhone(String phone) {
		if (phone == null)
			return null;
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> q = em.createQuery("SELECT u FROM User u WHERE u.phone=:p", User.class);
			q.setParameter("p", phone);
			return q.getResultStream().findFirst().orElse(null);
		} finally {
			em.close();
		}
	}

	public User save(User u) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(u);
			tx.commit();
			return u;
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void updatePasswordById(Long id, String newPassword) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			User u = em.find(User.class, id);
			if (u != null) {
				u.setPassWord(newPassword);
				em.merge(u);
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
}