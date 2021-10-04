package equipment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import equipment.model.EquimentLog;
import jdbc.JdbcUtil;

public class EqLogDao {

	/* 주문내용 DB 입력을 위한 insert 메소드*/
	public EquimentLog insert(Connection conn, EquimentLog equipment) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into equipment_log"
					+ " (comp_cd, plant_cd, line_cd, equip_cd, index_cd, start_time, end_time)"
					+ " values (?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, equipment.getComp_cd());
			pstmt.setInt(2, equipment.getPlant_cd());
			pstmt.setString(3, equipment.getLine_cd());
			pstmt.setString(4, equipment.equip_cd());
			pstmt.setInt(5, equipment.index_cd());
			pstmt.setTimestamp(6, toTimestamp(equipment.getIn_date()));
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return equipment;
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
			rs = stmt.executeQuery("select count(*) from equipment_log");
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
	public List<EquimentLog> select (Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from (select row_number() over (order by line_cd desc) num, o.* from equipment_log o order by equip_cd desc) "
					+ "where num between ? and ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<EquimentLog> result = new ArrayList<EquimentLog>();
			while(rs.next()) {
				result.add(convertEquipment(rs));
			}
			return result;
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public EquimentLog selectByNo(Connection conn, String line_cd) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from equipment_log where line_cd = ?");
			pstmt.setString(1, line_cd);
			rs = pstmt.executeQuery();
			EquimentLog equipment = new EquimentLog();
			while(rs.next()) {
				equipment = convertEquipment(rs);
			}
			return equipment;
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	/*DB 에서 조회한 Order 를 Order 객체로 변환하는 메소드*/
	private EquimentLog convertEquipment(ResultSet rs) throws SQLException{
		return new EquimentLog(rs.getInt("comp_cd"),
				rs.getInt("plant_cd"),
				rs.getString("line_cd"),
				rs.getString("equip_cd"),
				rs.getInt("index_cd"),
				rs.getDate("start_time"),
				rs.getDate("end_time"));
			
	}
	

	/*Timestamp -> Date 변환 메소드*/
	/*
	 * private Date toDate(Timestamp timestamp) { if(timestamp != null) { return new
	 * Date(timestamp.getTime()); } else { return null; } }
	 */

}

