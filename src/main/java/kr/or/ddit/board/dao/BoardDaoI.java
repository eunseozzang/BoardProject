package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardTypeVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.FileVO;
import kr.or.ddit.board.model.PageVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.model.WriteVO;

public interface BoardDaoI {
	
	// 존재하는 게시판의 정보들을 출력하는 메서드
	List<BoardTypeVO> showBoard();
	
	// 게시판을 생성하는 메서드
	int createBoard(BoardTypeVO boardTypeVO);
	
	// 사용 가능한 게시판의 정보들을 출력하는 메서드
	List<BoardTypeVO> showUseBoard();
	
	// 게시판 정보를 수정하는 메서드
	int updateBoard(Map<String, Integer> maps);
	
	// 해당 게시판의 글을 출력해주는 메서드
	List<BoardVO> selectBoard(int typeno);
	
	int selectBoardTotalCnt(SqlSession sqlSession,int typeno);
	
	List<BoardVO> selectBoardPageList(SqlSession sqlSession,PageVO pageVO);
	
	// 게시글을 작성하는 메서드
	int writeBoard(WriteVO writeVO);
	
	// 파일을 등록하는 메서드
	int uploadFile(FileVO fileVO);
	
	// 글 정보를 가져오는 메서드
	BoardVO showBoardContent(int boardno);	
	
	// 해당 게시글의 파일 정보를 가져오는 메서드
	List<FileVO> showFile(int boardno);
	
	// 파일 번호를 가지고 파일 정보를 가져오는 메서드
	FileVO getFile(int fileno);
	
	// 게시글을 삭제하는 메서드
	int deleteBoard(int boardno);
	
	// 게시글 번호를 가지고 게시판 번호를 가져오는 메서드
	int gettypeno(int boardno);
	
	// 답글을 작성하는 메서드
	int writeReply(BoardVO boardVO);
	
	// 댓글을 작성하는 메서드
	int writeComment(ReplyVO replyVO);
	
	// 해당 글의 댓글을 보는 메서드
	List<ReplyVO> showComment(int boardno);
	
	// 댓글을 삭제하는 메서드
	int delComment(int replyno);
	
	// 파일번호로 파일을 삭제하는 메서드
	int deleteFile(int fileno);

	// 글 수정 메서드
	int updateBoardContent(BoardVO boardVO);
	
	// 게시판 번호를 가지고 게시판 이름을 가져오는 메서드
	String boardName(int typeno);
	
	// 글번호로 파일을 삭제하는 메서드
	int fileDeleteAll(int boardno);
	
}
