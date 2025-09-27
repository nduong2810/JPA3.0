package service;

import model.User;

public interface ProfileService {
	User findById(long id);

	void updateProfile(long id, String fullName, String phone, String avatarUrlOrNull);
}