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
<title>ユーザーアカウント削除</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/orders.css"type="text/css"/>
</head>
<body>
	<html:form action="/profile" method="POST" styleId="commandv">
		<h1>ユーザーアカウントを削除しますか？</h1>
			<html:hidden property="select" value="5"/>
			<html:hidden property="email" value="${admin.email}"/>
			<html:submit value="はい" styleId="button"/>
		</html:form>
	<html:form action="/profile" method="POST" styleId="commandv">
			<html:submit value="戻る" styleId="button"/>
		</html:form>
</body>
</html>