<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="beans.AdminBean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ログイン</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/orders.css" type="text/css" />
</head>
<body>
	<h1>ログイン</h1>
	<html:form action="/login" method="POST">
		<table id="wakusen">
			<tr>
				<th>Email</th>
				<td>
					<html:text property="email" />
					<br>
					<html:errors property="email" />
				</td>
			</tr>
			<tr>
				<th>Password</th>
				<td>
					<html:password property="passWord" />
					<br>
					<html:errors property="passWord" />
				</td>
			</tr>
		</table>
		<br>
		<html:submit value="ログイン" styleId="button2" />
	</html:form>

	<html:form action="/loginNew" method="GET">
		<html:submit value="新規登録" styleId="button2" />
	</html:form>
</body>
</html>