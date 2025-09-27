package utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailer {
//	private static final String USER = (System.getenv("MAIL_USER") != null) ? System.getenv("MAIL_USER")
//			: System.getProperty("MAIL_USER");
//
//	private static final String APP_PASSWORD = (System.getenv("MAIL_PASS") != null) ? System.getenv("MAIL_PASS")
//			: System.getProperty("MAIL_PASS");
//	
	private static final String USER = "duongboil5@gmail.com";
	private static final String PASS = "ysrpmzqidlkzdkbx";

	private static String firstNonNull(String a, String b) {
		return (a != null && !a.isBlank()) ? a : ((b != null && !b.isBlank()) ? b : null);
	}

	private static Session tls587() {
		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "587");
		p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		p.put("mail.smtp.connectiontimeout", "20000");
		p.put("mail.smtp.timeout", "20000");
		return Session.getInstance(p, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER, PASS);
			}
		});
	}

	private static Session ssl465() {
		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.ssl.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		p.put("mail.smtp.connectiontimeout", "20000");
		p.put("mail.smtp.timeout", "20000");
		return Session.getInstance(p, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USER, PASS);
			}
		});
	}

	public static void send(String to, String subject, String html) throws MessagingException {
		if (USER == null || PASS == null) {
			throw new IllegalStateException("Thiếu MAIL_USER/MAIL_PASS (Gmail App Password).");
		}
		System.out.println("[Mailer] USER=" + USER + " | PASSlen=" + PASS.length());

		try {
			sendWithSession(tls587(), to, subject, html); // Thử TLS 587 trước
			return;
		} catch (MessagingException ex) {
			System.err.println("[Mailer] TLS 587 failed: " + ex.getMessage());
			sendWithSession(ssl465(), to, subject, html); // Fallback SSL 465
		}
	}

	private static void sendWithSession(Session s, String to, String subject, String html) throws MessagingException {
		s.setDebug(true);
		MimeMessage m = new MimeMessage(s);
		m.setFrom(new InternetAddress(USER));
		m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
		m.setSubject(subject, "UTF-8");
		m.setContent(html, "text/html; charset=UTF-8");
		Transport.send(m);
	}
}