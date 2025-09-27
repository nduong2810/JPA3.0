package service;

import model.PasswordResetOtp;
import java.time.Duration;

public interface OtpService {
	PasswordResetOtp issue(String email, Duration ttl);

	boolean verifyAndConsume(String email, String otp);
}