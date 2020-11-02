package kr.or.ddit.board.model;

public class BoardTypeVO {
	
	private String boardname;
	private int boarduse;
	private int typeno;
	
	public String getBoardname() {
		return boardname;
	}
	public void setBoardname(String boardname) {
		this.boardname = boardname;
	}
	public int getBoarduse() {
		return boarduse;
	}
	public void setBoarduse(int boarduse) {
		this.boarduse = boarduse;
	}
	public int getTypeno() {
		return typeno;
	}
	public void setTypeno(int typeno) {
		this.typeno = typeno;
	}
	@Override
	public String toString() {
		return "BoardTypeVO [boardname=" + boardname + ", boarduse=" + boarduse + ", typeno=" + typeno + "]";
	}

}
