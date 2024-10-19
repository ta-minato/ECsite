<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item  ,java.util.List" %>
<%
//リクエストスコープに保存された成績管理リストを取得
List<Item> itemLists = (List<Item>)request.getAttribute("itemLists");
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
<h1>商品一覧画面</h1>
<form action="ItemOrderServlet"  method="get">
<br>
<style>
.itemlist{
    overflow: auto;
    width: 100%;
    height: 420px;

}
.itemlist table{
    margin: 0;
    border-spacing: 0;

}
.itemlist th{
    background: #f2f2f2;
    position: sticky;
    top: 0;
    left: 0;
}
.itemlist tr:first-child th{
    border-top: 1px solid #999;
}
.itemlist th:first-child{
    border-left: 1px solid #999;
}
.itemlist tr:first-child th:first-child{
    z-index: 1;
}
}</style>
<div class="itemlist">
	<table border="1">
  	<tr>
  	  <th>商品コード</th>
  	  <th>商品名</th>
  	  <th>　 在庫数　</th>
  	  <th>　販売価格　 </th>
  	  <th>　売上数　</th>
  	  <th>　売上金額　</th>
  	  <th>　仕入単価　</th>
  	  <th>　仕入数　</th>
  	  <th>　仕入金額　</th>
  	  <th>発注へ</th>
 	 </tr>
	<% if (itemLists != null) {  %>
	<% 	for(Item item : itemLists) { %>
 	 <tr>
		<td ><%= item.getItemcode() %></td>
		<td><%= item.getItemname() %></td>
		<td align="right"><%= String.format("%,d",item.getRestocks() - item.getSales()) %></td>
		<td align="right"><%= String.format("%,d",item.getPrice()) %></td>
		<td align="right"><%= String.format("%,d",item.getSales()) %></td>
		<td align="right"><%= String.format("%,d",item.getPrice() * item.getSales()) %></td>
		<td align="right"><%= String.format("%,d",item.getCost()) %></td>
		<td align="right"><%= String.format("%,d",item.getRestocks()) %></td>
		<td align="right"><%= String.format("%,d",item.getCost() *  item.getRestocks()) %></td>
		<td align="center"><button type="submit" value=<%= item.getItemcode() %> name="order">発注</button></td>
		</tr>
	<% 	} %>
	<% } %>
	</table>
</div>
<br>
</form>
<form action="ItemListServlet" method="post">
<input type="submit" value="戻る">
</form>
<br>
<form action="AdminLoginServlet" method="get">
<input type="submit" value="ログアウト">
</form>

</body>
</html>