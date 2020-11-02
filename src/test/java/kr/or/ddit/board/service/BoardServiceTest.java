package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.model.BoardTypeVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.FileVO;
import kr.or.ddit.board.model.PageVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.model.WriteVO;
import kr.or.ddit.member.model.MemberVO;
import kr.or.ddit.member.service.MemberService;

public class BoardServiceTest {
	
	BoardServiceI boardService;
	
	// 존재하는 게시판의 정보들을 출력하는 메서드
		@Test
		public void showBoardTest() {
			/***Given***/
			boardService = new BoardService();
			/***When***/
			List<BoardTypeVO> boardtypeList = boardService.showBoard();
			/***Then***/
			assertEquals(4,boardtypeList.size());
		}

		// 게시판을 생성하는 메서드
		@Test
		public void createBoardTest() {
			/***Given***/
			boardService = new BoardService();
			BoardTypeVO boardTypeVO = new BoardTypeVO();
			boardTypeVO.setBoardname("취미게시판");
			boardTypeVO.setBoarduse(0);
			/***When***/
			int cnt = boardService.createBoard(boardTypeVO);

			/***Then***/
			assertEquals(1, cnt);
			
		}

		// 사용 가능한 게시판의 정보들을 출력하는 메서드
		@Test
		public void showUseBoardTest() {
			/***Given***/
			boardService = new BoardService();
			/***When***/
			List<BoardTypeVO> testuseList = boardService.showUseBoard();
			/***Then***/
			assertEquals(4,testuseList.size());
		}

		// 게시판 정보를 수정하는 메서드
		@Test
		public void updateBoardTest() {
			/*** Given ***/
			boardService = new BoardService();
			Map<String, Integer> maps = new HashMap<>();
			maps.put("typeno", 4);
			maps.put("use", 0);
			/*** When ***/
			int test = boardService.updateBoard(maps);
			/*** Then ***/
			assertEquals(1, test);
			
		}

		// 해당 게시판의 글을 출력해주는 메서드
		@Test
		public void selectBoardTest(){
			/***Given***/
			boardService = new BoardService();
			int typeno = 3;
			/***When***/
			List<BoardVO> testlist = boardService.selectBoard(typeno);
			/***Then***/
			assertEquals(1, testlist.size());
			
		}

		
		@Test
		public void selectBoardPageListTest() {
			
			/***Given***/
			boardService = new BoardService();
			PageVO pageVo = new PageVO();
			pageVo.setTypeno(1);
			pageVo.setPage(1);
			pageVo.setPageSize(10);

			/***When***/
			Map<String, Object> map = boardService.selectBoardPageList(pageVo);
			List<MemberVO> boardList = (List<MemberVO>)map.get("boardList");
			
			//생성해야할 page 수
			int pages = (int)map.get("pages");

			/***Then***/
			assertEquals(10, boardList.size());
			
		}

		// 게시글을 작성하는 메서드
		@Test
		public void writeBoardTest() {
			/***Given***/
			boardService = new BoardService();
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
			int check = boardService.writeBoard(writeVO);
			/***Then***/
			assertEquals(check>0, true);
			
		}


		// 글 정보를 가져오는 메서드
		@Test
		public void showBoardContentTest() {
			
			/***Given***/
			boardService = new BoardService();
			int boardno = 1;
			/***When***/
			BoardVO boardVO = boardService.showBoardContent(boardno);
			/***Then***/
			assertEquals("user1", boardVO.getUserid());
			
		}

		// 해당 게시글의 파일 정보를 가져오는 메서드
		@Test
		public void showFileTest() {
			
			/***Given***/
			boardService = new BoardService();
			int boardno = 1;
			/***When***/
			List<FileVO> fileList = boardService.showFile(boardno);
			/***Then***/
			assertEquals(1, fileList.size());
			
		}

		// 파일 번호를 가지고 파일 정보를 가져오는 메서드
		@Test
		public void getFileTest() {
			
			/***Given***/
			boardService = new BoardService();
			int fileno = 1;
			/***When***/
			FileVO fileVO = boardService.getFile(fileno);
			/***Then***/
			assertEquals(1, fileVO.getBoardno());
			
		}

		// 게시글을 삭제하는 메서드
		@Test
		public void deleteBoardTest() {
			/***Given***/
			boardService = new BoardService();
			int boardno = 20;
			/***When***/
			int chk = boardService.deleteBoard(boardno);
			/***Then***/
			assertEquals(1, chk);
			
		}

		// 게시글 번호를 가지고 게시판 번호를 가져오는 메서드
		@Test
		public void gettypenoTest() {
			
			/***Given***/
			boardService = new BoardService();
			int boardno = 1;
			/***When***/
			int typeno = boardService.gettypeno(boardno);
			/***Then***/
			assertEquals(1, typeno);
			
		}

		// 답글을 작성하는 메서드
		@Test
		public void writeReplyTest() {
			
			/***Given***/
			boardService = new BoardService();
			String boardtitle = "답글작성22";
			String boardcontent = "<p>내용</p>";
			String userid = "user2";
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
			int check = boardService.writeReply(boardVO);
			/***Then***/
			assertEquals(check>0, true);
			
		}

		// 댓글을 작성하는 메서드
		@Test
		public void writeCommentTest() {
			/***Given***/
			boardService = new BoardService();
			String userid = "user2";
			String replycontent = "ddddddd";
			int boardno = 18;
			ReplyVO replyVO = new ReplyVO(replycontent, userid, boardno);
			/***When***/
			int chk = boardService.writeComment(replyVO);
			/***Then***/
			assertEquals(1, chk);
			
		}

		// 해당 글의 댓글을 보는 메서드
		@Test
		public void showCommentTest() {
			/***Given***/
			boardService = new BoardService();
			int boardno = 18;
			/***When***/
			List<ReplyVO> list = boardService.showComment(boardno);
			/***Then***/
			assertEquals(2, list.size());
			
		}

		// 댓글을 삭제하는 메서드
		@Test
		public void delCommentTest() {
			
			/***Given***/
			boardService = new BoardService();
			int replyno = 9;
			/***When***/
			int chk = boardService.delComment(replyno);
			/***Then***/
			assertEquals(1, chk);
		}

		// 파일번호로 파일을 삭제하는 메서드
		@Test
		public void deleteFileTest() {
			
			/***Given***/
			boardService = new BoardService();
			int fileno = 42;
			/***When***/
			int chk = boardService.deleteFile(fileno);
			/***Then***/
			assertEquals(1, chk);
			
		}

		// 글 수정 메서드
		@Test
		public void updateBoardContentTest() {

			/***Given***/
			boardService = new BoardService();
			String boardtitle = "글 수정할거";
			String boardcontent = "<p>zz</p>";
			int boardno = 41;
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardtitle(boardtitle);
			boardVO.setBoardno(boardno);
			boardVO.setBoardcontent(boardcontent);
			/***When***/
			int chk = boardService.updateBoardContent(boardVO);
			/***Then***/
			assertEquals(1, chk);
		}

		// 게시판 번호를 가지고 게시판 이름을 가져오는 메서드
		@Test
		public void boardNameTest() {
			
			/***Given***/
			boardService = new BoardService();
			int typeno = 1;
			/***When***/
			String boardname = boardService.boardName(typeno);
			/***Then***/
			assertEquals("자유게시판", boardname);
			
		}

		// 글번호로 파일을 삭제하는 메서드
		@Test
		public void fileDeleteAllTest() {
			
			/***Given***/
			boardService = new BoardService();
			int boardno = 44;
			/***When***/
			int chk = boardService.fileDeleteAll(boardno);
			/***Then***/
			assertEquals(1, chk);
		}

}
