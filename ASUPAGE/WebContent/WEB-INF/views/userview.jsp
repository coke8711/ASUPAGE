<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="beans.UserBean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath() %>/CSS/users.css"type="text/css"/>
<title>検索画面</title>
<script type="text/javascript">
	function goModifyForm() {
		location.href = "profile.do";
	}
	function goLogout() {
		location.href = "logout.do";
	}
</script>
</head>
<body>
	ようこそ
	${admin.firstName}様
	<input type="button" value="情報の確認・変更" onClick="goModifyForm()" />
	<input type="button" value="ログアウト" onClick="goLogout()" />
	<h1>★顧客検索一覧画面</h1>
	<h1>検索</h1>
	<html:form action="/users" method="GET">
		<table id="center" border="3" summary="hahahaha">
			<tr>
				<th>(フリガナ)</th>
				<td><html:text property="firstNameKana" /></td>
				<th>(フリガナ)</th>
				<td><html:text property="lastNameKana" /></td>
			</tr>
			<tr>
				<th>顧客苗字</th>
				<td><html:text property="firstName" /></td>
				<th>顧客名前</th>
				<td><html:text property="lastName" /></td>
			</tr>
			<tr>
				<th>会社名</th>
				<td><html:text property="company" /></td>
				<th>役職名</th>
				<td><html:text property="positionName" /></td>
			</tr>
			<tr>
				<th>分類1</th>
				<td><html:select property="classification1">
						<html:option value="" />
						<logic:iterate id="list" name="pullDownListC1">
							<html:option value="${list.classification1 }" />
						</logic:iterate>
					</html:select></td>
				<th>分類2</th>
				<td><html:select property="classification2">
						<html:option value="" />
						<logic:iterate id="list" name="pullDownListC2">
							<html:option value="${list.classification2 }" />
						</logic:iterate>
					</html:select></td>
			</tr>
			<tr>
				<th>住所(都道府県)</th>
				<td><html:text property="street1" /></td>
				<th>住所(詳細住所)</th>
				<td><html:text property="street2" /></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><html:text property="tell" /></td>
				<th>FAX</th>
				<td><html:text property="fax" /></td>
			</tr>
			<tr>
				<th>携帯電話</th>
				<td><html:text property="mobile" /></td>
				<th>Email</th>
				<td><html:text property="email" /></td>
			</tr>
		</table>
		<html:submit value="検索" />
	</html:form>
	<table border="10" summary="hahahaha">
		<caption>
			顧客一覧
			<html:form action="/users/new.do" method="GET">
				<html:submit value="新規登録"></html:submit>
			</html:form>
		</caption>

		<thead>
			<tr>
				<th>会社名</th>
				<th>役職名</th>
				<th>氏名</th>
				<th>性別</th>
				<th>☆彡</th>
			</tr>
		</thead>
		<tbody>
			<logic:iterate id="bean" name="beans">
				<html:form action="/users/detail" method="GET">
					<tr>
						<td><bean:write name="bean" property="company" /></td>
						<td><bean:write name="bean" property="positionName" /></td>
						<td><bean:write name="bean" property="firstName" />
							<bean:write name="bean" property="lastName" />
							<bean:write name="bean" property="title" /></td>
						<td><bean:write name="bean" property="sex" /></td>
						<html:hidden name="bean" property="userId" />
						<td><html:submit value="詳細表示" /></td>
					</tr>
				</html:form>
			</logic:iterate>
		</tbody>
	</table>
	<html:form action="/users" method="GET" styleId="commandv ">
		<logic:iterate id="bean" name="Page">
			<html:submit property="offset" value="${bean}" />
		</logic:iterate>
		<html:hidden property="firstNameKana" />
		<html:hidden property="lastNameKana" />
		<html:hidden property="firstName" />
		<html:hidden property="lastName" />
		<html:hidden property="company" />
		<html:hidden property="positionName" />
		<html:hidden property="classification1" />
		<html:hidden property="classification2" />
		<html:hidden property="street1" />
		<html:hidden property="street2" />
		<html:hidden property="tell" />
		<html:hidden property="fax" />
		<html:hidden property="mobile" />
		<html:hidden property="email" />
	</html:form>
</body>
</html>