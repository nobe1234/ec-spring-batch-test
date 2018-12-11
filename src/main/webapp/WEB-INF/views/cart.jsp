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
<script src="/js/deleteConfirm.js"></script>

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

						<!-- <a href="cart_list.html" class="navbar-link">ショッピングカート</a>&nbsp;&nbsp; -->
<!-- 						<a href="order_history.html" class="navbar-link">注文履歴</a>&nbsp;&nbsp; -->
						
							<sec:authorize access="hasRole('ROLE_MEMBER') and isAuthenticated()">
							<sec:authentication var="userName" property="principal.user.name" />
							</sec:authorize>&nbsp;&nbsp;
					 <a href="${pageContext.request.contextPath}/help" class="navbar-link" target=”_blank”>お困りの方はこちら</a>&nbsp;&nbsp;
					 <a href="${pageContext.request.contextPath}/showItem/index" class="navbar-link">トップページへ</a>&nbsp;&nbsp;
							

						<c:choose>
							<c:when test="${userName == null}">
								<a href="${pageContext.request.contextPath}/"
									class="navbar-link">ログイン</a>&nbsp;&nbsp;
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
		
		<c:if test="${order == null}">
			カートに商品がありません<br>
		</c:if><br>

		<c:if test="${order != null}">
		<!-- table -->
		<div class="row">
			<div
				class="table-responsive col-lg-offset-1 col-lg-10 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
				<h3 class="text-center">ショッピングカート</h3>
				<table class="table table-striped">
					<tbody>
						<tr>
							<th>
								<div class="text-center">
									商品名
								</div>
							</th>
							<th>
								<div class="text-center">
									サイズ、価格(税抜)、数量
								</div>
							</th>
							<th>
								<div class="text-center">
									トッピング、価格(税抜)
								</div>
							</th>
							<th>
								<div class="text-center">
									小計
								</div>
							</th>
							<th>
							</th>
						</tr>
						
						<!-- 確認用 -->
						<c:forEach var="orderItem" items="${order.orderList}">
						<tr>
							<td>
								<div class="center">
									<img src="<c:out value="${orderItem.item.imagePath}"/>" class="img-responsive img-rounded" width="100" height="300"><br>
									<c:out value="${orderItem.item.name}"/>
								</div>
							</td>
							<td>
								<span class="price">&nbsp;<c:out value="${orderItem.size}"/></span>
										<c:if test="${orderItem.stringSize == 'M'}">
											&nbsp;&nbsp;<fmt:formatNumber pattern="###,###" value="${orderItem.item.priceM}"/>円
										</c:if>
										<c:if test="${orderItem.stringSize == 'L'}">
											&nbsp;&nbsp;<fmt:formatNumber pattern="###,###" value="${orderItem.item.priceL}"/>円
										</c:if>
									&nbsp;&nbsp;<c:out value="${orderItem.quantity}"/>個
							</td>
							<td>
								<ul>
									<c:if test="${orderItem.orderToppingList == null}">
									未選択
									</c:if>
									<c:forEach var="topping" items="${orderItem.orderToppingList}">
										<li><c:out value="${topping.topping.name}"/>
											<c:if test="${orderItem.stringSize == 'M'}">
												<c:out value="${topping.topping.priceM}"/>円
											</c:if>
											<c:if test="${orderItem.stringSize == 'L'}">
												<c:out value="${topping.topping.priceL}"/>円
											</c:if>	
										</li>
									</c:forEach>
								</ul>
							</td>
							<td>
								<div class="text-center">
									<fmt:formatNumber pattern="###,###" value="${orderItem.subTotal}"/>円
								</div>
							</td>
							<td>
								<div class="text-center">
								<form:form action="${pageContext.request.contextPath}/delete/delete" method="post">
									<input type="submit" value="削除" id="delete" class="btn btn-primary">
									<input type="hidden" name="orderItemId" value="${orderItem.id}">
								</form:form>
								</div>
							</td>
						</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>
	
		<div class="row">
			<div class="col-xs-offset-2 col-xs-8">
				<div class="form-group text-center">
					<span id="total-price">消費税：<fmt:formatNumber pattern="###,###" value="${order.tax}"/>円</span><br>
					<span id="total-price">ご注文金額合計：<fmt:formatNumber pattern="###,###" value="${order.calcTotalPrice + order.tax}"/>円(税込)</span>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-xs-offset-5 col-xs-3">
				<div class="form-group">
					<form action="${pageContext.request.contextPath}/order/toOrder">
						<input class="form-control btn btn-warning btn-block"
							type="submit" value="注文に進む">
					</form>
				</div>
			</div>
		</div>
		
			<div class="row">
			<div class="col-xs-offset-5 col-xs-3">
					<a  href="${pageContext.request.contextPath}/showItem/index" >
					ショッピングを続ける
					</a>
			</div>
		</div>
		
	</c:if>
	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>