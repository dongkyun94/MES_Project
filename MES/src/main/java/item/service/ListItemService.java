package item.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.connection.ConnectionProvider;


public class ListItemService {
	private ItemDao  itemDao = new ItemDao();
	private int size = 10;
	
	public ItemPage getItemPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			int total = itemDao.selectCount(conn);
			List<Item> content = itemDao.select(conn, (pageNum-1)*size, size);
			return new ItemPage(total, pageNum, size, content);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
