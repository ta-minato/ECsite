<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,model.Total,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%List<Item>cartList = (List<Item>)session.getAttribute("cartList");%>
<%//リクエストスコープに保存されたエラーメッセージを取得
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
<% if(errorMsg != null) { %>
<p class="errorMsg"><%= errorMsg %></p>
<% } %>
<p style="display:inline">ようこそ<c:out value="${account.userid}"/>さん　　　</p>
<% for (Item item : cartList) { %>
<div class="itemlist" >
	<form action="CartServlet" method="post">
		<input type="hidden" name="itemcode"value="<%= item.getItemcode() %>"></input>
		<%= item.getItemcode() %><br>
		<%= item.getItemname() %><br>
		個数 <input type="number" name="buy" onchange="this.form.submit()" value="<%= item.getBuy() %>"></input>※在庫数<%= item.getRestocks() - item.getSales() %><br>
		\<%= String.format("%,d",item.getPrice() * item.getBuy()) %><br>
		<img src="${pageContext.request.contextPath}/imgs/<%= item.getItemcode() %>.jpg">
	<input type="submit" class="button" name="command" value="削除"><br>
	</form><br>
</div>
<% } %>
<h3>合計 ￥${total}</h3>
<form action="CartServlet" method="post">
<input type="submit" class="button1" name="command" value="購入">
</form><br>
<a href="MainServlet">メイン画面に戻る</a>
</body>
</html>