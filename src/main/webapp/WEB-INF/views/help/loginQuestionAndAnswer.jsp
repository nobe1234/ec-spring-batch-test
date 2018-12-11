<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/background.css" rel="stylesheet">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script type="text/javascript">

$(function() {
  $("#wkTxt").tooltip({
    show:false,
    hide:false
  });
});

</script>
</head>
<body>
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
						aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/showItem/index"> <!-- 企業ロゴ -->
						<img alt="main log" src="../img/header_logo.png" height="35">
					</a>
					</div>
					<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					 <p class="navbar-text navbar-right">
					 <a href="${pageContext.request.contextPath}/showItem/index" class="navbar-link">トップページへ</a>&nbsp;&nbsp;
					</p>
					</div>
				</div>
					</nav>
				
<!-- <div class="question"> -->	
<!-- <table><tr><td> -->
<h1>何かお困りですか？</h1>
<!-- </td></tr></table> -->
<pre>
Q.画面表示がおかしい
A.当社のウェブサイトでは、より安全で快適にご利用いただくために下記のブラウザ、あるいはプラグインなどが必要です。ご利用のソフトウェアのバージョンをご確認の上、ダウンロードしてください。
 <a href="#chrome">推奨ブラウザ</a> <!-- Jqueryで出せると良い -->
</pre>

<pre>
Q.入会費・利用料について
A.当店は会員登録料・月額費用などはいただいておりません。 
</pre>
<pre>
Q.ログインができない/パスワードを忘れた
A.<a href="#abc">カスタマーサポート</a>までご連絡ください。
<!-- パスワード確認処理 -->
</pre>
<pre>
Q.カートに商品がない
A.もし以前ログインした状態で商品をカートに入れたのであれば、ログインをお試しください。 
→ <a href="${pageContext.request.contextPath}/" class="navbar-link">ログイン画面はこちら</a>
</pre>
<pre>
Q.注文した商品が届かない
A.交通状況により、遅延が発生する可能性がございます。各店舗までお問い合わせください。
</pre>

<pre>
Q.注文完了メールが届かない
A.混雑などにより、遅延が発生する可能性がございます。しばらくたっても届かない場合は<a href="#abc">カスタマーサポート</a>までご連絡ください。
</pre>
<pre>
Q.退会したい
A.誠に残念ではございますが、<a href="${pageContext.request.contextPath}/deleteUser/delete">こちら</a>から退会できます。またご利用いただける日を従業員一同、心よりお待ちしております。
<!-- 退会先のリンク -->
</pre>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<pre id="chrome">
推奨ブラウザ: Internet Explorer：8.0以上
            FireFox：最新版
            Google Chrome：最新版
            
 【タブブラウザをお使いのお客様へ】
複数タブを開いて操作を行った場合、ご注文や各種ご登録内容のご変更手続きが正常に行われない場合がございます。
複数のタブで同時に操作を行わず、一画面（一つのタブ）で操作をしていただきますようお願いいたします。
  
 【プラグインなど】
ウェブサイト内の一部のページでは、Adobe Flash Player (Flash Player 8.0以上) が必要となります。
</pre>

<pre id="abc">
もし解決しない場合は下記のカスタマーサポートへご連絡ください。
TEL:0120-0000-0000
mail:support@rakurakupiza.co.jp
<!-- 問い合わせフォーム作れれば -->
</pre>
<!-- </div> -->
</div>
</body>
</html>