<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TOMU</title>
</head>
<body>
<h1>管理者ログイン画面</h1>
<form action="AdminLoginServlet" method="post">
<table border="1">
	<tr><td>ユーザーID</td><td><input type="text" name="userid" > </td></tr>
	<tr><td>パスワード</td><td><input type="password" name="pass" > </td></tr>
</table>
<br>
<input type="submit" value="ログイン">
</form>
<% if(errorMsg != null) { %>
<p><%= errorMsg %></p>
<% } %>
</body>
</html>