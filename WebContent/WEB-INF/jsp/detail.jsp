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
<h1> 게시물 상세보기 </h1>

	번호 : ${myData2.id}
	<hr>
	제목 : ${myData2.title}
	<hr>
	내용 : ${myData2.body}
	<hr>
	
	
	<c:if test="${loginedMember.id == myData2.mid }">
		<a href="/JSP_total/article?action=showUpdate&aid=${myData2.id }">수정</a>
		<a href="/JSP_total/article?action=delete&aid=${myData2.id }">삭제</a>
	</c:if>

	<a href="/JSP_total/article?action=list">홈으로</a>
	
<h2> 댓글 </h2>
	<c:choose>
	<c:when test="${replyData == null }">
		댓글이 없습니다.
	</c:when>
	<c:otherwise>
		<c:forEach var="reply" items="${replies }">
			작성자 : ${reply.nickname } <br>
			댓글 내용 : ${reply.body } <br>
			등록 날짜 : ${reply.regDate } <br>
		</c:forEach>
	
	</c:otherwise>
	</c:choose>
	
	<hr>
	
	<form action="/JSP_total/article">
		<input type="text" placeholder="댓글입력" name="body"/> <br>
		<input type="hidden" name="mid" value="${loginedMember.id}"/>
		<input type="hidden" name="action" value="reply"/>
		<input type="hidden" name="aid" value="${myData2.id }"/>
		<input type="submit" value="댓글 등록"/>
	</form>
	
	
</body>
</html>