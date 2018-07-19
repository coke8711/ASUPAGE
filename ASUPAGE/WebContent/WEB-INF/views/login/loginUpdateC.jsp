<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="beans.AdminBean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>更新確認</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/orders.css"type="text/css"/>
</head>
<body>
	<h1>更新確認</h1>
	<html:form action="/profile" method="POST" styleId="commandv">
		<table id="wakusen">
			<tr>
				<th>
					苗字
				</th>
				<td>
					<bean:write name="form" property="firstName" />
				</td>
			</tr>
			<tr>
				<th>
					名前
				</th>
				<td>
					<bean:write name="form" property="lastName" />
				</td>
			</tr>
			<tr>
				<th>
					ログインID
				</th>
				<td>
					<bean:write name="form" property="email" />
				</td>
			</tr>
			<tr>
				<th>
					パスワード
				</th>
				<td>
					<bean:write name="form" property="passWord" />
				</td>
			</tr>
		</table>
		<html:hidden property="select" value="3"/>
		<html:hidden property="adminId" value="${admin.adminId}"/>
		<html:hidden property="firstName" value="${form.firstName}"/>
		<html:hidden property="lastName" value="${form.lastName}"/>
		<html:hidden property="email" value="${form.email}"/>
		<html:hidden property="passWord" value="${form.passWord}"/>
		<html:hidden property="loginDate" value="${admin.loginDate}"/>
		<html:submit value="更新" styleId="button"/>
	</html:form>

		<html:form action="/profile" method="POST" styleId="commandv">
			<html:hidden property="select" value="1"/>
			<html:submit value="戻る" styleId="button"/>
		</html:form>
</body>
</html>