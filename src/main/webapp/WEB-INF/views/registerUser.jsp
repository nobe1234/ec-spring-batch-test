<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ネットでピザ注文</title>
<link href="../css/bootstrap.css" rel="stylesheet">
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
		</nav>

		<!-- login form -->
		<div class="row">
			<div
				class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">
				<div class="well">
					<form:form modelAttribute="registerUserForm"
						action="${pageContext.request.contextPath}/registerController/registerUser" >
						<fieldset>
							<legend>ユーザ登録</legend>
							<div class="form-group">
								<form:errors path="name" cssStyle="color:red" element="div" />
								<label for="inputName">名前:</label>
								<form:input id="inputName" path="name" class="form-control"
									placeholder="Name" />
							</div>
							<div class="form-group">
								<form:errors path="email" cssStyle="color:red" element="div" />
								<label for="inputEmail">メールアドレス:</label>
								<form:input id="inputEmail" path="email" class="form-control"
									placeholder="Email" />
							</div>
							<div class="form-group">
								<form:errors path="zipcode" cssStyle="color:red" element="div" />
								<label for="inputZipcode">郵便番号: （ハイフンなし、数字７桁で入力してください　例:1001000）</label>
								<!--  <input type="button"
									value="住所検索"> -->
								<form:input id="inputZipcode" path="zipcode"
									class="form-control" placeholder="Zipcode"
									onKeyUp="AjaxZip3.zip2addr(this,'','address','address')" />

							</div>
							<div class="form-group">
								<form:errors path="address" cssStyle="color:red" element="div" />
								<label for="inputAddress">住所:</label>
								<form:input id="inputAddress" path="address"
									class="form-control" placeholder="Address" />
							</div>
							<div class="form-group">
								<form:errors path="telephone" cssStyle="color:red" element="div" />
								<label for="inputTel">電話番号:（携帯番号を(-)も含めて入力してください 例:090-0000-0000）</label>
								<form:input id="inputTel" path="telephone" class="form-control"
									placeholder="Tel" />
							</div>
							<div class="form-group">
								<form:errors path="password" cssStyle="color:red" element="div" />
								<label for="inputPassword">パスワード:（8~16文字で数字大文字小文字を1つ以上入れて入力してください）</label>
								<form:password id="inputPassword" path="password"
									class="form-control" placeholder="Password" />
							</div>
							<div class="form-group">
								<form:errors path="confirmPassword" cssStyle="color:red"
									element="div" />
								<label for="inputConfirmationPassword">確認用パスワード:</label>
								<form:password id="inputConfirmationPassword"
									path="confirmPassword" class="form-control"
									placeholder="Confirmation Password" />
							</div>
							<div class="form-group">
								<button type="submit" class="btn btn-primary">登録</button>
								<button id="reset" class="btn btn-primary">クリア</button>
							</div>
							<form:form name="resetForm"> <!-- ダミーフォーム --> </form:form>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>

	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>

</body>
</html>