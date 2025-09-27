<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Items</title>
</head>
<body>

	<div class="card" style="margin-bottom: 18px">
		<h1 style="margin: 0">Items</h1>
		<p style="color: #334155; margin: 8px 0 0">
			<span class="badge">${sessionScope.authRole}</span>
		</p>
	</div>

	<div class="table-wrap card">
		<table class="table">
			<thead>
				<tr>
					<th>ID</th>
					<th>Tên</th>
					<th>Hình</th>
					<th>Số lượng</th>
					<th style="width: 1%">Thao tác</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="it" items="${items}">
					<tr>
						<td>${it.id}</td>
						<td>${it.name}</td>
						<td><c:if test="${not empty it.image}">
								<img src="${it.image}" alt=""
									style="height: 40px; border-radius: 8px" />
							</c:if></td>
						<td>${it.quantity}</td>
						<td style="white-space: nowrap"><c:if
								test="${sessionScope.authRole == 'ADMIN' || sessionScope.authRole == 'MANAGER'}">
								<form action="${pageContext.request.contextPath}/items/update"
									method="post" style="display: inline-flex; gap: 6px">
									<input type="hidden" name="id" value="${it.id}" /> <input
										class="input" style="width: 160px" name="name"
										value="${it.name}" /> <input class="input"
										style="width: 230px" name="image" value="${it.image}"
										placeholder="image url" /> <input class="input"
										style="width: 90px" name="quantity" type="number" min="0"
										value="${it.quantity}" />
									<button class="btn btn-primary" type="submit">Sửa</button>
								</form>
								<form action="${pageContext.request.contextPath}/items/delete"
									method="post" style="display: inline">
									<input type="hidden" name="id" value="${it.id}" />
									<button class="btn btn-danger" type="submit"
										onclick="return confirm('Xoá item #${it.id}?')">Xóa</button>
								</form>
							</c:if> <c:if
								test="${sessionScope.authRole == 'USER' && it.owner == sessionScope.authUser}">
								<form action="${pageContext.request.contextPath}/items/update"
									method="post" style="display: inline-flex; gap: 6px">
									<input type="hidden" name="id" value="${it.id}" /> <input
										class="input" style="width: 160px" name="name"
										value="${it.name}" /> <input class="input"
										style="width: 230px" name="image" value="${it.image}"
										placeholder="image url" /> <input class="input"
										style="width: 90px" name="quantity" type="number" min="0"
										value="${it.quantity}" />
									<button class="btn btn-primary" type="submit">Sửa</button>
								</form>
								<form action="${pageContext.request.contextPath}/items/delete"
									method="post" style="display: inline">
									<input type="hidden" name="id" value="${it.id}" />
									<button class="btn btn-danger" type="submit"
										onclick="return confirm('Xoá item #${it.id}?')">Xóa</button>
								</form>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="card">
		<h2 style="margin-top: 0">Thêm Item</h2>
		<form class="form"
			action="${pageContext.request.contextPath}/items/create"
			method="post">
			<div class="grid cols-2">
				<div>
					<label>Tên</label><input class="input" name="name" required />
				</div>
				<div>
					<label>Số lượng</label><input class="input" name="quantity"
						type="number" min="0" value="1" required />
				</div>
			</div>
			<div>
				<label>Image URL</label><input class="input" name="image"
					placeholder="https://... hoặc data:image/..." />
			</div>
			<!-- ✅ ĐÃ BỎ Ô Owner -->
			<div class="actions">
				<button class="btn btn-primary" type="submit">Lưu</button>
			</div>
		</form>
	</div>

</body>
</html>
