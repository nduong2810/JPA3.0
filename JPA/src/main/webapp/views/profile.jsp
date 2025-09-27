<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>Hồ sơ</title>
</head>
<body>
	<div class="card shadow-sm">
		<div class="card-body">
			<h3 class="card-title mb-3">Hồ sơ cá nhân</h3>

			<c:if test="${param.ok != null}">
				<div class="alert alert-success">Cập nhật thành công.</div>
			</c:if>

			<div class="row g-4">
				<div class="col-md-4">
					<div class="text-center">
						<c:choose>
							<c:when test="${not empty user.avatar}">
								<img src="${user.avatar}" alt="avatar" class="img-thumbnail"
									style="max-height: 220px">
							</c:when>
							<c:otherwise>
								<div class="border rounded p-5 text-muted">Chưa có ảnh</div>
							</c:otherwise>
						</c:choose>
						<p class="mt-2 mb-0">
							<strong>${user.userName}</strong>
						</p>
						<div class="text-muted small">${user.email}</div>
					</div>
				</div>

				<div class="col-md-8">
					<form class="needs-validation"
						action="${pageContext.request.contextPath}/profile" method="post"
						enctype="multipart/form-data" novalidate>
						<div class="mb-3">
							<label class="form-label">Họ và tên</label> <input
								class="form-control" type="text" name="fullName"
								value="${user.fullName}" required>
							<div class="invalid-feedback">Vui lòng nhập họ tên.</div>
						</div>

						<div class="mb-3">
							<label class="form-label">Số điện thoại</label> <input
								class="form-control" type="text" name="phone"
								value="${user.phone}">
						</div>

						<div class="mb-3">
							<label class="form-label">Ảnh đại diện (jpg/png, tối đa
								5MB)</label> <input class="form-control" type="file" name="avatar"
								accept="image/*">
						</div>

						<div class="d-flex gap-2">
							<button class="btn btn-primary" type="submit">Lưu thay
								đổi</button>
							<a class="btn btn-outline-secondary"
								href="${pageContext.request.contextPath}/items">Về danh sách</a>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

	<script>
    // bootstrap client-side validation
    (() => {
      'use strict';
      const forms = document.querySelectorAll('.needs-validation');
      Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
          if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    })();
  </script>
</body>
</html>
