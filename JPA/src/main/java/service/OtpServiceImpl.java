package service;

import java.time.Duration;
import java.time.Instant;
import dao.OtpDaoJPA;
import model.PasswordResetOtp;

public class OtpServiceImpl implements OtpService {
	private final OtpDaoJPA dao = new OtpDaoJPA();

	@Override
	public PasswordResetOtp issue(String email, Duration ttl) {
		dao.invalidateOld(email);
		PasswordResetOtp t = new PasswordResetOtp();
		t.setEmail(email);
		t.setOtp(gen6());
		t.setExpiresAt(Instant.now().plus(ttl));
		t.setUsed(false);
		return dao.create(t);
	}

	@Override
	public boolean verifyAndConsume(String email, String otp) {
		PasswordResetOtp t = dao.findActive(email, otp);
		if (t == null)
			return false;
		dao.markUsed(t.getId());
		return true;
	}

	private static String gen6() {
		int n = (int) (Math.random() * 900000) + 100000;
		return String.valueOf(n);
	}
}