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
	<title>アカウント情報更新</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/orders.css"type="text/css"/>
</head>
<body>
	<h1>アカウント情報更新</h1>
	<html:form action="/profile" method="POST" styleId="commandv">
		<table id="wakusen">
			<tr>
				<th>
					苗字
				</th>
				<td>
					<html:text name="admin" property="firstName" ></html:text>
					<br>
					<html:errors property="firstName" />
				</td>
			</tr>
			<tr>
				<th>
					名前
				</th>
				<td>
					<html:text name="admin" property="lastName" ></html:text>
					<br>
					<html:errors property="lastName" />
				</td>
			</tr>
			<tr>
				<th>
					ログインID
				</th>
				<td>
					<html:text name="admin" property="email" ></html:text>
					<br>
					<html:errors property="email" />
				</td>
			</tr>
			<tr>
				<th>
					新しいパスワード
				</th>
				<td>
					<html:password property="passWord" />
						<br>
					<html:errors property="passWord" />
				</td>
			</tr>
		</table>
		<html:hidden property="select" value="2"/>
		<html:hidden property="adminId" value="${admin.adminId}"/>
		<html:submit value="更新確認" styleId="button"/>
	</html:form>

		<html:form action="/profile" method="POST" styleId="commandv">
			<html:submit value="戻る" styleId="button"/>
		</html:form>
</body>
</html>