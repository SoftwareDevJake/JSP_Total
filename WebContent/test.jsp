<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.ArrayList" %>
 <%@ page import="board.article.Article" %>
 <%@ page import="board.article.ArticleDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1> 자기소개 </h1>
	
	<%
	String name = "Jake";
	int age = 23;
	%>
	
	안녕하세요 저는 <%= age %>살 <%= name %> 입니다.
	
	<h1> 구구단 </h1>
	<div>
	<%
	for(int i = 2; i < 10; i++)
	{
		for(int j = 1; j < 10; j++)
		{%>
			<%= i %> X <%= j %> = <%= i * j %>
		<%}%>
		<br>
	<%}%>
	</div>	
	
	<h1> ArrayList import </h1>
	
	<%
	ArrayList<Integer> numList = new ArrayList<>();
	
	for(int i = 0; i < 10; i++)
	{
		numList.add(i+1);
	}
	%>
	
	<% for(int i = 0; i < numList.size(); i++) { %>
		<div>
			<%=numList.get(i)%>
		</div>
		<%}%>

	<h1> DB article list </h1>
	<%	
	ArrayList<Article> articles = (ArrayList<Article>)request.getAttribute("myData");
	
	%>

	<%for(int i = 0; i < articles.size(); i++) { %>
	<div>
	번호 : <%= articles.get(i).getId() %> <br>
	제목 : <%= articles.get(i).getTitle() %><br>
	내용 : <%= articles.get(i).getBody() %><br>
	작성자 : <%= articles.get(i).getNickname() %><br>
	등록날짜 : <%= articles.get(i).getRegDate() %><br>
	조회수 : <%= articles.get(i).getHit() %><br>
	좋아요 : <%= articles.get(i).getLikeCnt() %><br>
	</div>
	<br>
	<%} %>

	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회수</td>
			<td>좋아요</td>
		</tr>
		<%for(int i = 0; i < articles.size(); i++) { %>
		<tr>
			<td><%= articles.get(i).getId() %></td>
			<td><%= articles.get(i).getTitle() %></td>
			<td><%= articles.get(i).getBody() %></td>
			<td><%= articles.get(i).getNickname() %></td>
			<td><%= articles.get(i).getRegDate() %></td>
			<td><%= articles.get(i).getHit() %></td>
			<td><%= articles.get(i).getLikeCnt() %></td>
		</tr>
		<%} %>
	</table>


</body>
</html>