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
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/boardDelete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDeleteServlet.class);
	
	private BoardServiceI boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		logger.debug("삭제할 게시글 번호 : {}",boardno);
		
		int delCnt = boardService.deleteBoard(boardno);
		
		int typeno = boardService.gettypeno(boardno);
		logger.debug("게시판 : {}",typeno);
		
		System.out.println(delCnt);
		
		boardService.fileDeleteAll(boardno);
		if(delCnt > 0) {
			response.sendRedirect(request.getContextPath() + "/boardList?typeno=" + typeno + "&page=1");
		} else {
			response.sendRedirect(request.getContextPath() + "/board?boardno=" + boardno);
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
