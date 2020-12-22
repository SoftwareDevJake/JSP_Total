<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> EL/JSTL 연습</h1>

<!--  변수 선언 -->
<%
	int num = 21;
%>
<c:set var="num2" value="11"/>

<%
	out.println("jsp : " + num);
%>
<br>
<c:out value="jstl : ${num2}"/>

<!-- 조건문 -->
<br>
<%
	if(num % 2 == 0)
	{
		out.println("even");
	}
	else
	{
		out.println("odd");
	}
%>

<c:if test="${num2 % 2 == 0}">
	even
</c:if>
<c:if test="${num2 % 2 == 1}">
	odd
</c:if>
<c:choose>
	<c:when test="${num2 % 2 == 0}">
		even
	</c:when>
	<c:otherwise>
		odd
	</c:otherwise>
</c:choose>

<!-- 반복문 -->
<%
	for(int i = 1; i <= 10; i++)
	{
		out.println(i + " ");
	}
%>
<br>
<c:forEach var="i" begin="1" end="10" step="1">
	${i}
</c:forEach>

</body>
</html>