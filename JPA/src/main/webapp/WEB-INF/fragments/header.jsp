
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
model.User _u = (model.User) session.getAttribute("account");
String _name = (_u != null && _u.getFullName() != null && !_u.getFullName().isEmpty()) ? _u.getFullName() : "Khách";
int _role = (_u != null) ? _u.getRoleid() : 0;
String _roleName;
switch (_role) {
case 1:
	_roleName = "Admin";
	break;
case 2:
	_roleName = "Manager";
	break;
case 3:
	_roleName = "User";
	break;
default:
	_roleName = "Guest";
}
%>
<style>
.app-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 10px 16px;
	background: #111827;
	color: #fff
}

.app-header a {
	color: #fff;
	text-decoration: none
}

.app-header .brand {
	font-weight: 700
}

.app-header .right a.btn {
	background: #ef4444;
	padding: 8px 12px;
	border-radius: 8px
}
</style>
<div class="app-header">
	<div class="left">
		<a class="brand" href="<%=request.getContextPath()%>/">MyApp</a> <span
			style="margin-left: 10px; opacity: .8">| <%=_roleName%></span>
	</div>
	<div class="right">
		<%
		if (_u != null) {
		%>
		<span style="margin-right: 10px;">Xin chào, <strong><%=_name%></strong></span>
		<a class="btn" href="<%=request.getContextPath()%>/logout">Đăng
			xuất</a>
		<%
		} else {
		%>
		<a class="btn" href="<%=request.getContextPath()%>/login">Đăng
			nhập</a>
		<%
		}
		%>
	</div>
</div>
