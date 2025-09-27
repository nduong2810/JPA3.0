
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>403</title>
<style>
body {
	font-family: system-ui;
	display: grid;
	place-items: center;
	min-height: 100vh;
	background: #f7f7fb;
	margin: 0
}

.card {
	background: #fff;
	padding: 28px;
	border-radius: 16px;
	box-shadow: 0 10px 24px rgba(0, 0, 0, .08);
	text-align: center;
	max-width: 520px
}

a {
	display: inline-block;
	margin-top: 14px;
	padding: 10px 14px;
	border-radius: 10px;
	background: #2563eb;
	color: #fff;
	text-decoration: none;
	font-weight: 700
}
</style>
</head>
<body>
	<div class="card">
		<h1>403 — Không đủ quyền</h1>
		<p><%=request.getAttribute("reason") != null ? request.getAttribute("reason") : "Vui lòng liên hệ quản trị viên."%></p>
		<a href="<%=request.getContextPath()%>/views/hello.jsp">Về trang
			chính</a>
	</div>
</body>
</html>
