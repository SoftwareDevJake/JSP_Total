//package board;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import board.article.Article;
//import board.article.ArticleDao;
//import board.member.Member;
//import board.member.MemberDao;
//
//@WebServlet("/article")
//public class ArticleController extends HttpServlet {
//	ArticleDao articleDao = new ArticleDao();
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String action = request.getParameter("action");		
//		String dest = "";
//		
//		if(action.equals("list"))
//		{
//			dest = list(request, response);
//		}
//		else if(action.equals("insert"))
//		{
//			dest = insert(request, response);
//		}
//		else if(action.equals("delete"))
//		{
//			dest = delete(request, response);
//		}
//		else if(action.equals("update"))
//		{
//			dest = update(request, response);
//		}
//		else if(action.equals("detail"))
//		{
//			dest = detail(request, response);
//		}
//		else if(action.equals("showAdd"))
//		{
//			dest = "WEB-INF/jsp/addForm.jsp";
//		}
//		else if(action.equals("showUpdate"))
//		{
//			dest = showUpdate(request, response);
//		}
//		
//		request.setAttribute("myData", articleDao.getArticles());
//		
//		// 3. 요청하기
//		
//		// forwarding
//		RequestDispatcher rd = request.getRequestDispatcher(dest);
//		rd.forward(request, response);
//		
//		// redirecting
////		response.sendRedirect("/JSP_total/article?action=list");
//	}
//	
//	
//
//	private String showUpdate(HttpServletRequest request, HttpServletResponse response) {
//		int aid = Integer.parseInt(request.getParameter("aid"));
//		
//		Article article = articleDao.getArticleById(aid);
//		
//		request.setAttribute("myData3", article);
//		return "WEB-INF/jsp/updateForm.jsp";
//	}
//
//	private String detail(HttpServletRequest request, HttpServletResponse response) {
//		int aid = Integer.parseInt(request.getParameter("aid"));
//		
//		Article article = articleDao.getArticleById(aid);
//		
//		request.setAttribute("myData2", article);
//		return "WEB-INF/jsp/detail.jsp";
//	}
//
//	private String delete(HttpServletRequest request, HttpServletResponse response) {
//		int aid = Integer.parseInt(request.getParameter("aid"));
//		
//		articleDao.deleteArticle(aid);
//		return list(request, response);
//	}
//
//	private String update(HttpServletRequest request, HttpServletResponse response) {
//		String title = request.getParameter("title");
//		String body = request.getParameter("body");
//		int aid = Integer.parseInt(request.getParameter("aid"));
//		
//		articleDao.updateArticle(title, body, aid);
//		
//		return detail(request, response);
//	}
//
//	private String list(HttpServletRequest request, HttpServletResponse response)
//	{
//		ArrayList<Article> articles = articleDao.getArticles();
//		request.setAttribute("myData", articles);
//		
//		return "WEB-INF/jsp/list.jsp";
//	}
//	
//	private String insert(HttpServletRequest request, HttpServletResponse response) throws IOException
//	{
//		String title = request.getParameter("title");
//		String body = request.getParameter("body");
//		int mid = Integer.parseInt(request.getParameter("mid"));
//		
//		articleDao.insertArticle(title, body, mid);
//		
////		response.sendRedirect("/JSP_total/article?action=list");
//		return list(request, response);
//	}
//}
