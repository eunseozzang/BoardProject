package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;

/**
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/commentDelete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CommentDeleteServlet.class);
	private BoardServiceI boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int replyno = Integer.parseInt(request.getParameter("replyno"));
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		logger.debug("댓글번호 : {}",replyno);
		
		int delCnt = boardService.delComment(replyno);
		logger.debug("삭제 : {}",delCnt);
		
		response.sendRedirect(request.getContextPath() + "/board?boardno=" + boardno);
		
	
	}

}
