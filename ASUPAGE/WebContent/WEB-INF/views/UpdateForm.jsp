<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会員情報更新</title>

<script type="text/javascript">
	/*
	 function checkValue() {
	 if (!document.userInfo.password.value) {
	 alert("パスワードを入力してください")
	 return false;
	 }
	 }
	 */
</script>

</head>
<body>

	<br>
	<br>
	<b><font size="6" color="grey">会員情報更新</font></b>
	<br>
	<br>
	<br>
	<html:messages id="msg" header="message.header" footer="message.footer" message="true">
		<li><b><bean:write name="msg" /></b></li>
	</html:messages>
	<form method="post" action="update.do" name="userInfo"
		onsubmit="return checkValue()">
		<table>
			<tr>
				<td id="title">メール</td>
				<td id="title">${info.email}</td>
			</tr>
			<tr>
				<td id="title">パスワード</td>
				<td><input type="password" name="password" maxlength="50"
					value=""></td>
			</tr>
		</table>
		<table>
			<tr>
				<td id="title">苗字</td>
				<td><input type="text" name="firstname" maxlength="50"
					value="${info.firstName}"></td>
			</tr>
			<tr>
				<td id="title">名前</td>
				<td><input type="text" name="lastname" maxlength="50"
					value="${info.lastName}"></td>
			</tr>
		</table>
		<br> <br> <input type="button" value="キャンセル"
			onclick="javascript:window.location='../users.do'"> <input
			type="submit" value="確定" />
	</form>
</body>
</html>