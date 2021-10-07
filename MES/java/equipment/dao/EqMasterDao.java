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

import javax.lang.model.element.Element;

import equipment.model.EquimentMaster;
import jdbc.JdbcUtil;
import line.model.Line;

public class EqMasterDao {
	
	/* 주문내용 DB 입력을 위한 insert 메소드*/
	public EquimentMaster insert(Connection conn, EquimentMaster eqMaster) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into equipment_master"
					+ " (comp_cd, plant_cd, line_cd, equip_cd, index_cd, contents, grade, use_yn, in_usr_id, up_usr_id, in_date)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, eqMaster.getComp_cd());
			pstmt.setInt(2, eqMaster.getPlant_cd());
			pstmt.setString(3, eqMaster.getLine_cd());
			pstmt.setString(4, eqMaster.equip_cd());
			pstmt.setInt(5, eqMaster.index_cd());
			pstmt.setString(6, eqMaster.contents());
			pstmt.setString(7, eqMaster.grade());
			pstmt.setString(8, eqMaster.getUse_yn());
			pstmt.setString(9, eqMaster.in_usr_id());
			pstmt.setString(10, eqMaster.up_usr_id());
			pstmt.setTimestamp(11, toTimestamp(eqMaster.getIn_date()));
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return eqMaster;
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
			rs = stmt.executeQuery("select count(*) from equipment_master");
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
	public List<EquimentMaster> select (Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from (select row_number() over (order by line_cd desc) num, o.* from equipment_master o order by equip_cd desc) "
					+ "where num between ? and ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<EquimentMaster> result = new ArrayList<EquimentMaster>();
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
	
	public EquimentMaster selectByNo(Connection conn, String line_cd) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from equipment_master where line_cd = ?");
			pstmt.setString(1, line_cd);
			rs = pstmt.executeQuery();
			EquimentMaster eqMaster = new EquimentMaster();
			while(rs.next()) {
				eqMaster = convertEquipment(rs);
			}
			return eqMaster;
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	/*DB 에서 조회한 Order 를 Order 객체로 변환하는 메소드*/
	private EquimentMaster convertEquipment(ResultSet rs) throws SQLException{
		return new EquimentMaster(rs.getInt("comp_cd"),
				rs.getInt("plant_cd"),
				rs.getString("line_cd"),
				rs.getString("equip_cd"),
				rs.getInt("index_cd"),
				rs.getString("contents"),
				rs.getString("grade"),
				rs.getString("use_yn"),
				rs.getString("in_usr_id"),
				rs.getDate("in_date"),
				rs.getString("up_usr_id"),
				rs.getDate("up_date"));
			
	}
	

	/*Timestamp -> Date 변환 메소드*/
	/*
	 * private Date toDate(Timestamp timestamp) { if(timestamp != null) { return new
	 * Date(timestamp.getTime()); } else { return null; } }
	 */

}
