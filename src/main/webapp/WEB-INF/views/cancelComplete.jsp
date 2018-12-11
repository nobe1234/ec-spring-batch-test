<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	下記の商品のキャンセルを承りました。 お問い合わせ番号：
	<c:out value="${order.id}" />
	お客様番号：
	<c:out value="${order.userId}" />
	お名前：
	<c:out value="${order.userId}" />
	&nbsp;様

	<c:forEach items="${orderList}" var="orders">
注文番号：<c:out value="${orders.id}" />
注文番号：<c:out value="${orders.id}" />

		<%-- <c:forEach items="${item}" var /> --%>
		<%-- 商品名<c:out ${item.name} /> --%>
	</c:forEach>


	<a href="${pageContext.request.contextPath}/showItem/index">トップページへ</a>



	合計金額：
	<c:out value="${order.totalPrice}" />
	：
	<c:out value="${order.userId}" />


</body>
</html>