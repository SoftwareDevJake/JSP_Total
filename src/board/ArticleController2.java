package board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.article.Article;
import board.article.ArticleDao;
import board.article.Like;
import board.article.Reply;
import board.member.Member;

public class ArticleController2 {
	
	ArticleDao articleDao = new ArticleDao();
	//ArrayList<Reply> replies = new ArrayList<>();
	
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
		else if(action.equals("doDeleteReply"))
		{
			dest = deleteReply(request, response);
		}
		else if(action.equals("doInsertReply"))
		{
			dest = doInsertReply(request, response);
		}
		else if(action.equals("showReplyUpdate"))
		{
			dest = showReplyUpdate(request, response);
		}
		else if(action.equals("doUpdateReply"))
		{
			dest = updateReply(request, response);
		}
		else if(action.equals("doSearch"))
		{
			dest = doSearch(request, response);
		}
		else if(action.equals("doLikeCheck"))
		{
			dest = doLikeCheck(request, response);
		}
		
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
		
		//request.setAttribute("myData", articleDao.getArticles());
		
		return dest;
	}
	
	private String doLikeCheck(HttpServletRequest request, HttpServletResponse response) {
		
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		HttpSession session = request.getSession();
		int mid = ((Member)session.getAttribute("loginedMember")).getId();
		
		Like like = articleDao.getLike(aid, mid);
		
		if(like == null)
		{
			articleDao.insertLike(aid, mid);
		}
		else
		{
			articleDao.deleteLike(aid, mid);
		}
		
		return "redirect: /JSP_total/article?action=detail&aid=" + aid;
	}

	private String doSearch(HttpServletRequest request, HttpServletResponse response) {
		
		int searchDate = Integer.parseInt(request.getParameter("searchDate"));
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		ArrayList<Article> searchedArticles = articleDao.searchArticles(searchType, searchDate, searchKeyword);
		
		request.setAttribute("myData", searchedArticles);
		
		return "WEB-INF/jsp/list.jsp";
	}

	private String updateReply(HttpServletRequest request, HttpServletResponse response) {

		int aid = Integer.parseInt(request.getParameter("aid"));
		int rid = Integer.parseInt(request.getParameter("rid"));
		
		String body = request.getParameter("rbody");
		
		articleDao.updateReply(body, rid);
		
		return "redirect: /JSP_total/article?action=detail&aid=" + aid;
	}

	private String showReplyUpdate(HttpServletRequest request, HttpServletResponse response) {
		
		int aid = Integer.parseInt(request.getParameter("aid"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		return "redirect: /JSP_total/article?action=detail&aid=" + aid + "&flag=u&rid=" + id;
	}

	private String doInsertReply(HttpServletRequest request, HttpServletResponse response) {
		
		int aid = Integer.parseInt(request.getParameter("aid"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		String body = request.getParameter("rbody");
		
		articleDao.insertReply(aid, body, mid);
		
		return "redirect: /JSP_total/article?action=detail&aid=" + aid;
	}
	
	private String deleteReply(HttpServletRequest request, HttpServletResponse response) {
		
		int id = Integer.parseInt(request.getParameter("id"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		articleDao.deleteReplyById(id);
		
		return "redirect: /JSP_total/article?action=detail&id=" + aid;
	}

	private String reply(HttpServletRequest request, HttpServletResponse response)
	{
		String body = request.getParameter("body");
		int aid = Integer.parseInt(request.getParameter("aid"));
		int mid = Integer.parseInt(request.getParameter("mid"));
		
		articleDao.insertReply(aid, body, mid);
		ArrayList<Reply> replies = articleDao.getRepliesByArticleId(aid);
		request.setAttribute("replies", replies);
		
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
		String flag = request.getParameter("flag");
		
		Article article = articleDao.getArticleById(aid);
		ArrayList<Reply> replies = articleDao.getRepliesByArticleId(aid);
		// flag 안나옴 **
		if(flag != null)
		{
			int rid = Integer.parseInt(request.getParameter("rid"));
			request.setAttribute("flag", flag);
			request.setAttribute("rid", rid);
		}
		
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
		Pagination pagination = new Pagination(articles.size());
		
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		pagination.setCurrentPageNo(pageNo);
		pagination.setCurrentPageBlockNo(pageNo);
		
		ArrayList<Article> articlesPerPage = articleDao.getArticlesForPaging(pagination);
		
		request.setAttribute("myData", articlesPerPage);
		request.setAttribute("pagination", pagination);
		
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
