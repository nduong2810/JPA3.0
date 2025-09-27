
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Đổi mật khẩu</title>
</head>
<body>
	<div class="card">
		<h1>Đổi mật khẩu</h1>
		<form class="form" action="${pageContext.request.contextPath}/reset"
			method="post">
			<div class="grid cols-2">
				<div>
					<label>Mật khẩu mới</label><input class="input" type="password"
						name="password" required />
				</div>
				<div>
					<label>Nhập lại</label><input class="input" type="password"
						name="confirm" required />
				</div>
			</div>
			<div class="actions">
				<button class="btn btn-primary" type="submit">Cập nhật</button>
				<a class="btn btn-ghost"
					href="${pageContext.request.contextPath}/login">Hủy</a>
			</div>
		</form>
		<c:if test="${param.err == 'confirm'}">
			<p class="alert alert-danger" style="margin-top: 12px">Mật khẩu
				nhập lại không khớp.</p>
		</c:if>
	</div>
</body>
</html>
