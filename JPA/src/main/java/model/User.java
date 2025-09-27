package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "[User]") // USER là keyword trong SQL Server
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// cột trong DB: userName, passWord, roleId, phone, email, fullName,
	// createdDate, avatar
	@Column(name = "userName", nullable = false, unique = true)
	private String userName;

	@Column(name = "passWord", nullable = false)
	private String passWord;

	@Column(name = "roleId", nullable = false)
	private Integer roleId; // 1=ADMIN, 2=MANAGER, 3=USER

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "fullName")
	private String fullName;

	@Column(name = "createdDate")
	private LocalDateTime createdDate;

	@Column(name = "avatar")
	private String avatar;

	// ===== getters/setters =====
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getRoleid() {
		return this.getRoleId();
	}

	public void setRoleid(Integer roleId) {
		this.setRoleId(roleId);
	}

	// tiện dùng trong Filter/JSP
	@Transient
	public String getRoleName() {
		if (roleId == null)
			return "USER";
		return switch (roleId) {
		case 1 -> "ADMIN";
		case 2 -> "MANAGER";
		default -> "USER";
		};
	}
}