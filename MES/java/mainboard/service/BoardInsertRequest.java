package mainboard.service;

import java.util.Map;

public class BoardInsertRequest {
	
	private String boardTitle;
	private String boardContent;
	private String memberid;
	private String boardFile;
	
	public BoardInsertRequest(String boardTitle, String boardContent, String memberid, String string) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberid = memberid;
		this.boardFile = string;
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
	public String getBoardFile() {
		return boardFile;
	}
	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}

	//유효성검사
	public void validate(Map<String, Boolean> errors) {
		if(boardTitle == null) { errors.put("boardTitle", Boolean.TRUE);}
		if(boardContent == null) { errors.put("boardContent", Boolean.TRUE);}
		if(memberid == null) { errors.put("memberid", Boolean.TRUE);}
	}
	
}
