package kr.or.ddit.board.model;

import java.util.Date;

public class ReplyVO {
	
	private int replyno;
	private Date replydate;
	private String replycontent;
	private int replydel;
	private String userid;
	private int boardno;
	
	// 기본생성자
	public ReplyVO() {
		
	}
	
	public ReplyVO(String replycontent,String userid,int boardno) {
		this.replycontent = replycontent;
		this.userid = userid;
		this.boardno = boardno;
	}
	
	
	public int getReplyno() {
		return replyno;
	}
	public void setReplyno(int replyno) {
		this.replyno = replyno;
	}
	public Date getReplydate() {
		return replydate;
	}
	public void setReplydate(Date replydate) {
		this.replydate = replydate;
	}
	public String getReplycontent() {
		return replycontent;
	}
	public void setReplycontent(String replycontent) {
		this.replycontent = replycontent;
	}
	public int getReplydel() {
		return replydel;
	}
	public void setReplydel(int replydel) {
		this.replydel = replydel;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getBoardno() {
		return boardno;
	}
	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}
	@Override
	public String toString() {
		return "ReplyVO [replyno=" + replyno + ", replydate=" + replydate + ", replycontent=" + replycontent
				+ ", replydel=" + replydel + ", userid=" + userid + ", boardno=" + boardno + "]";
	}

}
