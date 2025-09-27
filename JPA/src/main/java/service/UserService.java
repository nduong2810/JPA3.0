package service;

import model.User;

public interface UserService {
	User findByUsernameAndPassword(String username, String password);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	User registerUser(String username, String fullName, String email, String phone, String password, int roleId);

	void updatePassword(Long userId, String newPassword);

	User findByUsername(String username);

	User findByEmail(String email);

	User findByPhone(String phone);
}