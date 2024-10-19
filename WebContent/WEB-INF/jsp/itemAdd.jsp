<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%Item item =(Item)session.getAttribute("item");%>
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
<h2>以下の内容がカートに追加されました</h2>
<tr>
	<td><%= item.getItemname()%></td><br>
	<td>￥<%= String.format("%,d",item.getPrice())%></td><br>
	<td>個数 <%= item.getBuy()%></td><br>
	<td>小計 : ￥<%= String.format("%,d",item.getPrice()*item.getBuy())%></td><br>
	<td><img src="${pageContext.request.contextPath}/imgs/<%= item.getItemcode()%>.jpg"></td>
</tr><br>
<a href="MainServlet">メイン画面に戻る</a>
</body>
</html>