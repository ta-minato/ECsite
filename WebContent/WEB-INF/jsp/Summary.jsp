<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="model.Summary  ,java.util.List" %>
<%
//リクエストスコープに保存された集計リストを取得
List<Summary> summaryLists = (List<Summary>)request.getAttribute("summaryLists");
//リクエストスコープに保存された総合計を取得
Summary totalSummary = (Summary)request.getAttribute("summary");
//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");
//セッションスコープに保存された区分を取得
String kbn =(String)session.getAttribute("kbn");
//セッションスコープに初期設定年月を取得
String year =(String)session.getAttribute("year");
String month =(String)session.getAttribute("month");

String title = "";
String title1 = "";
String title2 = "";
if (kbn.equals("kanri")) {
	title = "商品別仕入れ集計表";
	title1 = "仕入れ数";
	title2 = "仕入金額";
}
else {
	title = "商品別売上げ集計表";
	title1 = "売上げ数";
	title2= "売上金額";
}

%>
 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TOMU</title>
</head>
<body>
<h1><%= title %></h1>
<br>
<form action="SummaryServlet" method="post">
<table border="1">
	<tr><td><input type="number" name="year"  required min=2020 max=2050  value=<%= year %>>年</td><td><input type="number" name="month"  required min=1 max=12  value=<%= month %>>月</td><td><input type="submit" value="検索"></td></tr>
</table>
</form>
<% if (summaryLists != null) {  %>
<style>
.summarylist{
    overflow: auto;
    width: 100%;
    height: 400px;

}
.summarylist table{
    margin: 0;
    border-spacing: 0;

}
.summarylist th{
    background: #f2f2f2;
    position: sticky;
    top: 0;
    left: 0;
}
.summarylist tr:first-child th{
    border-top: 1px solid #999;
}
.summarylist th:first-child{
    border-left: 1px solid #999;
}
.summarylist tr:first-child th:first-child{
    z-index: 1;
}
}</style>
<div class="summarylist">
	<table border="1">
  	<tr>
  	  <th>商品コード</th>
  	  <th>商品名</th>
  	  <th><%= title1 %></th>
  	  <th><%= title2 %></th>
 	 </tr>
	<% 	for(Summary summary : summaryLists) { %>
 	 <tr>
		<td ><%= summary.getItemcode() %></td>
		<td><%= summary.getItemname() %></td>
		<td align="right"><%= String.format("%,d",summary.getAmount()) %></td>
		<td align="right"><%= String.format("%,d",summary.getTotalprice()) %></td>
		</tr>
	<% 	} %>
 	 <tr>
		<td colspan=2 align="center">合　　計</td>
		<td align="right"><%= String.format("%,d",totalSummary.getAmount()) %></td>
		<td align="right"><%= String.format("%,d",totalSummary.getTotalprice()) %></td>
	</tr>
	</table>
</div>
<% } %>
<br>
<form action="ItemListServlet" method="post">
<input type="submit" value="戻る">
</form>
<br>
<form action="AdminLoginServlet" method="get">
<input type="submit" value="ログアウト">
</form>
</body>
</html>