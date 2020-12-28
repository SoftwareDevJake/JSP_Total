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
import board.member.Member;
import board.member.MemberDao;

@WebServlet("/article")
public class Controller2 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		ArticleDao articleDao = new ArticleDao();
		MemberDao memberDao = new MemberDao();
		ArrayList<Article> articles = articleDao.getArticles();
		ArrayList<Member> members = memberDao.getMembers();
		
		String action = request.getParameter("action");
		
		String dest = "/WEB-INF/jsp/list.jsp";
		
		if(action.equals("list"))
		{
			request.setAttribute("myData", articles);
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			Member member = memberDao.getMemberByLoginIdAndLoginPw(id, pw);
			
			request.setAttribute("memberData2", member);
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
//			dest = "WEB-INF/jsp/updateFrom.jsp";
		}
		else if(action.equals("detail"))
		{
			dest = "WEB-INF/jsp/detail.jsp";
			
			int aid = Integer.parseInt(request.getParameter("aid"));
			
			Article article = articleDao.getArticleById(aid);
			
			request.setAttribute("myData2", article);
		}
		else if(action.equals("showAdd"))
		{
			dest = "WEB-INF/jsp/addForm.jsp";
		}
		else if(action.equals("showUpdate"))
		{
			dest = "WEB-INF/jsp/updateForm.jsp";
			
			int aid = Integer.parseInt(request.getParameter("aid"));
			
			Article article = articleDao.getArticleById(aid);
			
			request.setAttribute("myData3", article);
		}
		else if(action.equals("doLogin"))
		{
			String loginId = request.getParameter("loginId");  
			String loginPw = request.getParameter("loginPw");
			
			Member loginedMember = memberDao.getMemberByLoginIdAndLoginPw(loginId, loginPw);
			
			if(loginedMember != null)
			{
				dest = "WEB-INF/jsp/showMember.jsp";
			}
			else
			{
				dest = "WEB-INF/jsp/loginFailed.jsp";
			}
			
			request.setAttribute("memberData", loginedMember);
		}
		else if(action.equals("doInsertMember"))
		{
			dest = "WEB-INF/jsp/signupForm.jsp";
			
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String nm = request.getParameter("nm");
			
			memberDao.insertMember(id, pw, nm);
		}
		else if(action.equals("showMember"))
		{
			dest = "WEB-INF/jsp/signupForm.jsp";
		}
			
		request.setAttribute("myData", articleDao.getArticles());
		
		// 3. 요청하기
		RequestDispatcher rd = request.getRequestDispatcher(dest);
		rd.forward(request, response);
	}
}
