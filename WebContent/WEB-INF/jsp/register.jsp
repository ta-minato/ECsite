<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%

//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String)request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TOMU</title>
<link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" />
<script src="https://yubinbango.github.io/yubinbango/yubinbango.js" charset="UTF-8">;</script>
</head>
<header class="header">
<div>
<img src="${pageContext.request.contextPath}/imgs/header.png" alt="TOMU">
</div>
</header>
<body>
<% if(errorMsg != null){ %>
<p class="errorMsg"><%= errorMsg %></p>
<% } %>

<h1>ユーザー新規登録画面</h1>
<div class="login-container">
	<div class="form-group">
		<form class="h-adr" action="UserServlet" method="post" onSubmit="return check()">
			<label for="username">ユーザーID</label>
			<input type="text" name="userid" pattern="^([a-zA-Z0-9]{3,10})$" required>
			<label for="username">パスワード</label>
			<input type="password" name="pass" required>
			<label for="username">名前</label>
			<input type="text" name="username" required>
			<label for="username">メールアドレス</label>
			<input type="email" name="mail" required >
			<label for="username">生年月日</label>
			<input type="date" name="birthday" required>

		<fieldset>
				<legend>性別</legend>
				<div>
					<input type="radio" id="contactChoice1" name="gender" value="1" checked />
					<label for="contactChoice1">男</label>
					<input type="radio" id="contactChoice2" name="gender" value="2" />
					<label for="contactChoice2">女</label>
				</div>
			</fieldset>

			<span class="p-country-name" style="display:none;">Japan</span>
			<label>郵便番号</label>
			<input type="text" name="zipcode" class="p-postal-code" required><br>
			<label>住所</label>
			<input type="text" name="address" class="p-region p-locality p-street-address p-extended-address" required><br>
<br>
			<input type="submit" class="button2" name="toroku" value="登録">

		</form>
<br>
		<a href="LoginServlet">戻る</a>

	</div>
</div>

<script type="text/javascript">
	function check(){
		if(window.confirm('送信してよろしいですか？')){ // 確認ダイアログを表示
			return true; // 「OK」時は送信を実行

		}
		else{ // 「キャンセル」時の処理
			window.alert('キャンセルしました'); // 警告ダイアログを表示
			return false; // 送信を中止
		}
	}

</script>

</body>
</html>