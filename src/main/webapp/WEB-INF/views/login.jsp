<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ネットでピザ注文</title>
<link href="../css/bootstrap.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

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
		</nav>

		<!-- login form -->
		<div class="row">
			<div
				class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">
				<div class="well">
					<form:form action="${pageContext.request.contextPath}/login"  >
						<fieldset>
							<legend> ログイン </legend>
							<div class="form-group">
								<div class="loginError">
								 	<c:out value="${loginError}" /> 
								</div> 
								<label for="inputEmail">メールアドレス:</label>
							<%-- 	<form:errors path="email" cssStyle="color:red" element="div">
								</form:errors> --%>
								<input type="text" id="inputEmail" name="email"
									class="form-control" placeholder="Email" />
							</div>
							<div class="form-group">
								<label for="inputPassword">パスワード:</label>
<%-- 								<form:errors path="password" cssStyle="color:red" element="div">
								</form:errors>
 --%>								<input type="password" id="inputPassword" name="password"
									class="form-control" placeholder="Password" />
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">ログイン</button>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="text-center">
				<a
					href="${pageContext.request.contextPath}/registerController/toRegisterUser">ユーザ登録はこちら</a>
			</div>
		</div>

	</div>
	<!-- end container -->
</body>
</html>