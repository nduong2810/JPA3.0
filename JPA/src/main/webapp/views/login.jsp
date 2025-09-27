<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng nhập</title>
</head>
<body>

	<div class="card">
		<h1>Đăng nhập</h1>

		<form class="form" action="${pageContext.request.contextPath}/login"
			method="post">
			<div>
				<label>Username</label> <input class="input" type="text"
					name="username" required />
			</div>
			<div>
				<label>Password</label> <input class="input" type="password"
					name="password" required />
			</div>
			<div class="actions">
				<button class="btn btn-primary" type="submit">Login</button>
				<a class="btn btn-ghost"
					href="${pageContext.request.contextPath}/register">Đăng ký</a> <a
					class="link" href="${pageContext.request.contextPath}/forgot">Quên
					mật khẩu?</a>
			</div>
		</form>

		<c:if test="${param.err != null}">
			<p class="alert alert-danger" style="margin-top: 12px">Sai tài
				khoản hoặc mật khẩu</p>
		</c:if>
		<c:if test="${param.out != null}">
			<p class="alert alert-success" style="margin-top: 12px">Đã đăng
				xuất</p>
		</c:if>
		<c:if test="${param.registered != null}">
			<p class="alert alert-success" style="margin-top: 12px">Đăng ký
				thành công, vui lòng đăng nhập.</p>
		</c:if>
		<c:if test="${param.reset != null}">
			<p class="alert alert-success" style="margin-top: 12px">Đổi mật
				khẩu thành công, vui lòng đăng nhập.</p>
		</c:if>
	</div>

</body>
</html>