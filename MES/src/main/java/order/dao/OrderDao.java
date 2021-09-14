package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.JdbcUtil;
import order.model.Order;

public class OrderDao {
	
	public Order insert(Connection conn, Order order) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into order_"
					+ "(plant_cd, order_no, order_dt, item_cd, barcode, manu_no, delivery_dt, order_qty, order_status, remark)"
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, order.getPlant_cd());
			pstmt.setString(2, order.getOrder_no());
			pstmt.setTimestamp(3, toTimestamp(order.getOrder_dt()));
			pstmt.setInt(4, order.getItem_cd());
			pstmt.setString(5, order.getBarcode());
			pstmt.setInt(6, order.getManu_no());
			pstmt.setTimestamp(7, toTimestamp(order.getDelivery_dt()));
			pstmt.setInt(8, order.getOrder_qyt());
			pstmt.setString(9, order.getOrder_status());
			pstmt.setString(10, order.getRemark());
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return order;
			}
			else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(stmt);
		}
	}
	
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}

}
