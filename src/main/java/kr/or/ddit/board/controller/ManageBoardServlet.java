package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet implementation class ManageBoardServlet
 */
@WebServlet("/manageBoard")
public class ManageBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardServiceI boardService;
       
	private static final Logger logger = LoggerFactory.getLogger(ManageBoardServlet.class);
	
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
		
		String name = request.getParameter("typeno");
		String boarduse = request.getParameter("boarduse");
		
		logger.debug("게시판번호 : {}, 수정여부 : {}",name,boarduse);
		
		int typeno = Integer.parseInt(name);
		logger.debug("게시판번호 : {}",typeno);
		
		int use = 0;
		if(boarduse.equals("usable")) {
			use = 0;
		} else if(boarduse.equals("unusable")) {
			use = 1;
		}
		
		logger.debug("수정 : {}",use);
		
		Map<String, Integer> maps = new HashMap<String, Integer>();
		
		maps.put("typeno", typeno);
		maps.put("use", use);
		
		int updateCnt = boardService.updateBoard(maps);
		
		if(updateCnt > 0) {
			response.sendRedirect(request.getContextPath() + "/manageBoard");
		} else {
			doGet(request, response);
		}
		
	}

}
