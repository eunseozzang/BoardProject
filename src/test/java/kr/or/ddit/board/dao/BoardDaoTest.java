package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import kr.or.ddit.board.model.BoardTypeVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.FileVO;
import kr.or.ddit.board.model.PageVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.model.WriteVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.db.MybatisUtil;

public class BoardDaoTest {

	BoardDaoI boardDao;

	// 존재하는 게시판의 정보들을 출력하는 메서드
	@Test
	public void showBoardTest() {
		/***Given***/
		boardDao = new BoardDao();
		/***When***/
		List<BoardTypeVO> boardtypeList = boardDao.showBoard();
		/***Then***/
		assertEquals(3,boardtypeList.size());
	}

	// 게시판을 생성하는 메서드
	@Test
	public void createBoardTest() {
		/***Given***/
		boardDao = new BoardDao();
		BoardTypeVO boardTypeVO = new BoardTypeVO();
		boardTypeVO.setBoardname("임시게시판");
		boardTypeVO.setBoarduse(0);
		/***When***/
		int cnt = boardDao.createBoard(boardTypeVO);

		/***Then***/
		assertEquals(1, cnt);
		
	}

	// 사용 가능한 게시판의 정보들을 출력하는 메서드
	@Test
	public void showUseBoardTest() {
		/***Given***/
		boardDao = new BoardDao();
		/***When***/
		List<BoardTypeVO> testuseList = boardDao.showUseBoard();
		/***Then***/
		assertEquals(3,testuseList.size());
	}

	// 게시판 정보를 수정하는 메서드
	@Test
	public void updateBoardTest() {
		/***Given***/
		boardDao = new BoardDao();
		Map<String, Integer> maps = new HashMap<>();
		maps.put("typeno", 4);
		maps.put("use", 1);
		/***When***/
		int test = boardDao.updateBoard(maps);
		/***Then***/
		assertEquals(1, test);
	}

	// 해당 게시판의 글을 출력해주는 메서드
	@Test
	public void selectBoardTest(){
		/***Given***/
		boardDao = new BoardDao();
		int typeno = 3;
		/***When***/
		List<BoardVO> testlist = boardDao.selectBoard(typeno);
		/***Then***/
		assertEquals(1, testlist.size());
		
	}

	@Test
	public void selectBoardTotalCntTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int typeno = 1;
		/***When***/
		int totalCnt = boardDao.selectBoardTotalCnt(sqlSession, typeno);
		/***Then***/
		assertEquals(32, totalCnt);
		
	}

	
	@Test
	public void selectBoardPageListTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setPageSize(10);
		pageVO.setTypeno(1);
		/***When***/
		List<BoardVO> pageList = boardDao.selectBoardPageList(sqlSession, pageVO);
		/***Then***/
		assertEquals(10, pageList.size());
		
	}

	// 게시글을 작성하는 메서드
	@Test
	public void writeBoardTest() {
		/***Given***/
		boardDao = new BoardDao();
		String boardtitle = "게시글작성";
		String boardcontent = "<p>내용</p>";
		String userid = "user3";
		int typeno = 1;
		WriteVO writeVO = new WriteVO();
		writeVO.setBoardcontent(boardcontent);
		writeVO.setBoardtitle(boardtitle);
		writeVO.setTypeno(typeno);
		writeVO.setUserid(userid);
		/***When***/
		int check = boardDao.writeBoard(writeVO);
		/***Then***/
		assertEquals(check>0, true);
	}

	// 글 정보를 가져오는 메서드
	@Test
	public void showBoardContentTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		int boardno = 1;
		/***When***/
		BoardVO boardVO = boardDao.showBoardContent(boardno);
		/***Then***/
		assertEquals("user1", boardVO.getUserid());
		
	}

	// 해당 게시글의 파일 정보를 가져오는 메서드
	@Test
	public void showFileTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		int boardno = 1;
		/***When***/
		List<FileVO> fileList = boardDao.showFile(boardno);
		/***Then***/
		assertEquals(1, fileList.size());
	}

	// 파일 번호를 가지고 파일 정보를 가져오는 메서드
	@Test
	public void getFileTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		int fileno = 1;
		/***When***/
		FileVO fileVO = boardDao.getFile(fileno);
		/***Then***/
		assertEquals(1, fileVO.getBoardno());
	}

	// 게시글을 삭제하는 메서드
	@Test
	public void deleteBoardTest() {
		/***Given***/
		boardDao = new BoardDao();
		int boardno = 19;
		/***When***/
		int chk = boardDao.deleteBoard(boardno);
		/***Then***/
		assertEquals(1, chk);
	}

	// 게시글 번호를 가지고 게시판 번호를 가져오는 메서드
	@Test
	public void gettypenoTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		int boardno = 1;
		/***When***/
		int typeno = boardDao.gettypeno(boardno);
		/***Then***/
		assertEquals(1, typeno);
		
	}

	// 답글을 작성하는 메서드
	@Test
	public void writeReplyTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		String boardtitle = "답글작성";
		String boardcontent = "<p>내용</p>";
		String userid = "user1";
		int parentno = 1;
		int groupno = 1;
		int typeno = 1;
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardcontent(boardcontent);
		boardVO.setBoardtitle(boardtitle);
		boardVO.setTypeno(typeno);
		boardVO.setUserid(userid);
		boardVO.setParentno(parentno);
		boardVO.setGroupno(groupno);
		/***When***/
		int check = boardDao.writeReply(boardVO);
		/***Then***/
		assertEquals(check>0, true);
		
	}

	// 댓글을 작성하는 메서드
	@Test
	public void writeCommentTest() {
		/***Given***/
		boardDao = new BoardDao();
		String userid = "user1";
		String replycontent = "dddd";
		int boardno = 18;
		ReplyVO replyVO = new ReplyVO(replycontent, userid, boardno);
		/***When***/
		int chk = boardDao.writeComment(replyVO);
		/***Then***/
		assertEquals(1, chk);
	}

	// 해당 글의 댓글을 보는 메서드
	@Test
	public void showCommentTest() {
		/***Given***/
		boardDao = new BoardDao();
		int boardno = 18;
		/***When***/
		List<ReplyVO> list = boardDao.showComment(boardno);
		/***Then***/
		assertEquals(2, list.size());
	}

	// 댓글을 삭제하는 메서드
	@Test
	public void delCommentTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		int replyno = 23;
		/***When***/
		int chk = boardDao.delComment(replyno);
		/***Then***/
		assertEquals(1, chk);
		
	}

	// 파일번호로 파일을 삭제하는 메서드
	@Test
	public void deleteFileTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		int fileno = 41;
		/***When***/
		int chk = boardDao.deleteFile(fileno);
		/***Then***/
		assertEquals(1, chk);
		
	}

	// 글 수정 메서드
	@Test
	public void updateBoardContentTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		String boardtitle = "글 수정할거2";
		String boardcontent = "<p>zz2</p>";
		int boardno = 41;
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardtitle(boardtitle);
		boardVO.setBoardno(boardno);
		boardVO.setBoardcontent(boardcontent);
		/***When***/
		int chk = boardDao.updateBoardContent(boardVO);
		/***Then***/
		assertEquals(1, chk);
		
	}

	// 게시판 번호를 가지고 게시판 이름을 가져오는 메서드
	@Test
	public void boardNameTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		int typeno = 1;
		/***When***/
		String boardname = boardDao.boardName(typeno);
		/***Then***/
		assertEquals("자유게시판", boardname);
	}

	// 글번호로 파일을 삭제하는 메서드
	@Test
	public void fileDeleteAllTest() {
		
		/***Given***/
		boardDao = new BoardDao();
		int boardno = 45;
		/***When***/
		int chk = boardDao.fileDeleteAll(boardno);
		/***Then***/
		assertEquals(1, chk);
		
	}

}
