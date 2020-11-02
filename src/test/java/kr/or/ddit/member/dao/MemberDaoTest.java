package kr.or.ddit.member.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import kr.or.ddit.member.model.MemberVO;

public class MemberDaoTest {
	
	MemberDaoI memberDao;
	
	@Test
	public void getMembertest() {
		
		/***Given***/
		
		memberDao = new MemberDao();
		String userid= "user1";
		MemberVO testmemberVO = new MemberVO();
		testmemberVO.setUserid(userid);
		testmemberVO.setUsername("최은서");
		
		/***When***/

		MemberVO memberVO = memberDao.getMember(userid);
		
		/***Then***/
		assertEquals("user1", memberVO.getUserid());
		assertEquals("최은서", memberVO.getUsername());
		
	}

}
