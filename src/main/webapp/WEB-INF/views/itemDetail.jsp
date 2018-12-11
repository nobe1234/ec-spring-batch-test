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
<title>ピザ屋のネット注文</title>
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/piza.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="/js/realtime.js"></script>

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
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span> 
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${pageContext.request.contextPath}/showItem/index"> <!-- 企業ロゴ -->
						<img alt="main log" src="../img/header_logo.png" height="35">
					</a>
				</div>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<p class="navbar-text navbar-right">
								<a href="${pageContext.request.contextPath}/viewCartContent/view" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;

<%-- 								<a href="${pageContext.request.contextPath}/" class="navbar-link">注文履歴</a>&nbsp;&nbsp; --%>
					 <a href="${pageContext.request.contextPath}/help" class="navbar-link" target=”_blank”>お困りの方はこちら</a>&nbsp;&nbsp;
					 <a href="${pageContext.request.contextPath}/showItem/index" class="navbar-link">トップページへ</a>&nbsp;&nbsp;
							<sec:authorize access="hasRole('ROLE_MEMBER') and isAuthenticated()">
							<sec:authentication var="userName" property="principal.user.name" />
							</sec:authorize>&nbsp;&nbsp;


						<c:choose>
							<c:when test="${userName == null}">
								<a href="${pageContext.request.contextPath}/" class="navbar-link">ログイン</a>&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
									<c:out value="${userName}" />&nbsp;さん
								<a
									href="${pageContext.request.contextPath}/logout"
									class="navbar-link">ログアウト</a>
							</c:otherwise>
						</c:choose>
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<form:form modelAttribute="insertItemForm" action="${pageContext.request.contextPath}/insertItem/insert">
		<div class="row">
			<div class="col-xs-offset-2 col-xs-8">

				<h3 class="text-center">商品詳細</h3>
				<div class="row">
					<div class="col-xs-5">
						<img src="<c:out value="${itemDetail.imagePath}"/>" class="img-responsive img-rounded">
					</div>
					<div class="col-xs-5">
						<div class="bs-component">
							<h4><c:out value="${itemDetail.name}"/></h4> <br>
							<br>
							<p><c:out value="${itemDetail.description}"/></p>
						</div>
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-offset-2 col-xs-8">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<label for="inputResponsibleCompany">サイズ</label>
								</div>
								<div class="col-sm-12">
									<label class="radio-inline"> 
										<form:radiobutton path="size" name="responsibleCompany" value='M' class="size" checked="checked"/>
										<span class="price">&nbsp;М&nbsp;</span>&nbsp;&nbsp;
										<span class="priceM"><fmt:formatNumber pattern="###,###" value="${itemDetail.priceM}"/></span>
										円(税抜)<br>
									</label>
									<label class="radio-inline"> 
										<form:radiobutton path="size" name="responsibleCompany" value='L' class="size" />
										<span class="price">&nbsp;L&nbsp;</span>&nbsp;&nbsp;
										<span class="priceL"><fmt:formatNumber pattern="###,###" value="${itemDetail.priceL}" /></span>
										円(税抜)<br>
									</label>
								</div>
							</div>
						</div>
					</div>
				</div><br>
				<div class="row">
					<div class="col-xs-offset-2 col-xs-8">
						<div class="form-group">
							<div class="row">
								<div class="col-sm-12">
									<label for="inputResponsibleCompany">
										トッピング：&nbsp;1つにつき
										<span>&nbsp;М&nbsp;</span>&nbsp;&nbsp;200円(税抜)
										<span>&nbsp;L</span>&nbsp;&nbsp;300円(税抜)
									</label>
								</div>
								<div class="col-sm-12">
								<c:forEach var="topping" items="${toppingMap}">
									<label class="checkbox-inline">
								 		<form:checkbox path="toppingList" class="toppings" value="${topping.key}"/>
								 		<c:out value="${topping.value}"/>
									</label>
								</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-offset-2 col-xs-8">
						<div class="form-group">
							<div class="row">
								<div class="col-xs-5 col-sm-5">
									<label for="">数量:</label>
									<label class="control-label" style="color: red" for="inputError">数量を選択してください</label> 
									<form:select path="quantity" items="${quantityMap}" name="area" id="quantity" class="form-control"/>
								</div>
							</div>
						</div>
					</div>
				</div>
				<br>
				<div class="row">
					<div class="col-xs-offset-2 col-xs-10">
						<div class="form-group">
							<span id="total-price">この商品金額：0円(税抜)</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-offset-2 col-xs-3">
						<div class="form-group">
							<p>
								<input class="form-control btn btn-warning btn-block" type="submit" value="カートに入れる">
								<input type="hidden" name="itemId" value="${itemDetail.id}">
							</p>
							
						</div>
					</div>
				</div>
			</div>
		</div>
		</form:form>

	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="../js/bootstrap.min.js"></script>
</body>
</html>