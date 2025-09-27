<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title><sitemesh:write property="title" /></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/app.css" />
<sitemesh:write property="head" />
</head>
<body>
	<header class="navbar">
		<div class="wrap">
			<!-- Logo/brand: nếu muốn có thể đổi href tùy ý -->
			<div class="brand">
				<a href="${pageContext.request.contextPath}/login">JPA App</a>
			</div>

			<!-- ĐÃ BỎ TOÀN BỘ NAV LINK (Items/Manager/Admin) -->

			<div class="userbox">
				<c:choose>
					<c:when test="${not empty sessionScope.authUser}">
          Xin chào, <strong>${sessionScope.authUser}</strong>
          &nbsp;•&nbsp;<a
							href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
          &nbsp;|&nbsp;<a
							href="${pageContext.request.contextPath}/register">Đăng ký</a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</header>

	<main class="container">
		<sitemesh:write property="body" />
	</main>

	<footer class="container"
		style="opacity: .7; font-size: 12px; margin-top: 28px">
		<small>© Bui Nhat Duong</small>
	</footer>

	<sitemesh:write property="page.local_scripts" />
</body>
</html>