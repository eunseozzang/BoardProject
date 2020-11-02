package kr.or.ddit.member.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.db.MybatisUtil;
import kr.or.ddit.member.model.MemberVO;

public class MemberDao implements MemberDaoI{

	@Override
	public MemberVO getMember(String userId) {
		
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		
		MemberVO memberVO = sqlSession.selectOne("member.getMember",userId);
		
		sqlSession.close();
		
		return memberVO;
	}

}
