package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.persistence.EntityManager;
import model.User;
import utils.JPAConfig;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class DbSeeder implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();

			// Nếu bảng User rỗng thì mới seed
			Long userCount = em.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
			if (userCount == 0L) {
				runImportSql(em);
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				em.getTransaction().rollback();
			} catch (Exception ignore) {
			}
		} finally {
			em.close();
		}
	}

	private void runImportSql(EntityManager em) throws Exception {
		// import.sql phải nằm ở src/main/resources → target/classes/import.sql
		try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("import.sql")) {
			if (in == null) {
				System.err.println("⚠ import.sql not found on classpath.");
				return;
			}
			String content = new String(in.readAllBytes(), StandardCharsets.UTF_8);

			// Bỏ comment và tách theo dấu ';' (không dùng GO)
			String noLineComments = content.replaceAll("(?m)^\\s*--.*$", "");
			String noBlockComments = noLineComments.replaceAll("(?s)/\\*.*?\\*/", "");

			String[] raw = noBlockComments.split(";(\\r?\\n|$)");
			List<String> statements = new ArrayList<>();
			for (String s : raw) {
				String sql = s.trim();
				if (!sql.isBlank())
					statements.add(sql);
			}

			// Thực thi từng câu lệnh
			for (String sql : statements) {
				try {
					em.createNativeQuery(sql).executeUpdate();
				} catch (Exception ex) {
					// log rồi tiếp (tránh dừng cả file nếu 1 lệnh lỗi)
					System.err.println("⚠ Import failed for SQL: " + sql);
					ex.printStackTrace();
				}
			}
			System.out.println("✔ Seeded by import.sql (" + statements.size() + " statements).");
		}
	}
}