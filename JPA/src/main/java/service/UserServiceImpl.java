package service;

import dao.UserDaoJPA;
import model.User;
import java.time.LocalDateTime;

public class UserServiceImpl implements UserService {

	private final UserDaoJPA userDao = new UserDaoJPA();

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	public boolean existsByUsername(String u) {
		return userDao.existsByUsername(u);
	}

	@Override
	public boolean existsByEmail(String e) {
		return userDao.existsByEmail(e);
	}

	@Override
	public boolean existsByPhone(String p) {
		return userDao.existsByPhone(p);
	}

	@Override
	public User registerUser(String username, String fullName, String email, String phone, String password,
			int roleId) {
		User u = new User();
		u.setUserName(username);
		u.setFullName(fullName);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassWord(password); // nếu hash: set chuỗi hash
		u.setRoleId(roleId);
		u.setCreatedDate(LocalDateTime.now());
		return userDao.save(u);
	}

	@Override
	public void updatePassword(Long userId, String newPassword) {
		userDao.updatePasswordById(userId, newPassword);
	}

	@Override
	public User findByUsername(String u) {
		return userDao.findByUsername(u);
	}

	@Override
	public User findByEmail(String e) {
		return userDao.findByEmail(e);
	}

	@Override
	public User findByPhone(String p) {
		return userDao.findByPhone(p);
	}
}