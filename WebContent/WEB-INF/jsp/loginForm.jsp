<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board.member.Member" %>
<%@ page import="board.member.MemberDao" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> 로그인 페이지 </h1>

	<form action="/JSP_total/member">
		<input type="text" placeholder="아이디" name="loginId"/> <br>
		<input type="password" placeholder="비밀번호" name="loginPw"/> <br>
		<input type="hidden" name="action" value="doLogin"/>
		<input type="submit" value="로그인"/>
	</form>

</body>
</html>