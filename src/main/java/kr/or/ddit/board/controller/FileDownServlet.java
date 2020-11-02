package kr.or.ddit.board.controller;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.FileVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceI;

/**
 * Servlet implementation class FileDownServlet
 */
@WebServlet("/fileDown")
public class FileDownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FileDownServlet.class);
	private BoardServiceI boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fileno = Integer.parseInt(request.getParameter("fileno"));
		logger.debug("파일번호 : {}",fileno);
		
		FileVO fileVO = boardService.getFile(fileno);
		
		System.out.println(fileVO.getFilename());
		System.out.println(fileVO.getFilepath());
		// response content-type 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileVO.getFilename()+ "\"");
		response.setContentType("application/octet-stream");		
		
		FileInputStream fis = new FileInputStream(fileVO.getFilepath());
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buffer = new byte[512];
		
		//읽을것이 없을 때 까지
		while(fis.read(buffer) != -1) {
			sos.write(buffer);
		}
		
		fis.close();
		//응답이 안간게 있으면 마지막으로 보내기
		sos.flush();
		sos.close();
		
		
	}

}
