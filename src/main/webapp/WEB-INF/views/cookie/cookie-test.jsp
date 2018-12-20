<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@page import="jp.co.sample.ecommerce_a.BrowsingHistoryWithCookie" %>
<%
	//Cookieから"test_cookie_name"というKeyで登録された値(文字列)を取り出す
	String value = BrowsingHistoryWithCookie.getCookie(request, "test_cookie_name");

	//valueがnullの場合のみCookieをセットする(期限は5分)
	if (value == null) {
		BrowsingHistoryWithCookie.setCookie(request, response, "/", "test_cookie_name", "test_cookie_value",
				5 * 60);
	}
	System.out.println(value);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cookie test</title>
</head>
<body>

	取得した値="<%=value%>"
	<br>



</body>
</html>