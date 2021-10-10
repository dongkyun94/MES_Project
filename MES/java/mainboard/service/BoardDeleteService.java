package mainboard.service;

import java.sql.Connection;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mainboard.dao.BoardDAO;

public class BoardDeleteService {

	private BoardDAO boardDao = new BoardDAO();
	
	//게시판 삭제 메소드
	public boolean deleteBoard(int boardNum) {
		
		boolean isDeleteSuccess = false;
		Connection conn = null;
		
		try {
			//데이터베이스 커넥션
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int deleteCount = boardDao.deleteBoard(conn, boardNum);
			
			//삭제되었다면
			if(deleteCount > 0) {
				JdbcUtil.commit(conn);
				isDeleteSuccess = true;
			//삭제되지 않으면
			} else { JdbcUtil.rollback(conn); }
			
		} catch (Exception e) {
			System.out.println("BoardDeleteService.deleteBoard() \n" + e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return isDeleteSuccess;

	}

}
