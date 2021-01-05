//package board;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import board.member.Member;
//import board.member.MemberDao;
//
//@WebServlet("/member")
//public class memberController extends HttpServlet {
//	
//	MemberDao memberDao = new MemberDao();
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		String action = request.getParameter("action");		
//		String dest = "";
//		
//		if(action.equals("showLogin"))
//		{
//			dest = "WEB-INF/jsp/loginForm.jsp";
//		}
//		else if(action.equals("doLogin"))
//		{
//			dest = doLogin(request, response);
//		}
//		else if(action.equals("doInsertMember"))
//		{
//			dest = doInsertMember(request, response);
//		}
//		else if(action.equals("showMember"))
//		{
//			dest = "WEB-INF/jsp/signupForm.jsp";
//		}
//		else if(action.equals("logout"))
//		{
//			dest = logout(request, response);
//		}
//	}
//
//	private String doInsertMember(HttpServletRequest request, HttpServletResponse response) {
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
//		String nm = request.getParameter("nm");
//		
//		memberDao.insertMember(id, pw, nm);
//		
//		return "WEB-INF/jsp/list.jsp";
//	}
//
//	private String doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String loginId = request.getParameter("loginId");  
//		String loginPw = request.getParameter("loginPw");
//		
//		Member loginedMember = memberDao.getMemberByLoginIdAndLoginPw(loginId, loginPw);
//		if(loginedMember != null)
//		{
//			//session 저장소 저장하는 법
//			HttpSession session = request.getSession();
//			session.setAttribute("loginedMember", loginedMember);
//			
//			return "redirect: /JSP_total/article?action=list";
//		}
//		else
//		{
//			return "WEB-INF/jsp/loginFailed.jsp";
//		}
//	}
//
//	private String logout(HttpServletRequest request, HttpServletResponse response) {
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
//		
//		Member member = memberDao.getMemberByLoginIdAndLoginPw(id, pw);
//		
//		member = null;
//		
//		request.setAttribute("memberData", member);
//		 
//		return "WEB-INF/jsp/list.jsp";
//	}
//}
