<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TOMU</title>
</head>
<body>
<h1>管理者メニュー画面</h1>
<form action="AdminItemServlet" method="get">
<input type="submit" value="商品登録">　商品の新規登録、変更、削除を行えます
</form>
<br>
<form action="ItemListServlet" method="get">
<input type="submit" value="商品一覧">　登録されている商品の一覧が表示され、発注を行えます
</form>
<br>
<form action="SummaryServlet" method="get">
<button type="submit" value="kanri"  name="kbn">月別商品別仕入表</button>　発注履歴データより指定年月の商品別仕入れ集計表が表示されます
</form>
<br>
<form action="SummaryServlet" method="get">
<button type="submit" value="user"  name="kbn">月別商品別売上表</button>　買い物履歴データより指定年月の商品別売上げ集計表が表示されます
</form>
<br>
<form action="AdminLoginServlet" method="get">
<input type="submit" value="ログアウト">
</form>
</body>
</html>