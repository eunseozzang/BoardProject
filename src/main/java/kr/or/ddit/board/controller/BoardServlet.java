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

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.FileVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private static final Logger logger = LoggerFactory.getLogger(BoardServlet.class);
	private BoardServiceI boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardno = Integer.parseInt(request.getParameter("boardno"));
		logger.debug("글번호 : {}",boardno);
		
		BoardVO boardVO = boardService.showBoardContent(boardno);
		System.out.println(boardVO.getBoarddate());
		request.setAttribute("boardVO", boardVO);
		
		List<FileVO> fileList = boardService.showFile(boardno);
		request.setAttribute("fileList", fileList);
		if(fileList.size()>0) {
			System.out.println(fileList.get(0).getFilename());
		}
		
		List<ReplyVO> replyList = boardService.showComment(boardno);
		logger.debug("댓글 개수 : {}",replyList.size());
		
		request.setAttribute("replyList", replyList);
		
		request.getRequestDispatcher("/board/detail.jsp").forward(request, response);
		
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
