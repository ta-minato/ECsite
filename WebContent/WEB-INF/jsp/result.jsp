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
<p style="display:inline">ようこそ<c:out value="${account.userid}"/>さん　　　</p>
<h2>購入が完了しました</h2>
<% for (Item item : cartList) { %>
		<input type="hidden" name="itemcode"value="<%= item.getItemcode() %>"></input>
		<input type="hidden" name="buy"value="<%= item.getBuy() %>"></input>
		<td><%= item.getItemcode() %></td><br>
		<td><%= item.getItemname() %></td><br>
		<td>個数<%= item.getBuy() %></td><br>
		<td>\<%= String.format("%,d",item.getPrice() * item.getBuy()) %></td><br>
<% } %>
<h3>合計 ￥${total}</h3>
<a href="FinalServlet">メイン画面に戻る</a>
<a href="LoginServlet">ログアウト</a>
</body>
</html>