package board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.Article;
import board.article.ArticleDao;

@WebServlet("/article")
public class Controller2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		ArticleDao articleDao = new ArticleDao();
		ArrayList<Article> articles = articleDao.getArticles();
		
		String action = request.getParameter("action");
		
		String dest = "";
		
		if(action.equals("list"))
		{
			request.setAttribute("myData", articles);
			
			dest = "/list.jsp";
		}
		else if(action.equals("insert"))
		{
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int mid = Integer.parseInt(request.getParameter("mid"));
			
			articleDao.insertArticle(title, body, mid);
			
		}
		else if(action.equals("delete"))
		{
			int aid = Integer.parseInt(request.getParameter("aid"));
			
			articleDao.deleteArticle(aid);
			
		}
		else if(action.equals("update"))
		{
			String title = request.getParameter("title");
			String body = request.getParameter("body");
			int aid = Integer.parseInt(request.getParameter("aid"));
			
			articleDao.updateArticle(title, body, aid);
		}
		else
		{
			dest = "/#";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);
	}
}
