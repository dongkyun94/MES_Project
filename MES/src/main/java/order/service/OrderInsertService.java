package order.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;
import order.model.Order;

public class OrderInsertService {
	
	private OrderDao orderDao = new OrderDao();
	
	public String insert(OrderInsertRequest req) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Order order = toOrder(req);
			
		}
	}

	private Order toOrder(OrderInsertRequest req) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String idx = "";
		try {
			conn = ConnectionProvider.getConnection();
			String sql = "select idx_order_no.nextval seq_num from dual";
			rs = stmt.executeQuery(sql);
			rs.next();
			idx = rs.getString("seq_num");
		}catch (SQLException e) {
		}
		
		
		JdbcUtil.close(conn);
		JdbcUtil.close(stmt);
		JdbcUtil.close(rs);
		
		Date toDay = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String first = sdf.format(toDay);
		String order_no = "D"+first+ idx;
		return null;
	}

}
