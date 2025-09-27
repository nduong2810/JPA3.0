<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Nhập mã OTP</title>
</head>
<body>
	<div class="card">
		<h1>Xác thực OTP</h1>
		<form class="form" action="${pageContext.request.contextPath}/verify"
			method="post">
			<div>
				<label>Mã OTP</label><input class="input" name="code"
					inputmode="numeric" maxlength="6" placeholder="6 chữ số" required />
			</div>
			<div class="actions">
				<button class="btn btn-primary" type="submit">Xác nhận</button>
				<a class="btn btn-ghost"
					href="${pageContext.request.contextPath}/forgot">Gửi lại OTP</a>
			</div>
		</form>
		<c:if test="${param.err != null}">
			<p class="alert alert-danger" style="margin-top: 12px">Mã OTP
				không hợp lệ hoặc đã hết hạn.</p>
		</c:if>
	</div>
</body>
</html>