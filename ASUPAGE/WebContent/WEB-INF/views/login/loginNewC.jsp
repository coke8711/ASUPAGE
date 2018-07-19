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
	<title>新規登録確認</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/orders.css"type="text/css"/>
</head>
<body>
	<h1>新規登録確認</h1>

	<hr size="10">
		<html:form action="/loginNew" method="POST" styleId="commandv">
			<table id="wakusen">
				<tr>
					<th>
						苗字
					</th>
					<td>
						${form.firstName}
					</td>
				</tr>
				<tr>
					<th>
						名前
					</th>
					<td>
						${form.lastName}
					</td>
				</tr>
				<tr>
					<th>
						ログインID
					</th>
					<td>
						${form.email}
					</td>
				</tr>
				<tr>
					<th>
						パスワード
					</th>
					<td>
						${form.passWord}
					</td>
				</tr>
			</table>
			<html:hidden property="firstName" />
			<html:hidden property="lastName" />
			<html:hidden property="email" />
			<html:hidden property="passWord" />
			<html:submit value="確定" styleId="button"/>
		</html:form>
</body>
</html>