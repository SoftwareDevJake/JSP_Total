<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> 회원가입 페이지 </h1>

	<form action="/JSP/article">
		아이디 입력 : <input type="text" name="id" placeholder="아이디"/> <br>
		비밀번호 입력 : <input type="text" name="pw" placeholder="비밀번호"/> <br>
		닉네임 입력 : <input type="text" name="nm" placeholder="닉네임"/>
		<input type="hidden" name="action" value="doInsertMember"/>
		<input type="submit"/ value="가입하기">
	</form>

</body>
</html>