<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Item " %>
 <%
//セッションスコープに保存されたユーザー情報を取得
Item item =(Item)session.getAttribute("item");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TOMU</title>
</head>
<body>
<h1>商品発注結果画面</h1>
<form action="ItemListServlet" method="get">
<input type="submit" value="発注を続ける">
</form>
<br>
<font color="red">以下の内容で商品の発注が完了しました</font>
<br>
<table border="1" >
	<tr><td>商品コード</td><td><%= item.getItemcode() %> </td></tr>
	<tr><td>商品名</td><td><%= item.getItemname() %> </td></tr>
	<tr><td>仕入価格</td><td><%= String.format("%,d",item.getCost()) %> </td></tr>
	<tr><td>仕入数</td><td><%= String.format("%,d",item.getRestocks()) %> </td></tr>
	<tr><td>仕入金額</td><td><%= String.format("%,d",item.getCost() * item.getRestocks()) %> </td></tr>
</table>
<br>
<form action="ItemListServlet" method="post">
<input type="submit" value="メニュー画面">
</form>
</body>
</html>