<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item " %>
<%
//セッションスコープに保存されたユーザー情報を取得
Item item =(Item)session.getAttribute("item");
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
<h1>商品発注画面</h1>
<form action="ItemListServlet" method="get">
<input type="submit" value="戻る">
</form>
<br>
<form action="ItemOrderServlet" method="post"  onSubmit="return check()">
<table border="1" >
	<tr><td>商品コード</td><td><%= item.getItemcode() %> </td></tr>
	<tr><td>商品名</td><td><%= item.getItemname() %></td></tr>
	<tr><td>カテゴリ</td><td><%= item.getCategory() %></td></tr>
	<tr><td>販売価格</td><td align="right"><%= String.format("%,d",item.getPrice()) %></td></tr>
	<tr><td>売上数</td><td align="right"><%= String.format("%,d",item.getSales()) %> </td></tr>
	<tr><td>売上金額</td><td align="right"><%= String.format("%,d",item.getPrice() * item.getSales()) %></td></tr>
	<tr><td>仕入価格</td><td align="right"><%= String.format("%,d",item.getCost()) %></td></tr>
	<tr><td>仕入数</td><td><input type="number"  name="restocks" value=0 > </td></tr>
</table>
<br>
<% if(item.getItemname() != "") { %>
<input type="submit" value="発注">
<% }%>
</form>
<script type="text/javascript">
function check(){
	if(window.confirm('発注してよろしいですか？')){ // 確認ダイアログを表示
		return true; // 「OK」時は登録を実行
	}
	else{ // 「キャンセル」時の処理
		window.alert('キャンセルしました'); // 警告ダイアログを表示
		return false; // 登録を中止
	}
}
</script>

<% if(errorMsg != null) { %>
<p><%= errorMsg %></p>
<% } %>
</body>
</html>