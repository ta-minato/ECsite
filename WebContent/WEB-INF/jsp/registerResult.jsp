<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account" %>
<%
//セッションスコープに保存されたユーザー情報を取得
Account account = (Account)session.getAttribute("account");
String seibetsu = "";
 if (account.getGender().equals("1")) {
	seibetsu = "男";
 }
 if (account.getGender().equals("2")) {
		seibetsu = "女";
 }
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<meta charset="UTF-8">
<title>TOMU</title>
</head>
<header class="header">
<div>
<img src="${pageContext.request.contextPath}/imgs/header.png" alt="TOMU">
</div>
</header>
<body>
<h1>ユーザー登録完了画面</h1>
<form action="LoginServlet" method="get">
<font color="red">以下の内容でユーザー登録が完了しました</font>
<br><br>
<table border="1">
	<tr><td>ユーザーID</td><td><%= account.getUserid() %> </td></tr>
	<tr><td>パスワード</td><td><%= account.getPass() %> </td></tr>
	<tr><td>氏名</td><td><%= account.getUsername() %></td></tr>
	<tr><td>メールアドレス</td><td><%= account.getMail() %></td></tr>
	<tr><td>生年月日</td><td><%= account.getBirthday() %></td></tr>
	<tr><td>性別</td><td><%= account.getGender() %></td></tr>
	<tr><td>郵便番号</td><td><%= account.getZipcode() %> </td></tr>
	<tr><td>住所</td><td><%= account.getAddress() %></td></tr>
</table>
<br>
<input type="submit" class="button" value="ログイン画面">
</form>
</body>
</html>