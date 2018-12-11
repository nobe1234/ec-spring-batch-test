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
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/piza.css" rel="stylesheet">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="/js/realtime.js"></script>

<script> //オートコンプリート機能
$(function(){
	var names = [];
	
	<c:forEach var="item" items="${items}">
		names.push("<c:out value="${item.name}"/>");
	</c:forEach>
	$("#autocomplete").autocomplete({
			source : names,
			minLength: 1
		});
});	
</script>

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
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<p class="navbar-text navbar-right">

						<a href="${pageContext.request.contextPath}/viewCartContent/view" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
<%-- 						<a href="${pageContext.request.contextPath}/" class="navbar-link">注文履歴</a>&nbsp;&nbsp; --%>
						
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