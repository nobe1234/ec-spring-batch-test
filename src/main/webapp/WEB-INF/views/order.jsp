<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<script src="/js/creditCard.js"></script>
<script src="/js/order.js"></script>

<title>ピザ屋のネット注文</title>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/piza.css" rel="stylesheet">
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


						<a href="${pageContext.request.contextPath}/viewCartContent/view"
							class="navbar-link">ショッピングカート</a>&nbsp;&nbsp;
						<!-- 						<a href="order_history.html" class="navbar-link">注文履歴</a>&nbsp;&nbsp; -->
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


		<c:if test="${order == null}">
			カートに商品がありません<br>
		</c:if>
		<br>

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
									<div class="text-center">商品名</div>
								</th>
								<th>
									<div class="text-center">サイズ、価格(税抜)、数量</div>
								</th>
								<th>
									<div class="text-center">トッピング、価格(税抜)</div>
								</th>
								<th>
									<div class="text-center">小計</div>
								</th>
								<th></th>
							</tr>

							<!-- 確認用 -->
							<c:forEach var="orderItem" items="${order.orderList}">
								<tr>
									<td>
										<div class="center">
											<img src="<c:out value="${orderItem.item.imagePath}"/>"
												class="img-responsive img-rounded" width="100" height="300"><br>
											<c:out value="${orderItem.item.name}" />
										</div>
									</td>
									<td><span class="price">&nbsp;<c:out
												value="${orderItem.size}" /></span> <c:if
											test="${orderItem.stringSize == 'M'}">
											&nbsp;&nbsp;<fmt:formatNumber
												value="${orderItem.item.priceM}" pattern="###,###" />円
										</c:if> <c:if test="${orderItem.stringSize == 'L'}">
											&nbsp;&nbsp;<fmt:formatNumber
												value="${orderItem.item.priceL}" pattern="###,###" />円
										</c:if> &nbsp;&nbsp;<c:out value="${orderItem.quantity}" />個</td>
									<td>
										<ul>
											<c:if test="${orderItem.orderToppingList == null}">
									未選択
									</c:if>
											<c:forEach var="topping"
												items="${orderItem.orderToppingList}">
												<li><c:out value="${topping.topping.name}" /> <c:if
														test="${orderItem.stringSize == 'M'}">
														<fmt:formatNumber value="${topping.topping.priceM}"
															pattern="###,###" />円
											</c:if> <c:if test="${orderItem.stringSize == 'L'}">
														<fmt:formatNumber value="${topping.topping.priceL}"
															pattern="###,###" />円
											</c:if></li>
											</c:forEach>
										</ul>
									</td>
									<td>
										<div class="text-center">
											<fmt:formatNumber value="${orderItem.subTotal}"
												pattern="###,###" />
											円
										</div>
									</td>
									<td>
										<div class="text-center">
											<form:form
												action="${pageContext.request.contextPath}/delete/delete"
												method="post">

												<input type="hidden" name="orderItemId"
													value="${orderItem.id}">
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
						<span id="total-price">消費税：<fmt:formatNumber
								value="${order.tax}" pattern="###,###" />円
						</span><br> <span id="total-price">ご注文金額合計：<fmt:formatNumber
								value="${order.calcTotalPrice + order.tax}" pattern="###,###" />円(税込)
						</span>
					</div>
				</div>
			</div>
		</c:if>

		<!-- table -->
		<form:form action="${pageContext.request.contextPath}/order/confirm"
			modelAttribute="orderForm" onsubmit="return checkNijyuSubmit();">
			<div class="row">
				<div
					class="table-responsive col-lg-offset-3 col-lg-6 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
					<h3 class="text-center">お届け先情報</h3>
					<table class="table table-striped">
						<tbody>
							<tr>
								<td>
									<div class="text-center">
										<label for="destinationName">お名前</label>
									</div>
								</td>
								<td><form:errors path="destinationName"
										cssStyle="color:red" element="div" /> <form:input
										path="destinationName" id="destinationName"
										placeholder="例）楽楽太郎" /></td>
							</tr>
							<tr>
								<td>
									<div class="text-center">
										<label for="destinationEmail">メールアドレス</label>
									</div>
								</td>
								<td><form:errors path="destinationEmail"
										cssStyle="color:red" element="div" /> <form:input
										path="destinationEmail" id="destinationEmail"
										placeholder="例）rakuraku@mail.com" /></td>
							</tr>
							<tr>
								<td>
									<div class="text-center">
										<label for="destinationZipcode">郵便番号</label>
									</div>
								</td>
								<td><form:input path="destinationZipcode" size="10"
										maxlength="7"
										onKeyUp="AjaxZip3.zip2addr(this,'','destinationAddress','destinationAddress');"
										placeholder="例）1600022" /> &nbsp;&nbsp;&nbsp;<a
									href="https://www.post.japanpost.jp/zipcode/index.html"
									target="_blank">郵便番号検索</a>（外部サイトが開きます）</td>
							</tr>
							<tr>
								<td>
									<div class="text-center">
										<label for="destinationAddress">住所</label>
									</div>
								</td>
								<td><form:errors path="destinationAddress"
										cssStyle="color:red" element="div" /> <form:input
										path="destinationAddress" size="60" placeholder="例）東京都新宿区4-3" />
								</td>
							</tr>
							<tr>
								<td>
									<div class="text-center">
										<label for="destinationTel">電話番号</label>
									</div>
								</td>
								<td><form:errors path="destinationTel" cssStyle="color:red"
										element="div" /> <form:input path="destinationTel"
										id="destinationTel" placeholder="例）090-1111-2222" /></td>
							</tr>
							<tr>
								<td>
									<div class="text-center">
										<label for="deliveryDate">配達日時</label>
									</div>
								</td>
								<td>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-5">
												<form:errors path="deliveryDate" cssStyle="color:red"
													element="div" />
												<input type="date" name="deliveryDate" id="deliveryDate" /><br>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-12">
												<label class="radio-inline"> <form:radiobutton
														path="deliveryTime" value="10:00" checked="checked" />10時
												</label> <label class="radio-inline"> <form:radiobutton
														path="deliveryTime" value="11:00" />11時
												</label> <label class="radio-inline"> <form:radiobutton
														path="deliveryTime" value="12:00" />12時
												</label><br> <label class="radio-inline"> <form:radiobutton
														path="deliveryTime" value="13:00" />13時
												</label> <label class="radio-inline"> <form:radiobutton
														path="deliveryTime" value="14:00" />14時
												</label> <label class="radio-inline"> <form:radiobutton
														path="deliveryTime" value="15:00" />15時
												</label><br> <label class="radio-inline"> <form:radiobutton
														path="deliveryTime" value="16:00" />16時
												</label> <label class="radio-inline"> <form:radiobutton
														path="deliveryTime" value="17:00" />17時
												</label> <label class="radio-inline"> <form:radiobutton
														path="deliveryTime" value="18:00" />18時
												</label> <br>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<!-- table -->
<%-- 			<div class="row">
				<div
					class="table-responsive col-lg-offset-3 col-lg-6 col-md-offset-1 col-md-10 col-sm-10 col-xs-12">
					<h3 class="text-center">お支払い方法</h3>
					<table class="table table-striped">
						<tbody>
							<tr>
								<td>
									<div class="text-center">
										<label for="cashOnDelivery">代金引換</label>
									</div>
								</td>
								<td>
									<div class="row">
										<div class="col-sm-12">
											<label class="radio-inline"> <form:radiobutton
													path="paymentMethod" value="1" id="cashOnDelivery"
													checked="checked" /> 代金引換
											</label>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>
									<div class="text-center">
										<label for="creditCard">クレジットカード決済</label>
									</div>
								</td>
								<td align="center">
									<div class="row">
										<div class="col-sm-12">
											<label class="radio-inline"> <form:radiobutton
													path="paymentMethod" value="2" id="creditCard" /> クレジットカード
											</label>
											<br> <br>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- table終わり -->  --%>
			<div class="row">
				<div class="col-xs-offset-4 col-xs-4">
					<div class="form-group">
						<p style="color: gray; display: none;" id="nowProcessing">
							画面遷移に時間がかかる場合がございます。 <br>そのままでお待ちください。
						</p>
						<input type="submit" value="この内容で注文する" id="btnSubmit">
					</div>
				</div>
			</div>
		</form:form>
	</div>
	<!-- end container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>