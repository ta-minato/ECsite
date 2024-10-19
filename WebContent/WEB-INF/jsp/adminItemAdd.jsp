<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item " %>
<%
//セッションスコープに保存されたユーザー情報を取得
Item item =(Item)session.getAttribute("item");
String findcode = item.getItemcode();
if(findcode == null) {
	findcode = "";
};

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
<h1>商品登録・削除画面</h1>
<form action="ItemListServlet" method="post">
<input type="submit" value="戻る">
</form>
<br>
<form action="AdminItemServlet" method="get">
<table border="1">
	<tr><td>商品コード</td><td><input type="text" name="itemcode"  required maxlength=4 value=<%=findcode %>></td><td><input type="submit" value="検索"></td></tr>
</table>
<br>
</form>
<%if(item.getItemcode() == null)  { } else {%>
<form action="AdminItemServlet" method="post"  onSubmit="return check()">
<table border="1" >
	<tr><td>商品コード</td><td><input type="text"  maxlength=4 name="itemcode" readonly="readonly"   value=<%= item.getItemcode() %>> </td></tr>
	<tr><td>商品名</td><td><input type="text" name="itemname" required value=<%= item.getItemname() %> > </td></tr>
	<tr><td>販売価格</td><td><input type="number" name="price" value=<%= item.getPrice() %> > </td></tr>
	<tr><td>仕入価格</td><td><input type="number" name="cost" value=<%= item.getCost() %> > </td></tr>
	<tr><td>カテゴリ</td><td><input type="text" name="category" required value=<%= item.getCategory() %> > </td></tr>
</table>
<br>
<input type="submit" value="登録" name="button">
<% if(item.getItemname() != "") { %>
<input type="submit" value="削除" name="button"><br>
</form>
<% } %>

<script type="text/javascript">
function check(){
	if(window.confirm('実行してよろしいですか？')){ // 確認ダイアログを表示
		return true; // 「OK」時は実行
	}
	else{ // 「キャンセル」時の処理
		window.alert('キャンセルしました'); // 警告ダイアログを表示
		return false; // 登録を中止
	}
}
</script>
<% } %>

<% if(errorMsg != null) { %>
<p><%= errorMsg %></p>
<% } %>
</body>
</html>