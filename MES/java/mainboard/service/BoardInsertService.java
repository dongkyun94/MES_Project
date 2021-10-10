package mainboard.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mainboard.dao.BoardDAO;

public class BoardInsertService {

	private BoardDAO boardDao = new BoardDAO(); //BoardDAO객체 생성
	
	public Boolean insertBoard(BoardInsertRequest req){
		
		Connection conn = null;
		Boolean IsBoardWrite = false;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int num = toBoard();
			BoardInsertRequest saveboard = boardDao.boardInsert(conn, req, num);
			
			if(saveboard != null) {
				JdbcUtil.commit(conn);
				IsBoardWrite = true;
			} else {
				JdbcUtil.rollback(conn);
			}
			
			return IsBoardWrite;
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

	//데이터베이스 게시글 번호를 만드는 toBoard 메소드
	private int toBoard() {
		
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		int num = 0;
		
		try {
			conn = ConnectionProvider.getConnection();
			String sql = "select max(boardNum) from MAINBOARD";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) num = rs.getInt(1) + 1; else num = 1;
			
		} catch (SQLException e) {
			System.out.println("게시글 번호 가져오기 실패 \n BoardInsertService.toBoard() " + e);
		} finally {
			JdbcUtil.close(conn, stmt, rs);			
		}
		
		return num;
	}
	
}
