package item.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import item.model.Item;
import jdbc.JdbcUtil;

public class ItemDao {
	public Item insert(Connection conn, Item item) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into item"
					+ " (comp_cd, plant_cd, acct_id, item_cd, item_nm, item_spec, item_spec2, item_color, cust_cd, acct_price, currency,"
					+ " unit_cd, remark, in_usr_id, in_date)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, item.getComp_cd());
			pstmt.setInt(2, item.getPlant_cd());
			pstmt.setString(3, item.getAcct_id());
			pstmt.setInt(4, item.getItem_cd());
			pstmt.setString(5, item.getItem_nm());
			pstmt.setString(6, item.getItem_spec());
			pstmt.setString(7, item.getItem_spec2());
			pstmt.setString(8, item.getItem_color());
			pstmt.setString(9, item.getCust_cd());
			pstmt.setInt(10, item.getAcct_price());
			pstmt.setString(11, item.getCurrency());
			pstmt.setString(12, item.getUnit_cd());
			pstmt.setString(13, item.getRemark());
			pstmt.setString(14, item.getIn_usr_id());
			pstmt.setTimestamp(15, toTimestamp(item.getIn_date()));
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return item;
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

	/* DB에 담긴 데이터 수 조회 */
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from item");
			if(rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	/* DB전체 조회 후, List에 담기 */
	public List<Item> select (Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from (select row_number() over (order by item_cd desc) num, i.* from item i order by item_cd desc) "
					+ "where num between ? and ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Item> result = new ArrayList<Item>();
			while(rs.next()) {
				result.add(convertItem(rs));
			}
			return result;
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	/* DB에서 조회한 내용을 model 객체로 변환 */
	private Item convertItem(ResultSet rs) throws SQLException{
		return new Item(rs.getInt("comp_cd"),
				rs.getInt("plant_cd"),
				rs.getString("acct_id"),
				rs.getInt("item_cd"),
				rs.getString("item_nm"),
				rs.getString("item_spec"),
				rs.getString("item_spec2"),
				rs.getString("item_color"),
				rs.getString("cust_cd"),
				rs.getInt("acct_price"),
				rs.getString("currency"),
				rs.getString("unit_cd"),
				rs.getString("remark"),
				rs.getString("in_usr_id"),
				rs.getDate("in_date"),
				rs.getString("up_usr_id"),
				rs.getDate("up_date")
				);
			
	}
	
	/* 수정 기능 */
	public int update(Connection conn, String order_no, String order_status , Date delivery_dt, int order_qty, String remark) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update ordering set order_status = ?, delivery_dt = ?, order_qty = ?, remark = ? where order_no = ?")) {
			pstmt.setString(1, order_status);
			pstmt.setTimestamp(2, toTimestamp(delivery_dt));
			pstmt.setInt(3, order_qty);
			pstmt.setString(4, remark);
			pstmt.setString(5, order_no);
			return pstmt.executeUpdate();
		}
	}
	/* 삭제 기능 */
	public int delete(Connection conn, String item_cd) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("delete from item where item_cd = ?")) {
			pstmt.setString(1, item_cd);
			return pstmt.executeUpdate();
		}
	}

}
