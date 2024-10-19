<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<meta charset="UTF-8">
<title>TOMU</title>
</head>
<header class="header">
<div>
<img src="${pageContext.request.contextPath}/imgs/header.png" alt="TOMU">
</div>
</header>
<body><div class="login">
<div class="login-screen">
<div class="app-title">
<% if(errorMsg != null) { %>
<p class="errorMsg"><%= errorMsg %></p>
<% } %>
<h1>Login</h1>
</div>
<div class="login-form">
<div class="control-group">
<form action="LoginServlet" method="post">
<input type="text" class="login-field" name="userid" placeholder="username" id="login-name"></input><br>
<label class="login-field-icon fui-user" for="login-name"></label>
</div>
<div class="control-group">
<input type="password" name="pass" class="login-field" placeholder="password" id="login-pass"></input><br>
<label class="login-field-icon fui-lock" for="login-pass"></label>
</div>
<input type="submit" value="ログイン" class="btn btn-primary btn-large btn-block">
</form>
<a class="login-link" href="UserServlet" >新規登録はこちらから</a>
</div>
</div>
</div>
</body>
</html>