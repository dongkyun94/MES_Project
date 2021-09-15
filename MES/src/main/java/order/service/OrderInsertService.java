package order.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
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
			Order savedOrder = orderDao.insert(conn, order);
			if (savedOrder == null) {
				throw new RuntimeException("fail to insert order");
			}
			
			conn.commit();
			
			return savedOrder.getOrder_no();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (ParseException e) {
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

	private Order toOrder(OrderInsertRequest req) throws ParseException {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String idx = "";
		try {
			conn = ConnectionProvider.getConnection();
			String sql = "select idx_order_no.nextval seq_num from dual";
			stmt = conn.createStatement();
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
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String first = sdf.format(toDay);
		String order_no = "D"+first+ idx;
		return new Order(req.getComp_cd(), req.getPlant_cd(), order_no, toDay, req.getItem_cd(), sdf1.parse(req.getDelivery_dt()), req.getOrder_qyt(), req.getOrder_status(), req.getRemark());
	}

}
