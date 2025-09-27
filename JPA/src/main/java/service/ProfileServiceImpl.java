package service;

import dao.ProfileDaoJPA;
import model.User;

public class ProfileServiceImpl implements ProfileService {

	private final ProfileDaoJPA dao = new ProfileDaoJPA();

	@Override
	public User findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void updateProfile(long id, String fullName, String phone, String avatarUrlOrNull) {
		dao.updateProfile(id, fullName, phone, avatarUrlOrNull);
	}
}