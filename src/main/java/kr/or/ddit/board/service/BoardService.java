package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.BoardDaoI;
import kr.or.ddit.board.model.BoardTypeVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.FileVO;
import kr.or.ddit.board.model.PageVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.model.WriteVO;
import kr.or.ddit.db.MybatisUtil;

public class BoardService implements BoardServiceI{
	
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	private BoardDaoI boardDao;
	
	public BoardService() {
		boardDao = new BoardDao();
	}

	@Override
	public List<BoardTypeVO> showBoard() {
		return boardDao.showBoard();
	}

	@Override
	public int createBoard(BoardTypeVO boardTypeVO) {
		return boardDao.createBoard(boardTypeVO);
	}

	@Override
	public List<BoardTypeVO> showUseBoard() {
		return boardDao.showUseBoard();
	}

	@Override
	public int updateBoard(Map<String, Integer> maps) {
		return boardDao.updateBoard(maps);
	}

	@Override
	public List<BoardVO> selectBoard(int typeno) {
		return boardDao.selectBoard(typeno);
	}

	@Override
	public Map<String, Object> selectBoardPageList(PageVO pageVO) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int typeno = pageVO.getTypeno();
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardList", boardDao.selectBoardPageList(sqlSession, pageVO));
		
		int totalCnt = boardDao.selectBoardTotalCnt(sqlSession,typeno);
		int pages = (int)Math.ceil( (double) totalCnt/ pageVO.getPageSize());
		map.put("pages", pages);
		
		sqlSession.close();
		return map;
	}

	@Override
	public int writeBoard(WriteVO writeVO) {
		return boardDao.writeBoard(writeVO);
	}

	@Override
	public int uploadFile(FileVO fileVO) {
		return boardDao.uploadFile(fileVO);
	}

	@Override
	public BoardVO showBoardContent(int boardno) {
		return boardDao.showBoardContent(boardno);
	}

	@Override
	public List<FileVO> showFile(int boardno) {
		return boardDao.showFile(boardno);
	}

	@Override
	public FileVO getFile(int fileno) {
		return boardDao.getFile(fileno);
	}

	@Override
	public int deleteBoard(int boardno) {
		return boardDao.deleteBoard(boardno);
	}

	@Override
	public int gettypeno(int boardno) {
		return boardDao.gettypeno(boardno);
	}

	@Override
	public int writeReply(BoardVO boardVO) {
		return boardDao.writeReply(boardVO);
	}

	@Override
	public int writeComment(ReplyVO replyVO) {
		return boardDao.writeComment(replyVO);
	}

	@Override
	public List<ReplyVO> showComment(int boardno) {
		return boardDao.showComment(boardno);
	}

	@Override
	public int delComment(int replyno) {
		return boardDao.delComment(replyno);
	}

	@Override
	public int deleteFile(int fileno) {
		return boardDao.deleteFile(fileno);
	}

	@Override
	public int updateBoardContent(BoardVO boardVO) {
		return boardDao.updateBoardContent(boardVO);
	}

	@Override
	public String boardName(int typeno) {
		return boardDao.boardName(typeno);
	}

	@Override
	public int fileDeleteAll(int boardno) {
		return boardDao.fileDeleteAll(boardno);
	}

	
}
