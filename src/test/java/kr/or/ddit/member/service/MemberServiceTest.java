package kr.or.ddit.member.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import kr.or.ddit.member.model.MemberVO;

public class MemberServiceTest {
	
	MemberServiceI memberService;
	
	@Test
	public void getMembertest() {
		
		/***Given***/
		
		memberService = new MemberService();
		String userid= "user1";
		MemberVO testmemberVO = new MemberVO();
		testmemberVO.setUserid(userid);
		testmemberVO.setUsername("최은서");
		
		/***When***/

		MemberVO memberVO = memberService.getMember(userid);
		
		/***Then***/
		assertEquals("user1", memberVO.getUserid());
		assertEquals("최은서", memberVO.getUsername());
		
	}

}
