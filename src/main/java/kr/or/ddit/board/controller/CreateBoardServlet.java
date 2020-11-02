package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardTypeVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;

/**
 * Servlet implementation class CreateBoardServlet
 */
@WebServlet("/boardCreate")
public class CreateBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory.getLogger(CreateBoardServlet.class);
	
	private BoardServiceI boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BoardTypeVO> typeList = boardService.showUseBoard();
		List<BoardTypeVO> typeList2 = boardService.showBoard();
		
		request.getSession().setAttribute("typeList", typeList);
		request.getSession().setAttribute("typeList2", typeList2);
		
		request.getRequestDispatcher("board/manage.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		String boardname = request.getParameter("boardname");
		String boarduse = request.getParameter("boarduse");
		int use = 0;
		if(boarduse.equals("usable")) {
			use = 0;
		} else if(boarduse.equals("unusable")) {
			use = 1;
		}
		logger.debug("게시판 이름 : {}",boardname);
		logger.debug("사용여부 : {}",boarduse);
		logger.debug("사용여부1 : {}",use);
		
		BoardTypeVO boardTypeVO = new BoardTypeVO();
		
		boardTypeVO.setBoardname(boardname);
		boardTypeVO.setBoarduse(use);
		
		System.out.println(boardTypeVO.getBoardname());
		
		int createCnt = boardService.createBoard(boardTypeVO);
		
		
		if(createCnt == 1) {
			response.sendRedirect(request.getContextPath() + "/manageBoard");
		}
		else {
			doGet(request, response); // 화면요청이라 그냥 이렇게만 해도 됨 . . . . . . . 왜 ? 
		}
	}

}
