package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.Article;
import board.article.ArticleDao;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		ArticleDao articleDao = new ArticleDao();
		ArrayList<Article> articles = articleDao.getArticles();
		
		String action = request.getParameter("action");
		
		request.setAttribute("myData", articles);
		
		String dest = "";
		
		if(action.equals("list1.jsp"))
		{
			dest = "/list1.jsp";
		}
		else
		{
			dest = "/list2.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);
		
		// jsp에 articles 넘기기
		// 1. request 객체에 데이터 저장
		request.setAttribute("myData", articles);
		
		// 2. 위에서 저장한 request 객체를 이용해 새로운 jsp 요청 - 목적지 jsp 필요
		String dest = "";
		if(action.equals("list1"))
		{
			dest = "/test.jsp";
		}
		else
		{
			dest = "/test.jsp";
		}
		
		
		//3. 요청하기
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);
		
	}

}
