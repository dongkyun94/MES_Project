package order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.JdbcUtil;
import order.model.Order;

public class OrderDao {
	
	/* 주문내용 DB 입력을 위한 insert 메소드*/
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
	
	/*DB에 입력되어 있는 주문 갯수 조회 메소드*/
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from ordering");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	/*DB order 리스트 조회 메소드*/
	public List<Order> select (Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from (select row_number() over (order by order_no desc) num, o.* from ordering o order by order_no desc) "
					+ "where num between ? and ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Order> result = new ArrayList<Order>();
			while(rs.next()) {
				result.add(convertOrder(rs));
			}
			return result;
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	/*DB 에서 조회한 Order 를 Order 객체로 변환하는 메소드*/
	private Order convertOrder(ResultSet rs) throws SQLException{
		return new Order(rs.getInt("comp_cd"),
				rs.getInt("palnt_cd"),
				rs.getString("order_no"),
				toDate(rs.getTimestamp("order_dt")),
				rs.getInt("item_cd"),
				toDate(rs.getTimestamp("delivery_dt")),
				rs.getInt("order_qyt"),
				rs.getString("order_status"),
				rs.getString("remark"));
	}
	
	/*Timestamp -> Date 변환 메소드*/
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

}
