<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Danh mục</title>
<style>
:root {
	--bg: #f7f7fb;
	--card: #fff;
	--muted: #6b7280;
	--primary: #2563eb
}

body {
	margin: 0;
	background: var(--bg);
	font-family: system-ui, -apple-system, Segoe UI, Roboto, Arial,
		sans-serif
}

.wrap {
	min-height: 100vh;
	display: grid;
	place-items: center;
	padding: 24px
}

.card {
	width: 100%;
	max-width: 920px;
	background: #fff;
	border-radius: 16px;
	box-shadow: 0 12px 26px rgba(0, 0, 0, .08);
	padding: 24px
}

.top {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 12px
}

a.btn {
	display: inline-block;
	padding: 10px 14px;
	border-radius: 10px;
	background: var(--primary);
	color: #fff;
	text-decoration: none;
	font-weight: 700
}

table {
	border-collapse: collapse;
	width: 100%
}

th, td {
	border: 1px solid #e5e7eb;
	padding: 10px
}

th {
	background: #f3f4f6
}

.muted {
	color: var(--muted)
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/fragments/header.jsp"%>
	<div class="wrap">
		<div class="card">
			<div class="top">
				<h2>Danh mục</h2>
				<a class="btn" href="<c:url value='/admin/category/add'/>">+
					Thêm</a>
			</div>

			<c:if test="${empty items}">
				<p class="muted">Chưa có dữ liệu.</p>
			</c:if>
			<c:if test="${not empty items}">
				<table>
					<thead>
						<tr>
							<th>#</th>
							<th>Tên</th>
							<th>Icon</th>
							<th>Hành động</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="it" items="${items}" varStatus="st">
							<c:url var="editUrl" value="/admin/category/edit">
								<c:param name="id" value="${it.id}" />
							</c:url>
							<c:url var="delUrl" value="/admin/category/delete">
								<c:param name="id" value="${it.id}" />
							</c:url>
							<tr>
								<td>${st.index+1}</td>
								<td>${it.name}</td>
								<td>${it.icon}</td>
								<td><a href="${editUrl}">Sửa</a> | <a href="${delUrl}"
									onclick="return confirm('Xóa danh mục này?')">Xóa</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>