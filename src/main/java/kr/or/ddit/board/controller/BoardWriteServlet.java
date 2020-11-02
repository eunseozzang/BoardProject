package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.FileVO;
import kr.or.ddit.board.model.WriteVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.fileUpload.FileUploadUtil;
import kr.or.ddit.member.model.MemberVO;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/boardWrite")
@MultipartConfig
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardWriteServlet.class);
	
	private BoardServiceI boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("board/write.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String type = request.getParameter("typeno");
		String boardtitle = request.getParameter("boardtitle");
		String boardcontent = request.getParameter("summernote");
		int typeno = Integer.parseInt(type);
		
		//유저 id 가져오기
		HttpSession session = request.getSession(true);
		MemberVO memberVO = (MemberVO) session.getAttribute("USER");
		String userid = memberVO.getUserid();
		
		logger.debug("게시판 : {}, 제목 : {}, 내용 : {}",typeno,boardtitle,boardcontent);
		logger.debug("아이디 : {}",userid);
		
		WriteVO writeVO = new WriteVO();
		writeVO.setBoardtitle(boardtitle);
		writeVO.setBoardcontent(boardcontent);
		writeVO.setTypeno(typeno);
		writeVO.setUserid(userid);
		
		int boardno = boardService.writeBoard(writeVO);
		
		logger.debug("글번호 : {}",boardno);
		
		
		
		for(int i=1;i<6;i++) {
			
			Part file = request.getPart("realfilename" + i);
			logger.debug("file : {}",file.getHeader("Content-Disposition"));
			
			String realfilename = FileUploadUtil.getFilename(file.getHeader("Content-Disposition"));
			logger.debug("realfilename : {}",realfilename);
			
			String filename = UUID.randomUUID().toString(); //어떤 중복X 문자열을 return한다.
			String filePath = "";
			String extension = FileUploadUtil.getExtension(realfilename);
			logger.debug("extension : {}",extension);
			
			if(file.getSize() > 0) {
				filePath = "D:\\board\\" + filename + "." + extension;
				file.write(filePath);
				FileVO fileVO = new FileVO();
				
				fileVO.setBoardno(boardno);
				fileVO.setFilename(realfilename);
				fileVO.setFilepath(filePath);
				
				int insertCnt = boardService.uploadFile(fileVO);
				
				logger.debug("파일 업로드 : {}",insertCnt);
			}
		}
		
		if(boardno>0) {
			response.sendRedirect(request.getContextPath() + "/board?boardno=" + boardno);
		} else {
			doGet(request, response);
		}
		
		
	}
}
