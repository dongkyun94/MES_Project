package mainboard.model;

import java.util.Date;

//Board 테이블에서 데이터를 받을 객체
public class MainBoard {
	
	private int boardNum; 
	private String boardTitle;
	private String boardContent;
	private String memberid;
	private Date boardDate;
	private String boardFile;
	private int boardAvailable;
	private int boardReadCount;
	
	public MainBoard() {};
	
	public MainBoard(int boardNum, String boardTitle, String boardContent, String memberid, java.sql.Date boardDate, String boardFile,
			int boardAvailable, int boardReadCount) {
		super();
		this.boardNum = boardNum;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberid = memberid;
		this.boardDate = boardDate;
		this.boardFile = boardFile;
		this.boardAvailable = boardAvailable;
		this.boardReadCount = boardReadCount;
	}
	
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardFile() {
		return boardFile;
	}
	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}
	public int getBoardAvailable() {
		return boardAvailable;
	}
	public void setBoardAvailable(int boardAvailable) {
		this.boardAvailable = boardAvailable;
	}
	public int getBoardReadCount() {
		return boardReadCount;
	}
	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}
}