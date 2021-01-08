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
	<hr>
	<a href="/JSP_total/article?action=doLikeCheck&aid=${myData2.id }">좋아요</a> ${myData2.likeCnt }
	
<h2> 댓글 </h2>

	<c:forEach items="${replies}" var="reply" >
		<c:choose>
			<c:when test="${flag == 'u' && rid == reply.id}">
				${loginedMember.nickname } <br>
				<form action="/JSP_total/article">
					<input type="text" name="rbody" value="${reply.body }"/>
					<input type="hidden" name="rid" value="${reply.id }"/>
					<input type="hidden" name="action" value="doUpdateReply"/>
					<input type="hidden" name="aid" value="${myData2.id }"/>
					<input type="submit" value="등록"/>
				</form>
			</c:when>
			<c:otherwise>
				${reply.nickname } <br>
				${reply.body } <br>
				${reply.regDate } 

				<c:if test="${reply.mid == loginedMember.id }">
					<a href="/JSP_total/article?action=showReplyUpdate&aid=${myData2.id }&id=${reply.id }">수정</a>
					<a href="/JSP_total/article?action=doDeleteReply&id=${reply.id }&aid=${myData2.id}">삭제</a>
				</c:if>
				<hr>
				
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<hr>
	${loginedMember.nickname } <br>
	<form action="/JSP_total/article">
		<input type="text" placeholder="댓글을 남겨보세요" name="rbody"/>
		<input type="hidden" name="mid" value="${loginedMember.id}"/>
		<input type="hidden" name="action" value="doInsertReply"/>
		<input type="hidden" name="aid" value="${myData2.id }"/>
		<input type="submit" value="등록"/>
	</form>
	<hr>
	
	
</body>
</html>