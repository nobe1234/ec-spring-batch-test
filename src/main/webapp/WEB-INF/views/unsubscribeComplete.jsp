<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ネットでピザ注文</title>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/slide.css" rel="stylesheet">
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="/js/registerUser.js"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
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

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					 <p class="navbar-text navbar-right">
					 <a href="${pageContext.request.contextPath}/help" class="navbar-link" target=”_blank”>お困りの方はこちら</a>&nbsp;&nbsp;
					 <a href="${pageContext.request.contextPath}/showItem/index" class="navbar-link">トップページへ</a>&nbsp;&nbsp;
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			
			<!-- /.container-fluid -->
		<!-- /.container-fluid -->
		</nav>

		<h3 class="text-center">退会が完了しました</h3>
		<h3 class="text-center">ありがとうございました</h3>
		
		<div class="row">
			<div class="col-xs-offset-5 col-xs-3">
				<div class="form-group">
					<form action="${pageContext.request.contextPath}/logout">
						<input class="form-control btn btn-warning btn-block"
							type="submit" value="トップページへ">
					</form>
				</div>
			</div>
		</div>

		
		<div class="album">
			<img class="img1" src="/img/ojigi_woman1.png" alt="ojigi1">
			<img class="img2" src="/img/ojigi_woman2_esyaku.png" alt="ojigi2">
			<img class="img3" src="/img/ojigi_woman3_keirei.png" alt="ojigi3">
			<img class="img4" src="/img/ojigi_woman4_saikeirei.png" alt="ojigi4">
		</div>
		
		<!-- <!-- ロード -->
		<div class="anime_test">
		</div> -->

		<%-- <a href="${pageContext.request.contextPath}/logout">トップページに戻る</a> --%>
</div>
</body>
</html>