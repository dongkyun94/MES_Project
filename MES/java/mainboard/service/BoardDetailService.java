package mainboard.service;

import jdbc.JdbcUtil;

import java.sql.Connection;

import jdbc.connection.ConnectionProvider;
import mainboard.dao.BoardDAO;
import mainboard.model.MainBoard;

public class BoardDetailService {

	private BoardDAO boardDao = new BoardDAO();

	//데이터베이스 커넥션을 위한 getBoard 메소드
	public MainBoard getBoard(int boardNum) {
		
		Connection conn = null;
		MainBoard board = null;
		
		try {
			//데이터베이스 커넥션
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			//조회수 업데이트하기
			int updateCount = boardDao.updateReadCount(conn, boardNum);
			//게시판 선택하기
			board = boardDao.selectBoard(conn, boardNum);
			
			// 업데이트가 되었다면 커밋하고, 아니면 rollback
			if(updateCount > 0) JdbcUtil.commit(conn); else JdbcUtil.rollback(conn);
			
			return board;
			
		} catch (Exception e) {
			System.out.println("BoardDetailService.getBoard() \n" + e);
			return null;
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
