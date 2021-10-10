package mainboard.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import mainboard.dao.BoardDAO;
import mainboard.model.MainBoard;
import mainboard.model.PageInfo;

public class ListBoardService {

	private BoardDAO boardDao = new BoardDAO();
	
	public PageInfo getBoardList(int page, int limit) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			//전체 게시판 수 조회 메소드
			int total = boardDao.selectCount(conn);
			//전체 게시글 가져오기
			List<MainBoard> content = boardDao.getBoardList(conn, page, limit);
			//페이징 객체 만들고 생성하기
			return new PageInfo(content, page, limit, total);
			
		} catch (SQLException e) {
			System.out.println("ListBoardService.getOrderPage() \n" + e);
			return null;
		}
		
	}

}
