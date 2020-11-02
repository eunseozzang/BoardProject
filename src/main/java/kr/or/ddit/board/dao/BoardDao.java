package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardTypeVO;
import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.model.FileVO;
import kr.or.ddit.board.model.PageVO;
import kr.or.ddit.board.model.ReplyVO;
import kr.or.ddit.board.model.WriteVO;
import kr.or.ddit.db.MybatisUtil;

public class BoardDao implements BoardDaoI{
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);

	@Override
	public List<BoardTypeVO> showBoard() {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<BoardTypeVO> typeList = sqlSession.selectList("board.showBoard");
		
		sqlSession.close();
		
		return typeList;
	}

	@Override
	public int createBoard(BoardTypeVO boardTypeVO) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int createCnt = 0;
		
		try {
			createCnt = sqlSession.insert("board.createBoard",boardTypeVO);
		} catch (Exception e) {
		}
		
		if(createCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		
		return createCnt;
	}

	@Override
	public List<BoardTypeVO> showUseBoard() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<BoardTypeVO> typeList = sqlSession.selectList("board.showUseBoard");
		
		sqlSession.close();
		
		return typeList;
	}

	@Override
	public int updateBoard(Map<String, Integer> maps) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		int updateCnt = sqlSession.update("board.updateBoard",maps);
		
		if(updateCnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return updateCnt;
	}

	@Override
	public List<BoardVO> selectBoard(int typeno) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		List<BoardVO> boardList = sqlSession.selectList("board.selectBoard",typeno);
		
		sqlSession.close();
		
		return boardList;
	}

	@Override
	public int selectBoardTotalCnt(SqlSession sqlSession,int typeno) {
		return sqlSession.selectOne("board.selectBoardTotalCnt",typeno);
	}

	@Override
	public List<BoardVO> selectBoardPageList(SqlSession sqlSession,PageVO pageVO) {
		return sqlSession.selectList("board.selectBoardPageList",pageVO);
	}

	@Override
	public int writeBoard(WriteVO writeVO) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertCnt = 0;
		
		try {
			insertCnt = sqlSession.insert("board.writeBoard",writeVO);
		} catch (Exception e) {
		}
		
		if(insertCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return writeVO.getBoardno();
	}

	@Override
	public int uploadFile(FileVO fileVO) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int uploadCnt = 0;
		try {
			uploadCnt = sqlSession.insert("board.uploadFile",fileVO);
		} catch (Exception e) {
		}
		
		if(uploadCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		
		return uploadCnt;
	}

	@Override
	public BoardVO showBoardContent(int boardno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardVO boardVO = sqlSession.selectOne("board.showBoardContent",boardno);
		sqlSession.close();
		return boardVO;
	}

	@Override
	public List<FileVO> showFile(int boardno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<FileVO> fileList = sqlSession.selectList("board.showFile",boardno);
		sqlSession.close();
		return fileList;
	}

	@Override
	public FileVO getFile(int fileno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		FileVO fileVO = sqlSession.selectOne("board.getFile",fileno);
		sqlSession.close();
		return fileVO;
	}

	@Override
	public int deleteBoard(int boardno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int delCnt = sqlSession.update("board.deleteBoard",boardno);
		if(delCnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return delCnt;
	}

	@Override
	public int gettypeno(int boardno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int typeno = sqlSession.selectOne("board.gettypeno",boardno);
		sqlSession.close();
		return typeno;
	}

	@Override
	public int writeReply(BoardVO boardVO) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertCnt = 0;
		try {
			insertCnt = sqlSession.insert("board.writeReply",boardVO);
		} catch (Exception e) {
		}
		if(insertCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return boardVO.getBoardno();
	}

	@Override
	public int writeComment(ReplyVO replyVO) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertCnt = 0;
		
		try {
			insertCnt = sqlSession.insert("board.writeComment",replyVO);
		} catch (Exception e) {
			
		}
		
		if(insertCnt == 1) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();		
		return insertCnt;
	}

	@Override
	public List<ReplyVO> showComment(int boardno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<ReplyVO> replyList = sqlSession.selectList("board.showComment",boardno);
		sqlSession.close();
		return replyList;
	}

	@Override
	public int delComment(int replyno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int delCnt = sqlSession.update("board.delComment",replyno);
		if(delCnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return delCnt;
	}

	@Override
	public int deleteFile(int fileno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int delCnt = sqlSession.delete("board.fileDelete",fileno);
		if(delCnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return delCnt;
	}

	@Override
	public int updateBoardContent(BoardVO boardVO) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int upChk = 0;
		try {
			upChk = sqlSession.update("board.updateBoardContent",boardVO);
		} catch(Exception e) {
			
		}
		if(upChk>0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return upChk;
	}

	@Override
	public String boardName(int typeno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		String boardname = sqlSession.selectOne("board.boardName",typeno);
		sqlSession.close();
		return boardname;
	}

	@Override
	public int fileDeleteAll(int boardno) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int delCnt = sqlSession.delete("board.fileDeleteAll",boardno);
		if(delCnt > 0) {
			sqlSession.commit();
		} else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return delCnt;
	}

}
