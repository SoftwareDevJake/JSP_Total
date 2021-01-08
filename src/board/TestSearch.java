package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.Article;
import board.article.ArticleDao;

@WebServlet("/TestSearch")
public class TestSearch extends HttpServlet {
	
	ArticleDao articleDao = new ArticleDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String dest = "";
		
		if(action.equals("doSearch"))
		{
			dest = doSearch(request, response);
		}
		
	}

	
private String doSearch(HttpServletRequest request, HttpServletResponse response) {
		
		String searchType = request.getParameter("searchType");
		String searchDate = request.getParameter("searchDate");
		ArrayList<Article> articles = new ArrayList<>();
//============================searchType================================		
		if(searchType.equals("title"))
		{
			int searchFlag = 1;
			String searchKeyword = request.getParameter("searchKeyword");
			
			articles = articleDao.getSearchedArticles(searchFlag, searchKeyword);
		}
//============================searchDate================================
		if(searchDate.equals("all"))
		{
			articles = articleDao.getArticles();
		}
		else if(searchDate.equals("day"))
		{
			articles = articleDao.day();
		}
		else if(searchDate.equals("week"))
		{
			articles = articleDao.week();
		}
		else if(searchDate.equals("month"))
		{
			articles = articleDao.month();
		}
		else if(searchDate.equals("half_year"))
		{
			articles = articleDao.half_year();
		}
		else if(searchDate.equals("year"))
		{
			articles = articleDao.year();
		}
		
		request.setAttribute("myData", articles);
		
		return "WEB-INF/jsp/list.jsp";
	}
}
