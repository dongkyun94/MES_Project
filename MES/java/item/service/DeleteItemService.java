package item.service;

import java.sql.Connection;
import java.sql.SQLException;

import item.dao.ItemDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;

public class DeleteItemService {
	
	private ItemDao itemDao = new ItemDao();
	
	public void delete(int item_cd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			itemDao.delete(conn, item_cd);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}

}
