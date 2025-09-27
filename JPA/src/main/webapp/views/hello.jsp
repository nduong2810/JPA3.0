<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Hello</title>
<style>
body {
	font-family: system-ui;
	min-height: 100vh;
	margin: 0;
	background: #f7f7fb
}

.wrap {
	display: grid;
	place-items: center;
	padding: 24px
}

.card {
	background: #fff;
	padding: 24px 32px;
	border-radius: 16px;
	box-shadow: 0 10px 24px rgba(0, 0, 0, .08);
	min-width: 320px;
	text-align: center
}

a.btn {
	display: inline-block;
	margin-top: 12px;
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
	<%@ include file="/WEB-INF/fragments/header.jsp"%>
	<div class="wrap">
		<div class="card">
			<%
			model.User u = (model.User) session.getAttribute("account");
			String name = (u != null && u.getFullName() != null && !u.getFullName().isEmpty()) ? u.getFullName() : "bạn";
			boolean canAdmin = (u != null && (u.getRoleid() == 1 || u.getRoleid() == 2));
			%>
			<h1>
				Hello
				<%=name%></h1>
			<%
			if (canAdmin) {
			%>
			<a class="btn"
				href="<%=request.getContextPath()%>/admin/category/list">Quản
				trị danh mục</a>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>