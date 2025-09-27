
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Sửa danh mục</title>
<style>
body {
	margin: 0;
	background: #f7f7fb;
	font-family: system-ui
}

.wrap {
	min-height: 100vh;
	display: grid;
	place-items: center;
	padding: 24px
}

.card {
	width: 100%;
	max-width: 640px;
	background: #fff;
	border-radius: 16px;
	box-shadow: 0 12px 26px rgba(0, 0, 0, .08);
	padding: 24px
}

label {
	display: block;
	margin: 12px 0 6px;
	font-weight: 600
}

input {
	width: 100%;
	padding: 12px;
	border: 1px solid #e5e7eb;
	border-radius: 10px
}

.row {
	display: flex;
	gap: 10px;
	margin-top: 16px
}

.btn {
	padding: 12px 16px;
	border: 0;
	border-radius: 10px;
	background: #2563eb;
	color: #fff;
	font-weight: 700
}

a.link {
	padding: 12px 16px;
	border-radius: 10px;
	background: #e5e7eb;
	text-decoration: none;
	color: #111
}

.err {
	color: #b91c1c
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jsp"%>
	<div class="wrap">
		<div class="card">
			<h2>Sửa danh mục</h2>
			<c:if test="${not empty error}">
				<p class="err">${error}</p>
			</c:if>
			<form method="post" action="<c:url value='/admin/category/edit'/>">
				<input type="hidden" name="id" value="${item.id}"> <label
					for="name">Tên *</label> <input id="name" name="name" required
					value="${item.name}"> <label for="icon">Icon</label> <input
					id="icon" name="icon" value="${item.icon}">
				<div class="row">
					<button class="btn" type="submit">Cập nhật</button>
					<a class="link" href="<c:url value='/admin/category/list'/>">Hủy</a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
