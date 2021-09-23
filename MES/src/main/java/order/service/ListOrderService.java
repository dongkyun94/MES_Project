package order.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;
import order.model.Order;

public class ListOrderService {
	
	private OrderDao orderDao = new OrderDao();
	private int size = 10;
	
	public OrderPage getOrderPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			int total = orderDao.selectCount(conn);
			List<Order> content = orderDao.select(conn, (pageNum-1)*size, size);
			return new OrderPage(total, pageNum, size, content);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
