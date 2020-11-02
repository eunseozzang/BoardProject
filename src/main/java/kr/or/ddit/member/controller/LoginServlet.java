package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardTypeVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceI;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
	
	private MemberServiceI memberService;
	private BoardServiceI boardService;
	
	@Override
		public void init() throws ServletException {
			//service 객체 초기화
			memberService = new MemberService();
			boardService = new BoardService();
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		String userId = request.getParameter("userID");
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		logger.debug("아이디 : {}, 비밀번호 : {}",userId,password);
		
		MemberVO memberVO = memberService.getMember(userId);
		
		List<BoardTypeVO> typeList = boardService.showUseBoard();
		List<BoardTypeVO> typeList2 = boardService.showBoard();
		 
		
		if(memberVO == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		} else if(memberVO.getUserpass().equals(password)) {
			request.getSession().setAttribute("USER", memberVO);
			request.getSession().setAttribute("typeList", typeList);
			request.getSession().setAttribute("typeList2", typeList2);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		// 쿠키정보
		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {
			logger.debug("name : {}, value : {}", cookie.getName(), cookie.getValue());
		}

		Cookie cookie = new Cookie("SERVERCOOKIE", "COOKIEVALUE");
		cookie.setMaxAge(60 * 60 * 24);

		response.addCookie(cookie);
	}

}
