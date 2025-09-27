package dao;

import java.time.Instant;
import java.util.List;
import jakarta.persistence.*;
import model.PasswordResetOtp;
import utils.JPAConfig;

public class OtpDaoJPA {
	public void invalidateOld(String email) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			em.createQuery("UPDATE PasswordResetOtp p SET p.isUsed = true WHERE p.email = :e AND p.isUsed = false")
					.setParameter("e", email).executeUpdate();
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		} finally {
			em.close();
		}
	}

	public PasswordResetOtp create(PasswordResetOtp t) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			return t;
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		} finally {
			em.close();
		}
	}

	public PasswordResetOtp findActive(String email, String otp) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			List<PasswordResetOtp> list = em
					.createQuery("SELECT p FROM PasswordResetOtp p "
							+ "WHERE p.email=:e AND p.otp=:o AND p.isUsed=false AND p.expiresAt > :now "
							+ "ORDER BY p.id DESC", PasswordResetOtp.class)
					.setParameter("e", email).setParameter("o", otp).setParameter("now", Instant.now()).setMaxResults(1)
					.getResultList();
			return list.isEmpty() ? null : list.get(0);
		} finally {
			em.close();
		}
	}

	public void markUsed(Integer id) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			PasswordResetOtp p = em.find(PasswordResetOtp.class, id);
			if (p != null) {
				p.setUsed(true);
				em.merge(p);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		} finally {
			em.close();
		}
	}
}