package kr.or.ddit.board.controller;

import java.io.IOException;
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
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.PageVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/boardList")
public class BoardListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   private static final Logger logger = LoggerFactory.getLogger(CreateBoardServlet.class);
   
   private BoardServiceI boardService;
   
   @Override
      public void init() throws ServletException {
      boardService = new BoardService();
   }
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      // 첫번째 페이지 저장
      String page_str = request.getParameter("page");
      int page = page_str == null ? 1 : Integer.parseInt(page_str);
      request.setAttribute("page", page);
      System.out.println(page);

      // 페이지 크기 지정

      String pageSize_str = request.getParameter("pageSize");
      int pageSize = pageSize_str == null ? 10 : Integer.parseInt(pageSize_str);
      request.setAttribute("pageSize", pageSize);
      System.out.println(pageSize);
      
      // 어떤 게시판인지 
      String type = request.getParameter("typeno");
      int typeno = Integer.parseInt(type);
      request.setAttribute("typeno", typeno);
      
      String boardname = boardService.boardName(typeno);
      request.setAttribute("boardname", boardname);
      
      // 페이지VO에 몇페이지, 페이지당 글 개수, 게시판 이름을 저장한다.
      PageVO pageVO = new PageVO(page,pageSize,typeno);
//      List<BoardVO> baordList = new BoardVO();
//      baordList = boardService.selectBoard(typeno);
//      List<BoardVO> boardList = boardService.selectBoard(typeno);
//      
//      logger.debug("글 개수 : {}",boardList.size());
      
//      request.setAttribute("boardList", boardList);
      
      
      // map에 페이지당 글을 저장함..
      Map<String, Object> map = boardService.selectBoardPageList(pageVO);
      
      request.setAttribute("boardList", map.get("boardList"));
      request.setAttribute("pages", map.get("pages"));
      
      request.getRequestDispatcher("board/list.jsp").forward(request, response);
      
      
   }
   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
   }

}