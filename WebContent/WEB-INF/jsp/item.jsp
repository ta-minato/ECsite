<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
//リクエストスコープに保存されたエラーメッセージを取得
Item item =(Item)session.getAttribute("item");
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<title>TOMU</title>
</head>
<header class="header">
<div>
<img src="${pageContext.request.contextPath}/imgs/header.png" alt="TOMU">
</div>
</header>
<body>
<p style="display:inline">ようこそ<c:out value="${account.userid}"/>さん　　　</p>
<button class="button" onclick="location.href='CartServlet'">カート🛒</button><br>
<% if(errorMsg != null) { %>
<p class="errorMsg"><%= errorMsg %></p>
<% } %>
<tr><form action="ItemServlet" method="post">
	<input type="hidden" name="itemcode"value="<%= item.getItemcode()%>"></input>
	<td><%= item.getItemname()%></td><br>
	<td>￥<%= String.format("%,d",item.getPrice())%></td><br>
	<td>個数 <input type="number" name="buy" value="1"></input>※在庫数<%= item.getRestocks() - item.getSales() %></td><br>
	<td><img src="${pageContext.request.contextPath}/imgs/<%= item.getItemcode()%>.jpg"></td>
	<input type="submit" class="button" value="カートに追加"></input>
</form></tr><br>
<a href="MainServlet">メイン画面に戻る</a>
</body>
</html>