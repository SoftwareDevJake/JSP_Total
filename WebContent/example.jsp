<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> 할인대상 연습문제</h1>
<c:set var = "age" value = "10"/>

당신의 나이는 ${age}살 입니다.
<br>

<c:if test="${age <= 19}">
	할인대상
</c:if>
<c:if test="${age >= 60}">
	할인대상
</c:if>
<c:if test="${age > 19 && age < 60}">
	할인대상X
</c:if>

<c:choose>
	<c:when test="${age <= 19 }">
	할인대상
	</c:when>
	
	<c:when test="${age >= 60 }">
	할인대상
	</c:when>
	
	<c:otherwise>
	할인대상X
	</c:otherwise>
</c:choose>

<h1> 1~100 짝수</h1>
<c:forEach var="i" begin="1" end="100" step="1">
	<c:choose>
		<c:when test="${i % 2 == 0}">
			${i}
			<br>
		</c:when>
	</c:choose>

</c:forEach>

<h1> 응용 구구단 </h1>

<c:set var="n" value="4"/>
<c:set var="m" value="19"/>
<c:set var="limit" value="11"/>

<c:forEach var="i" begin="${n }" end="${m }" step="1">
	<c:forEach var="j" begin="2" end="${limit }" step="2">
		<c:choose>
			<c:when test="${i % 2 == 1 }">
				${i } x ${j } = ${i*j }
				<br>
			</c:when>
		
		</c:choose>
	</c:forEach>

</c:forEach>

<h1> 삼각형 그리기</h1>

<c:set var="y" value="5"/>

<c:forEach var="i" begin="1" end="${y }" step="1">
	<c:forEach var="j" begin="1" end="${i }" step="1">
		*
	</c:forEach>
<br>
</c:forEach>

<%
int y = 7;
for(int i = 1; i <= y; i++)
{
	for(int j = 1; j <= i; j++)
	{
		out.println("*");		
	}
	%>
	<br>
	<%
}
%>

<%
	ArrayList<String> strList = new ArrayList<String>();

	strList.add("안녕");
	strList.add("반가워");
	strList.add("잘가");
	
	
	for(String str : strList) {
		out.println(str);
	}
%>

<c:forEach var="str" items="${strList }">
	${str }
</c:forEach>

</body>
</html>