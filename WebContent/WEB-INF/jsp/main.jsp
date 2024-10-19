<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,model.MainItem,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%List<Item>itemList = (List<Item>)session.getAttribute("itemList");%>
<%//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
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
<button class="button" onclick="location.href='CartServlet'">カート🛒</button><br>
<form id="form1" action="MainServlet" method="post">
<input id="sbox1" type="text" name="itemname"></input>
<input id="sbtn1" type="submit" class="button" value="🔍"></input>
</form><br>
<div class="itemlist">
<ul>
<% for (Item item : itemList) { %>
	<form action="ItemServlet" method="get">
		<input type="hidden" name="itemcode"value="<%= item.getItemcode() %>"></input>
		<li>
		<input type="image" class="img" src="${pageContext.request.contextPath}/imgs/<%= item.getItemcode() %>.jpg" alt="送信"><br>
		<td><%= item.getItemname() %></td><br>
		<td>\<%= String.format("%,d",item.getPrice()) %></td><br>
		<td>在庫数<%= item.getRestocks() - item.getSales() %></td><br>
		</li>
	</form><br>
<% } %>
</ul>
</div>
<a href="LoginServlet">ログアウト</a>

<p class="pagetop"><a href="#wrap">▲</a></p>
</body>
<script>
$(document).ready(function() {
  var pagetop = $('.pagetop');
    $(window).scroll(function () {
       if ($(this).scrollTop() > 100) {
            pagetop.fadeIn();
       } else {
            pagetop.fadeOut();
            }
       });
       pagetop.click(function () {
           $('body, html').animate({ scrollTop: 0 }, 500);
              return false;
   });
});
</script>
</html>