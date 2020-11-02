package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.member.model.MemberVO;

@WebServlet("/commentWrite")
public class CommentWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentWriteServlet.class);
       
	private BoardServiceI boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		String replycontent = request.getParameter("boardcomment");
		
		HttpSession session = request.getSession(true);
		MemberVO memberVO = (MemberVO) session.getAttribute("USER");
		String userid = memberVO.getUserid();
		
		logger.debug("글번호 : {}, 댓글내용 : {}, 아이디 : {}",boardno,replycontent,userid);
		
		ReplyVO replyVO = new ReplyVO(replycontent, userid, boardno);
		
		int insertCnt = boardService.writeComment(replyVO);
		
		logger.debug("등록 : {}",insertCnt);
		
		if(insertCnt == 1) {
			response.sendRedirect(request.getContextPath() + "/board?boardno=" + boardno);
		} else {
			doGet(request, response);
		}
	}

}
