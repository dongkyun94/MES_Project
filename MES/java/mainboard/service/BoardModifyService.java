package mainboard.service;

import java.sql.Connection;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mainboard.dao.BoardDAO;

public class BoardModifyService {

	private BoardDAO boardDao = new BoardDAO();
	
	//[DB] 게시판 업데이트 메소드
	public boolean modifyBoard(BoardInsertRequest board, int boardNum) {
		
		boolean isModifySuccess = false;
		Connection conn = null;
		
		try {
			//데이터베이스 커넥션
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//[SQL] 게시판 업데이트 메소드
			int updateCount = boardDao.updateBoard(conn, board, boardNum);
			
			if(updateCount > 0) {
				JdbcUtil.commit(conn);
				isModifySuccess = true;
			}else {
				JdbcUtil.rollback(conn);
			}
		} catch (Exception e) {
			System.out.println("BoardModifyService.modifyBoard() \n" + e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return isModifySuccess;
	}

}
