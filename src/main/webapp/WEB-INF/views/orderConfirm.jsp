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

<title>ご注文確認画面</title>
<link href="../css/bootstrap.css" rel="stylesheet">
<link href="../css/piza.css" rel="stylesheet">

<script>
	function cancel() {
		document.getElementById('form').action = '${pageContext.request.contextPath}/creditCardAuthorization/cancel';
	}
</script>

</head>
<body>

	<!-- table -->
	<div class="row">
		<form:form
			action="${pageContext.request.contextPath}/creditCardAuthorization/"
			modelAttribute="creditCardForm" id="form">
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
										<form:errors path="card_number" cssStyle="color:red"></form:errors>
										<div id="creditCardNumber" style="display: none;">
											カード番号：
											<form:input path="card_number" id="creditNumber"
												maxlength="16" placeholder="ハイフンなし数値のみ" />
											<br>
											<!-- 有効期限 expiration date-->
											<!-- 有効期限（月）のプルダウン -->
											<form:errors path="card_exp_month" cssStyle="color:red"></form:errors>
											<form:label path="card_exp_month">有効期限（月）:</form:label>
											<form:select path="card_exp_month"
												items="${expirationMonthMap}">
												<%-- 											<c:forEach var="card_exp_month" items="${expirationMonthMap}">
											<form:option value="${card_exp_month}"><c:out value="${card_exp_month}"></c:out></form:option></c:forEach> --%>
											</form:select>
											<br>
											<!-- 有効期限（年）のプルダウン -->
											<form:label path="card_exp_year">有効期限（年）:</form:label>
											<form:select path="card_exp_year"
												items="${expirationYearMap}">
												<%-- 											<c:forEach var="card_exp_year" items="${expirationYearMap}">
											<form:option value="${card_exp_year}"><c:out value="${card_exp_year}"></c:out></form:option></c:forEach>
 --%>
											</form:select>
											<br> カード名義人： <input type="text" name="card_name"
												id="creditNumber" placeholder="例.SOHEI NOBE"><br>
											セキュリティコード： <input type="text" name="card_cvv"
												id="creditNumber" placeholder="3桁または4桁 例.000"><br>
											<input type="submit" class="creditCertification"
												value="カード認証"><br>
											<!-- キャンセル処理は管理者権限のみとする -->
		<%-- 									<input type="hidden" name="order_number" value="${order.id}">
											<input type="submit" onclick="cancel()" value="キャンセル" />
		 --%>									<%-- 	<form:form modelAttribute="creditCardCancelForm"
												action="${pageContext.request.contextPath}/creditCardAuthorization/cancel">
												<button type="submit" value="キャンセル"></button>
											</form:form> --%>
										</div>
										<br> <br>
									</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form:form>
		<form:form modelAttribute="CreditCardCancelForm"
			action="${pageContext.request.contextPath}/creditCardAuthorization/cancel">
			<input type="hidden" name="order_number" value="${order.id}">
			<input type="submit" value="キャンセル">
		</form:form> 
	</div>
	<!-- table終わり -->


</body>
</html>