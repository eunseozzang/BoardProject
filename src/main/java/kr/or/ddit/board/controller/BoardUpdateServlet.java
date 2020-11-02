package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.FileVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;
import kr.or.ddit.fileUpload.FileUploadUtil;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/boardUpdate")
@MultipartConfig
public class BoardUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(BoardUpdateServlet.class);
	private BoardServiceI boardService;

	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		logger.debug("글번호 : {}",boardno);
		BoardVO boardVO = boardService.showBoardContent(boardno);
		List<FileVO> fileList = boardService.showFile(boardno);
		int filecount = fileList.size();
		
		request.setAttribute("boardVO", boardVO);
		request.setAttribute("fileList", fileList);
		request.setAttribute("filecount", filecount);
		request.getRequestDispatcher("/board/boardUpdate.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String boardtitle = request.getParameter("boardtitle");
		String boardcontent = request.getParameter("summernote");
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		String delno1 = request.getParameter("delete1");
		String delno2 = request.getParameter("delete2");
		String delno3 = request.getParameter("delete3");
		String delno4 = request.getParameter("delete4");
		String delno5 = request.getParameter("delete5");
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardtitle(boardtitle);
		boardVO.setBoardcontent(boardcontent);
		boardVO.setBoardno(boardno);
		
		logger.debug("제목 : {}, 내용 : {}, 번호 : {}",boardtitle,boardcontent,boardno);
		System.out.println(boardVO.getBoardtitle());
		System.out.println(boardVO.getBoardcontent());
		System.out.println(boardVO.getBoardno());
		
		int updateCnt = boardService.updateBoardContent(boardVO);
		System.out.println("업데이트" + updateCnt);
		
		if(!(delno1.equals("")) && delno1!=null) {
			int fileno = Integer.parseInt(delno1);
			boardService.deleteFile(fileno);
		}
		if(!(delno2.equals("")) && delno2!=null) {
			int fileno = Integer.parseInt(delno2);
			boardService.deleteFile(fileno);
		}
		if(!(delno3.equals("")) && delno3!=null) {
			int fileno = Integer.parseInt(delno3);
			boardService.deleteFile(fileno);
		}
		if(!(delno4.equals("")) && delno4!=null) {
			int fileno = Integer.parseInt(delno4);
			boardService.deleteFile(fileno);
		}
		if(!(delno5.equals("")) && delno5!=null) {
			int fileno = Integer.parseInt(delno5);
			boardService.deleteFile(fileno);
		}
		
		
		for (int i = 1; i < 6; i++) {

			Part file = request.getPart("realfilename" + i);
			logger.debug("file : {}", file.getHeader("Content-Disposition"));

			String realfilename = FileUploadUtil.getFilename(file.getHeader("Content-Disposition"));
			logger.debug("realfilename : {}", realfilename);

			String filename = UUID.randomUUID().toString(); // 어떤 중복X 문자열을 return한다.
			String filePath = "";
			String extension = FileUploadUtil.getExtension(realfilename);
			logger.debug("extension : {}", extension);

			if (file.getSize() > 0) {
				filePath = "D:\\board\\" + filename + "." + extension;
				file.write(filePath);
				FileVO fileVO = new FileVO();

				fileVO.setBoardno(boardno);
				fileVO.setFilename(realfilename);
				fileVO.setFilepath(filePath);

				int insertCnt = boardService.uploadFile(fileVO);

				logger.debug("파일 업로드 : {}", insertCnt);
			}
		}
		
		
		if(updateCnt == 1) {
			response.sendRedirect(request.getContextPath() + "/board?boardno=" + boardno);
		} else {
			doGet(request, response);
		}
		
		
	}

}
