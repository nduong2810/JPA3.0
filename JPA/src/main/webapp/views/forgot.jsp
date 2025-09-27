<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Quên mật khẩu</title>
</head>
<body>
	<div class="card">
		<h1>Quên mật khẩu</h1>
		<form class="form" action="${pageContext.request.contextPath}/forgot"
			method="post">
			<p style="color: #334155; margin: 0">
				Nhập <b>Email</b> hoặc <b>Số điện thoại</b> đã đăng ký:
			</p>
			<div class="grid cols-2">
				<div>
					<label>Email</label><input class="input" name="email" type="email" />
				</div>
				<div>
					<label>Điện thoại</label><input class="input" name="phone" />
				</div>
			</div>
			<div class="actions">
				<button class="btn btn-primary" type="submit">Gửi mã OTP</button>
				<a class="btn btn-ghost"
					href="${pageContext.request.contextPath}/login">Về đăng nhập</a>
			</div>
		</form>

		<c:if test="${param.err == 'need_input'}">
			<p class="alert alert-danger" style="margin-top: 12px">Vui lòng
				nhập email hoặc số điện thoại.</p>
		</c:if>
		<c:if test="${param.err == 'notfound'}">
			<p class="alert alert-danger" style="margin-top: 12px">Không tìm
				thấy tài khoản phù hợp.</p>
		</c:if>
		<c:if test="${param.err == 'noemail'}">
			<p class="alert alert-danger" style="margin-top: 12px">Tài khoản
				này chưa có email để gửi OTP.</p>
		</c:if>
	</div>
</body>
</html>