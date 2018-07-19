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
<title>新規ユーザ登録</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/CSS/orders.css" type="text/css" />
</head>
<body>
	<html:form action="/loginNew" method="POST" styleId="commandv">
		<table id="wakusen">
			<tr>
				<th>
					苗字
				</th>
				<td>
					<html:text property="firstName" />
					<br>
					<html:errors property="firstName" />
				</td>
			</tr>
			<tr>
				<th>
					名前
				</th>
				<td>
					<html:text property="lastName" />
					<br>
					<html:errors property="lastName" />
				</td>
			</tr>
			<tr>
				<th>
					ログインID（Email）
				</th>
				<td>
					<html:text property="email" />
					<br>
					<html:errors property="email" />
				</td>
			</tr>
			<tr>
				<th>
					パスワード
				</th>
				<td>
					<html:text property="passWord" />
					<br>
					<html:errors property="passWord" />
				</td>
			</tr>
		</table>
		<html:hidden property="select" value="1"/>
		<html:submit value="確認" styleId="button"/>
	</html:form>

</body>
</html>