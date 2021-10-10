package mainboard.dao;

import jdbc.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mainboard.model.MainBoard;
import mainboard.service.BoardInsertRequest;

public class BoardDAO {

/**************************
[A] 글쓰기
***************************/
//A-1 글 쓰기(DB 입력) 메소드
public BoardInsertRequest boardInsert(Connection conn, BoardInsertRequest req, int num) {
	
	PreparedStatement pstmt = null;
	Statement stmt = null;
	ResultSet rs = null;
	String sql = "insert into MAINBOARD values(?,?,?,?,sysdate,?,?,?)";
	
	int insertedCount = 0; //잘 들어갔는지 확인하는 변수
	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		pstmt.setString(2, req.getBoardTitle());
		pstmt.setString(3, req.getBoardContent());
		pstmt.setString(4, req.getMemberid());
		pstmt.setString(5, req.getBoardFile());
		pstmt.setInt(6, 1);
		pstmt.setInt(7, 0);
		insertedCount = pstmt.executeUpdate();
		System.out.println(num);
		
	} catch (Exception e) {
		System.out.println("데이터 입력 실패 \n BoardDAO.boardInsert() \n" + e);
	} finally {
		JdbcUtil.close(pstmt, rs);
	}
	
	//데이터가 잘 입력되었다면 board를 리턴
	if(insertedCount > 0) {
		return req;
	} else {
		return null;
	}
		
}

//A-2 DB에 입력되어 있는 전체 게시판 수 조회 메소드
public int selectCount(Connection conn) throws SQLException {
	
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select count(*) from MAINBOARD");
		if(rs.next()) { return rs.getInt(1); }
		return 0;
	} finally {
		JdbcUtil.close(stmt, rs);
	}
	
}


/**************************
[B] 글 조회
***************************/
//B-1 DB 게시판 리스트 조회 메서드
public List<MainBoard> getBoardList(Connection conn, int page, int limit){
	
	List<MainBoard> result = new ArrayList<MainBoard>();
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "select * from "
			+ "(select row_number() over (order by boardNum desc) num,"
			+ " A.* from MAINBOARD A WHERE BOARDAVAILABLE = 1 order by boardNum desc) "
			+ "where num between ? and ?";
	int startRow = (page-1) * limit;
	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, startRow+limit);
		rs = pstmt.executeQuery();
		while(rs.next()) { result.add(convertBoard(rs)); }
		return result;
	} catch (Exception e) {
		System.out.println("BoardDAO.getBoardList() \n" + e);
		return null;
	} finally {
		JdbcUtil.close(pstmt, rs);
	}
}

//B-2 DB 에서 조회한 Board를 Board객체로 변환하는 메소드
private MainBoard convertBoard(ResultSet rs) throws SQLException{
	return new MainBoard(rs.getInt("boardNum"),
			rs.getString("boardTitle"),
			rs.getString("boardContent"),
			rs.getString("memberid"),
			rs.getDate("boardDate"),
			rs.getString("boardFile"),
			rs.getInt("boardAvailable"),
			rs.getInt("boardReadCount"));
}

/**************************
[C] 단일 게시글 조회
***************************/
//C-1 조회수 업데이트하기
public int updateReadCount(Connection conn, int boardNum) {
	
	int updateCount = 0;
	
	PreparedStatement pstmt = null;
	String sql = "update MAINBOARD set boardReadCount = boardReadCount + 1 " +
            " where boardNum = " + boardNum;
	
	try {
		pstmt = conn.prepareStatement(sql);
		updateCount = pstmt.executeUpdate();
	} catch (Exception e) {
		System.out.println("BoardDAO.updateReadCount() \n" + e);
	} finally {
		JdbcUtil.close(pstmt);
	}
	
	//성공했다면 1리턴
	return updateCount;
}

//C-2 게시판 선택하기
public MainBoard selectBoard(Connection conn, int boardNum) {
	
	MainBoard board = null;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "select * from MAINBOARD where BOARDNUM = " + boardNum
			+ "and BOARDAVAILABLE = 1";
	
	try {
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()) { board = convertBoard(rs); }
	} catch (Exception e) {
		System.out.println("BoardDAO.selectBoard() \n" + e);
	} finally {
		JdbcUtil.close(pstmt, rs);
	}
	
	//모두 성공했다면 선택한 보드 데이터를 전달함
	return board;
}

/**************************
[D] 게시글 삭제
***************************/
//D-1 게시글 삭제 메소드
public int deleteBoard(Connection conn, int boardNum) {
	
	int deleteCount = 0;
	PreparedStatement pstmt = null;
	//String sql = "delete from MAINBOARD where boardNum = " + boardNum;
	String sql = "update MAINBOARD set BOARDAVAILABLE = 0 where boardNum = " + boardNum;
	
	try {
		pstmt = conn.prepareStatement(sql);
		deleteCount = pstmt.executeUpdate();
	} catch (Exception e) {
		System.out.println("BoardDAO.deleteBoard() \n" + e);
	} finally {
		JdbcUtil.close(pstmt);
	}
	
	//삭제되었다면 값1이 들어감
	return deleteCount;
}

/**************************
[D] 게시글 수정
***************************/
//E-1 [SQL] 게시판 업데이트 메소드
public int updateBoard(Connection conn, BoardInsertRequest board, int boardNum) {
	
	int updateCount = 0;
	PreparedStatement pstmt = null;
	String sql = "update MAINBOARD set boardTitle = ?, boardContent = ?, boardFile = ? " +
	             " where boardNum = ? ";
	
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board.getBoardTitle());
		pstmt.setString(2, board.getBoardContent());
		pstmt.setString(3, board.getBoardFile());
		pstmt.setInt(4, boardNum);
		updateCount = pstmt.executeUpdate();
	} catch (Exception e) {
		System.out.println("BoardDAO.updateBoard() \n" + e);
	} finally {
		JdbcUtil.close(pstmt);
	}
	return updateCount; 
}


}
