package production.dao;

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
import production.model.Production;

public class ProductionDao {
	/* 주문내용 DB 입력을 위한 insert 메소드 */
	public Production insert(Connection conn, Production production) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into production"
					+ "(comp_cd, plant_cd, order_no, wo_no, line_cd, equip_cd, start_dt, start_shift, end_dt, end_shift, flag_end, plan_qty, remark)"
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, production.getComp_cd());
			pstmt.setInt(2, production.getPlant_cd());
			pstmt.setString(3, production.getOrder_no());
			pstmt.setString(4, production.getWo_no());
			pstmt.setString(5, production.getLine_cd());
			pstmt.setString(6, production.getEquip_cd());
			pstmt.setTimestamp(7, toTimestamp(production.getStart_dt()));
			pstmt.setString(8, production.getStart_shift());
			pstmt.setTimestamp(9, toTimestamp(production.getEnd_dt()));
			pstmt.setString(10, production.getEnd_shift());
			pstmt.setString(11, production.getFlag_end());
			pstmt.setInt(12, production.getPlan_qty());
			pstmt.setString(13, production.getRemark());
			int insertedCount = pstmt.executeUpdate();

			if (insertedCount > 0) {
				return production;
			} else {
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

	/* DB에 입력되어 있는  갯수 조회 메소드 */
	public int selectCount(Connection conn) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from production");
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}

	/* DB order 리스트 조회 메소드 */
	public List<Production> select(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from (select row_number() over (order by order_no desc) num, o.* from production o order by wo_no desc) "
							+ "where num between ? and ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Production> result = new ArrayList<Production>();
			while (rs.next()) {
				result.add(convertProduction(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Production selectByNo(Connection conn, String wo_no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from production where wo_no = ?");
			pstmt.setString(1, wo_no);
			rs = pstmt.executeQuery();
			Production production = new Production();
			while (rs.next()) {
				production = convertProduction(rs);
			}
			return production;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	/* DB 에서 조회한 데이터를 객체로 변환하는 메소드 */
	private Production convertProduction(ResultSet rs) throws SQLException {
		return new Production(rs.getInt("comp_cd"), rs.getInt("plant_cd"), rs.getString("order_no"), rs.getString("wo_no"), 
				rs.getString("line_cd"), rs.getString("equip_cd"), rs.getDate("start_dt"), rs.getString("start_shift"), 
				rs.getDate("end_dt"), rs.getString("end_shift"), rs.getString("flag_end"), rs.getInt("plan_qty"),rs.getString("remark"));
	}

	/* Timestamp -> Date 변환 메소드 */
	/*
	 * private Date toDate(Timestamp timestamp) { return new
	 * Date(timestamp.toString()); }
	 */

	/* 수정 기능 */
	public int update(Connection conn, String wo_no, String line_cd , String equip_cd, Date start_dt, String start_shift, 
			Date end_dt, String end_shift, String flag_end, int plan_qty, String remark) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update production set line_cd = ?, equip_cd = ?, start_dt = ?, start_shift = ?, end_dt = ?, end_shift = ?, flag_end = ?, plan_qty = ?, remark = ? where wo_no = ?")) {
			pstmt.setString(1, line_cd);
			pstmt.setString(1, equip_cd);
			pstmt.setTimestamp(2, toTimestamp(start_dt));
			pstmt.setString(1, start_shift);
			pstmt.setTimestamp(2, toTimestamp(end_dt));
			pstmt.setString(1, end_shift);
			pstmt.setString(1, flag_end);
			pstmt.setInt(3, plan_qty);
			pstmt.setString(4, remark);
			pstmt.setString(5, wo_no);
			return pstmt.executeUpdate();
		}
	}
	/* 삭제 기능 */
	public int delete(Connection conn, String wo_no) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("delete from production where wo_no = ?")) {
			pstmt.setString(1, wo_no);
			return pstmt.executeUpdate();
		}
	}

}
