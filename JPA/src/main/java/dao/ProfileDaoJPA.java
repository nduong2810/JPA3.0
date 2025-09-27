package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.User;
import utils.JPAConfig;

public class ProfileDaoJPA {

	public User findById(long id) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(User.class, id);
		} finally {
			em.close();
		}
	}

	public void updateProfile(long id, String fullName, String phone, String avatarUrlOrNull) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			User u = em.find(User.class, id);
			if (u != null) {
				if (fullName != null)
					u.setFullName(fullName);
				if (phone != null)
					u.setPhone(phone);
				if (avatarUrlOrNull != null && !avatarUrlOrNull.isBlank()) {
					u.setAvatar(avatarUrlOrNull);
				}
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
