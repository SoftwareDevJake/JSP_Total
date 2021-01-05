package board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.Article;
import board.article.ArticleDao;
import board.article.Reply;

public class ArticleController2 {
	
	ArticleDao articleDao = new ArticleDao();
	ArrayList<Reply> replies = new ArrayList<>();
	
	public String doAction(HttpServletRequest request, HttpServletResponse response)
	{
		String action = request.getParameter("action");		
		String dest = "";
		
		if(action.equals("list"))
		{
			dest = list(request, response);
		}
		else if(action.equals("insert"))
		{
			dest = insert(request, response);
		}
		else if(action.equals("delete"))
		{
			dest = delete(request, response);
		}
		else if(action.equals("update"))
		{
			dest = update(request, response);
		}
		else if(action.equals("detail"))
		{
			dest = detail(request, response);
		}
		else if(action.equals("showAdd"))
		{
			dest = "WEB-INF/jsp/addForm.jsp";
		}
		else if(action.equals("showUpdate"))
		{
			dest = showUpdate(request, response);
		}
		else if(action.equals("reply"))
		{
			dest = reply(request, response);
		}
//		else if(action.equals("doReply"))
//		{
//			dest = doReply(request, response);
//		}
		
//		else if(action.equals("select"))
//		{
//			dest = "WEB-INF/jsp/selectTest.jsp";
//		}
//		else if(action.equals("doTest"))
//		{
//			String text = request.getParameter("text");
//			String select = request.getParameter("select");
//			String chkbox = request.getParameter("chkbox");
//			
//			System.out.println(text);
//			System.out.println(select);
//			System.out.println(chkbox);
//		}
		
		request.setAttribute("myData", articleDao.getArticles());
		
		return dest;
	}
	
//	private String doReply(HttpServletRequest request, HttpServletResponse response) {
//		String body = request.getParameter("body");
//		String nickname = request.getParameter("nickname");
//		
//		
//		
//		return detail(request, response);
//	}

	private String reply(HttpServletRequest request, HttpServletResponse response)
	{
		String body = request.getParameter("body");
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		articleDao.insertReply(aid, body);
		replies = articleDao.getRepliesByArticleId(aid);
		request.setAttribute("replyData", replies);
		
		return detail(request, response);
	}

	private String showUpdate(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		Article article = articleDao.getArticleById(aid);
		
		request.setAttribute("myData3", article);
		return "WEB-INF/jsp/updateForm.jsp";
	}

	private String detail(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		Article article = articleDao.getArticleById(aid);
		
		ArrayList<Reply> replies = articleDao.getRepliesByArticleId(aid);
		
		request.setAttribute("myData2", article);
		request.setAttribute("replies", replies);
		return "WEB-INF/jsp/detail.jsp";
	}

	private String delete(HttpServletRequest request, HttpServletResponse response) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		articleDao.deleteArticle(aid);
		return list(request, response);
	}

	private String update(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		articleDao.updateArticle(title, body, aid);
		
		return detail(request, response);
	}

	public String list(HttpServletRequest request, HttpServletResponse response)
	{
		ArrayList<Article> articles = articleDao.getArticles();
		request.setAttribute("myData", articles);
		
		return "WEB-INF/jsp/list.jsp";
	}
	
	private String insert(HttpServletRequest request, HttpServletResponse response)
	{
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		int mid = Integer.parseInt(request.getParameter("mid"));
		
		articleDao.insertArticle(title, body, mid);
		
//		response.sendRedirect("/JSP_total/article?action=list");
		return list(request, response);
	}
}
