<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="board.article.Article" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${loginedMember != null }">
			환영합니다 ${loginedMember.nickname  }님!
			<form action="/JSP_total/article">
				<input type="hidden" name="action" value="logout"/>
				<input type="hidden" name="loginId" value="${loginedMember.loginId}"/>
				<input type="hidden" name="loginPw" value="${loginedMember.loginPw}"/>
				<input type="submit" value="로그아웃"/>
			</form>
		</c:when>
		<c:otherwise>
			<a href="/JSP_total/article?action=showLogin">로그인하기</a>
			<a href="/JSP_total/article?action=showMember">회원가입하기</a>
		</c:otherwise>
	</c:choose>
	
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
			<td>좋아요</td>
		</tr>
		<c:forEach var="article" items="${myData}" >
			<tr>
				<td>${article.id}</td>
				<c:choose>
					<c:when test="${loginedMember == null }">
						<td><a href="/JSP_total/article?action=showLogin">${article.title}_${article.id}</a></td>
					</c:when>
					<c:otherwise>
						<td><a href="/JSP_total/article?action=detail&aid=${article.id}">${article.title}_${article.id}</a></td>	
					</c:otherwise>
				</c:choose>
				<td>${article.nickname}</td>
				<td>${article.regDate}</td>
				<td>${article.hit}</td>
				<td>${article.likeCnt}</td>
			</tr>
		</c:forEach>
	</table>
	
	<c:choose>
		<c:when test="${loginedMember == null }">
		</c:when>
		<c:otherwise>
			<a href="/JSP_total/article?action=showAdd">글쓰기</a>	
		</c:otherwise>
	</c:choose>
	

	<!-- 
	<c:forEach var="i" begin="1" end="5">
		<div>안녕</div>
	</c:forEach>
	 -->
</body>
</html>