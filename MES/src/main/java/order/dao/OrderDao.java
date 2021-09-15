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
			pstmt = conn.prepareStatement("insert into ordering"
					+ "(comp_cd, plant_cd, order_no, order_dt, item_cd, delivery_dt, order_qty, order_status, remark)"
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, order.getComp_cd());
			pstmt.setInt(2, order.getPlant_cd());
			pstmt.setString(3, order.getOrder_no());
			pstmt.setTimestamp(4, toTimestamp(order.getOrder_dt()));
			pstmt.setInt(5, order.getItem_cd());
			pstmt.setTimestamp(6, toTimestamp(order.getDelivery_dt()));
			pstmt.setInt(7, order.getOrder_qyt());
			pstmt.setString(8, order.getOrder_status());
			pstmt.setString(9, order.getRemark());
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return order;
			}
			else {
				System.out.println("데이터 입력 실패");
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
