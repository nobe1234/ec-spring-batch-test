<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/css/bootstrap.css" rel="stylesheet">
<link href="/css/piza.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<title>全ての注文【管理者用ページ】</title>
</head>
<body>
	<sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
		<!-- <sec:authentication var="userName" property="principal.user.name" /> -->
		</sec:authorize>
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
						<a href="${pageContext.request.contextPath}/viewCartContent/view"
							class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;

						<%-- 								<a href="${pageContext.request.contextPath}/" class="navbar-link">注文履歴</a>&nbsp;&nbsp; --%>
						<a href="${pageContext.request.contextPath}/help"
							class="navbar-link" target=”_blank”>お困りの方はこちら</a>&nbsp;&nbsp; <a
							href="${pageContext.request.contextPath}/showItem/index"
							class="navbar-link">トップページへ</a>&nbsp;&nbsp;
						<sec:authorize
							access="hasRole('ROLE_MEMBER') and isAuthenticated()">
							<sec:authentication var="userName" property="principal.user.name" />
						</sec:authorize>
						&nbsp;&nbsp;


						<c:choose>
							<c:when test="${userName == null}">
								<a href="${pageContext.request.contextPath}/"
									class="navbar-link">ログイン</a>&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
								<c:out value="${userName}" />&nbsp;さん
								<a href="${pageContext.request.contextPath}/logout"
									class="navbar-link">ログアウト</a>
							</c:otherwise>
						</c:choose>
					</p>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>

		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<table class="table table-striped">
					<tbody>
						<tr>
							<td>注文番号</td>
							<td>日付</td>
							<td>利用者名</td>
							<td>現在のステータス</td>
							<td>総計（税込）</td>
						</tr>
						<c:forEach var="order" items="${orderList}" varStatus="status">
							<tr>
								<td><c:out value="${order.order_number}" /></td>
								<!-- 	<td><c:out value="${order.orderDate}" /></td> -->
								<td><fmt:formatDate value="${order.orderDate}"
										pattern="yyyy/MM/dd" /></td>
								<%-- 									<c:forEach items="${order}" var="user">
									<td><c:out value="${user.name}" /></td>
									</c:forEach> --%>
								<td><c:out value="${order.destinationName}" />様</td>
								<td><c:if test="${order.status == '0' }">注文前</c:if> <c:if
										test="${order.status == '1' }">未入金</c:if> <c:if
										test="${order.status == '2' }">入金済</c:if> <c:if
										test="${order.status == '3' }">発送済</c:if> <c:if
										test="${order.status == '9' }">キャンセル</c:if></td>
								<td><fmt:formatNumber value="${order.totalPrice}"
										pattern="###,###円" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- table end -->

		<button type="button"
			onclick="location.href='${pageContext.request.contextPath}/downloadCsv/outputCsv'">
			ユーザー情報一括ダウンロード(csv)</button>

	</div>

</body>
</html>