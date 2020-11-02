package kr.or.ddit.member.service;

import kr.or.ddit.member.model.MemberVO;

public interface MemberServiceI {
	
	MemberVO getMember(String userId);
	
}
