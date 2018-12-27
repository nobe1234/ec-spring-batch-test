<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ピザ屋のネット注文</title>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/piza.css" rel="stylesheet">
<link href="../css/form.css" rel="stylesheet">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/js/cookie.js"></script>
<script src="/js/productIntroduction.js"></script>

<!-- <script src="/js/setCookie.js"></script> -->
<!-- <script src="/js/recent.js"></script -->
<script>
	$(function() {
		var names = [];

		<c:forEach var="item" items="${items}">
		names.push("<c:out value="${item.name}"/>");
		</c:forEach>
		$("#autocomplete").autocomplete({
			source : names,
			minLength : 1
		});
	});
</script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body id="load">

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
						<a href="${pageContext.request.contextPath}/downloadCsv/"
							class="navbar-link">注文一覧【管理者】</a> <a
							href="${pageContext.request.contextPath}/viewCartContent/view"
							class="navbar-link">ショッピングカート</a> <a
							href="${pageContext.request.contextPath}/introductionCompany/index"
							class="navbar-link">会社紹介</a>&nbsp;&nbsp;
						<sec:authorize access=" isAuthenticated()">
							<sec:authentication var="userName" property="principal.user.name" />
						</sec:authorize>
						&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/help"
							class="navbar-link" target=”_blank”>お困りの方はこちら</a>&nbsp;&nbsp;
						<c:choose>
							<c:when test="${userName == null}">
								<a
									href="${pageContext.request.contextPath}/registerController/toRegisterUser"
									class="navbar-link">ユーザ登録はこちら</a>&nbsp;&nbsp;
								<a href="${pageContext.request.contextPath}/"
									class="navbar-link">ログイン</a>&nbsp;&nbsp;
							</c:when>
							<c:otherwise>
								<c:out value="${userName}" />&nbsp;さん
								<a href="${pageContext.request.contextPath}/logout"
									class="navbar-link">ログアウト</a>&nbsp;&nbsp;
								<a href="${pageContext.request.contextPath}/orderHistory/"
									class="navbar-link">注文履歴</a>
							</c:otherwise>
						</c:choose>
					</p>
				</div>
				<!-- /.navbar-collapse -->
				<!-- /.container-fluid -->
				<!-- 			<div id="result">
				<script type="text/javascript">
					var k = document.cookie;
					k = k.replace('itemInfo=', '');
					k = decodeURIComponent(k);
					k = k.replace(/,/g, '');
					$('#result').html(k);
				</script> -->
			</div>
		</nav>
		<c:if test="${cookieItemList != null }">
			<p>最近見た商品</p>
			<table>
				<tr>
					<c:forEach items="${cookieItemList}" var="cookieItem"
						varStatus="cookieStatus">
						<td><img src="<c:out value="${cookieItem.imagePath}" />"
							class="img-responsive img-rounded" width="200" height="150">
							<c:out value="${cookieItem.name}"></c:out></td>
					</c:forEach>
				</tr>
			</table>
		</c:if>

		<!-- 	<script type="text/javascript">
		alert('最近見た商品を読み込んでます');
		load_recentlist();
	</script>
	<div id="recent_item"></div> -->
		<div class="row">
			<div
				class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-10 col-xs-12">

				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">商品を検索する</div>
					</div>
					<div class="panel-body">
						<form:form
							action="${pageContext.request.contextPath}/showItem/search"
							class="form-horizontal">
							<div class="form-group">
								<label for="autocomplete" class="control-label col-sm-2">商品名</label>
								<div class="col-sm-9">
									<input type="text" name="name" id="autocomplete"
										class="form-control input-sm" placeholder="検索キーワード" />
								</div>
							</div>
							<c:if test="${message != null}">
								<div class="text-center">
									<p>
										<c:out value="${message}" />
									</p>
								</div>
							</c:if>
							<div class="text-center">
								<button type="submit" value="検索" class="btn btn-primary">検索</button>
								<button type="reset" value="クリア" class="btn btn-default">クリア</button>
							</div>
						</form:form>
						<div style="display: inline-flex">
							<div class="text-center">
								<form:form
									action="${pageContext.request.contextPath}/showItem/sortPrice"
									class="form-horizontal">
					並び替え&nbsp;&nbsp;<input type="submit" value="安い順"
										class="btn btn-primary">
								</form:form>
								<form:form
									action="${pageContext.request.contextPath}/showItem/sortPriceDesc"
									class="form-horizontal">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" value="高い順" class="btn btn-primary">
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<table class="table table-striped">
					<tbody>
						<tr>
							<c:forEach var="item" items="${items}" varStatus="status">
								<td><a
									href="${pageContext.request.contextPath}/showItemDetail/detail?id=<c:out value="${item.id}"/>">
										<img src="<c:out value="${item.imagePath}" />"
										class="img-responsive img-rounded" width="400" height="300"
										id="<c:out value="${item.id}"/>"
										title="<c:out value="${item.description}"/>">
								</a><br> <a class="${item.name}"
									href="${pageContext.request.contextPath}/showItemDetail/detail?id=<c:out value="${item.id}"/>">
										<c:out value="${item.name}"></c:out><br>
								</a><br> <span class="price">&nbsp;М&nbsp;</span>&nbsp;&nbsp; <fmt:formatNumber
										pattern="###,###" value="${item.priceM}" />円(税抜)<br> <span
									class="price">&nbsp;L&nbsp;</span>&nbsp;&nbsp; <fmt:formatNumber
										pattern="###,###" value="${item.priceL}" />円(税抜)<br> <br>
								</td>
								<%-- <c:if test="${item.id % 3 == 0}"> --%>
								<c:if test="${status.count % 3 == 0 }">
						</tr>
						<tr>
							</c:if>
							</c:forEach>
						</tr>
					</tbody>
				</table>
				<script src="/js/clickedPaging.js"></script>
				<div class="form_conf">
					<form:form id="page1Form" method="get"
						action="${pageContext.request.contextPath}/showItem/index"
						modelAttribute="pageForm" name="pageForm">
						<input id="pageOneLink" type="button" name="pageNumber" value="1">
						<input id="pageNumber1" type="hidden" name="pageNumber" value="0">
				
				</form:form>
					<form:form id="page2Form" method="get"
						action="${pageContext.request.contextPath}/showItem/index"
						modelAttribute="pageForm" name="pageForm">
						<input id="pageNumber2" type="hidden" name="pageNumber" value="10">
						<input id="pageTwoLink" type="button" name="pageNumber" value="2">
					</form:form>
				</div>
			</div>
		</div>
		<!-- table end -->
	</div>

</body>
</html>
