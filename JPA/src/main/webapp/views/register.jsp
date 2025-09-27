
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng ký</title>
</head>
<body>
	<div class="card">
		<h1>Đăng ký</h1>
		<form class="form"
			action="${pageContext.request.contextPath}/register" method="post">
			<div>
				<label>Username</label><input class="input" name="username" required />
			</div>
			<div>
				<label>Họ tên</label><input class="input" name="fullName" required />
			</div>
			<div class="grid cols-2">
				<div>
					<label>Email</label><input class="input" name="email" type="email"
						required />
				</div>
				<div>
					<label>Điện thoại</label><input class="input" name="phone" required />
				</div>
			</div>
			<div class="grid cols-2">
				<div>
					<label>Password</label><input class="input" type="password"
						name="password" required />
				</div>
				<div>
					<label>Confirm</label><input class="input" type="password"
						name="confirm" required />
				</div>
			</div>
			<div class="actions">
				<button class="btn btn-primary" type="submit">Tạo tài khoản</button>
				<a class="btn btn-ghost"
					href="${pageContext.request.contextPath}/login">Đăng nhập</a>
			</div>
		</form>

		<c:if test="${param.err == 'invalid'}">
			<p class="alert alert-danger" style="margin-top: 12px">Vui lòng
				nhập đủ thông tin.</p>
		</c:if>
		<c:if test="${param.err == 'confirm'}">
			<p class="alert alert-danger" style="margin-top: 12px">Mật khẩu
				nhập lại không khớp.</p>
		</c:if>
		<c:if test="${param.err == 'existsUser'}">
			<p class="alert alert-danger" style="margin-top: 12px">Username
				đã tồn tại.</p>
		</c:if>
		<c:if test="${param.err == 'existsEmail'}">
			<p class="alert alert-danger" style="margin-top: 12px">Email đã
				tồn tại.</p>
		</c:if>
		<c:if test="${param.err == 'existsPhone'}">
			<p class="alert alert-danger" style="margin-top: 12px">Số điện
				thoại đã tồn tại.</p>
		</c:if>
	</div>
</body>
</html>
