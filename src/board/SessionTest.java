package board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SessionTest")
public class SessionTest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String flag = request.getParameter("flag");
		HttpSession session = request.getSession();
		
		if(flag != null && flag.equals("a"))
		{
			String msg = (String)request.getAttribute("test");
			System.out.println(msg);
		}
		else if(flag != null && flag.equals("b"))
		{
			String msg = (String)session.getAttribute("test");
			System.out.println(msg);
		}
		
		request.setAttribute("test", "test message1");
		session.setAttribute("test", "test message2");
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/etc/test.jsp");
		rd.forward(request, response);
		
	}

}
