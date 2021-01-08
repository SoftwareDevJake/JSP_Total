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
			<form action="/JSP_total/member">
				<input type="hidden" name="action" value="logout"/>
				<input type="hidden" name="loginId" value="${loginedMember.loginId}"/>
				<input type="hidden" name="loginPw" value="${loginedMember.loginPw}"/>
				<input type="submit" value="로그아웃"/>
			</form>
		</c:when>
		<c:otherwise>
			<a href="/JSP_total/member?action=showLogin">로그인하기</a>
			<a href="/JSP_total/member?action=showMember">회원가입하기</a>
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
						<td><a href="/JSP_total/member?action=showLogin">${article.title}_${article.id}</a></td>
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
	
	<hr>
	<c:if test="${pagenation.currentPageBlockNo != 1 }">
		<a href="/JSP_total/article?action=list&pageNo=${pagination.startPageNoInCurrentBlock-1}">이전</a>
	</c:if>
	<c:forEach begin="${pagination.startPageNoInCurrentBlock }" end="${pagination.endPageNoInCurrentBlock }" var="i">
		<a href="/JSP_total/article?action=list&pageNo=${i}">${i}</a> 
	</c:forEach>
	<c:if test="${pagenation.currentPageBlockNo != lastPageNo }">
	<a href="/JSP_total/article?action=list&pageNo=${pagination.endPageNoInCurrentBlock+1}">다음</a>
	</c:if>
	<hr>
	
	검색
	
	<form action="/JSP_total/article">
		<select name="searchDate">
			<option value="1">전체기간</option>
			<option value="2">1일</option>
			<option value="3">1주</option>
			<option value="4">1개월</option>
			<option value="5">6개월</option>
			<option value="6">1년</option>
		</select>
		<select name="searchType">
			<option value="title">제목</option>
			<option value="body">내용</option>
			<option value="title_body">제목+내용</option>
			<option value="nickname">작성자</option>
			<option value="rbody">댓글내용</option>
			<option value="rnickname">댓글작성자</option>
		</select>
		<input type="text" name="searchKeyword" placeholder="검색"/>
		<input type="hidden" name="action" value="doSearch"/>
		<input type="submit" value="검색"/>
	</form>
	


</body>
</html>