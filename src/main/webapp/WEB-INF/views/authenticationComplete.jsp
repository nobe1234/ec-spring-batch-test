<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

認証<c:out value="${message}"></c:out>！
 <a href="${pageContext.request.contextPath}/showItem/index" >トップページへ</a>
</body>
</html>