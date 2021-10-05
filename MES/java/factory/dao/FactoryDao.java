package factory.dao;

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
import factory.model.Factory;

public class FactoryDao {

	/* 주문내용 DB 입력을 위한 insert 메소드*/
	public Factory insert(Connection conn, Factory factory) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into factory"
					+ " (comp_cd, plant_cd, plant_nm, valid_fr_dt, valid_to_dt, remark, in_usr_id, in_date, up_usr_id )"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, factory.getComp_cd());
			pstmt.setInt(2, factory.getPlant_cd());
			pstmt.setString(3, factory.getPlant_nm());
			pstmt.setTimestamp(4, toTimestamp(factory.getValid_fr_dt()));
			pstmt.setTimestamp(5, toTimestamp(factory.getValid_to_dt()));
			pstmt.setString(6, factory.getRemark());
			pstmt.setString(7, factory.getIn_usr_id());
			pstmt.setTimestamp(8, toTimestamp(factory.getIn_date()));
			pstmt.setString(9, factory.getUp_usr_id());			
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return factory;
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
			rs = stmt.executeQuery("select count(*) from factory");
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
	public List<Factory> select (Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from (select row_number() over (order by plant_cd desc) num, o.* from factory o order by plant_cd desc) "
					+ "where num between ? and ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Factory> result = new ArrayList<Factory>();
			while(rs.next()) {
				result.add(convertFactory(rs));
			}
			return result;
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Factory selectByNo(Connection conn, int plant_cd) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from factory where plant_cd = ?");
			pstmt.setInt(1, plant_cd);
			rs = pstmt.executeQuery();
			Factory factory = new Factory();
			while(rs.next()) {
				factory = convertFactory(rs);
			}
			return factory;
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	/*DB 에서 조회한 Order 를 Order 객체로 변환하는 메소드*/
	private Factory convertFactory(ResultSet rs) throws SQLException{
		return new Factory(rs.getInt("comp_cd"),
				rs.getInt("plant_cd"),
				rs.getString("plant_nm"),
				rs.getDate("valid_fr_dt"),
				rs.getDate("valid_to_dt"),
				rs.getString("remark"),
				rs.getString("in_usr_id"),
				rs.getDate("in_date"),
				rs.getString("up_usr_id"),
				rs.getDate("up_date"));
			
	}
		

	/*Timestamp -> Date 변환 메소드*/
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	/* 주문  수정 기능 */
	public int update(Connection conn, int plant_cd, String plant_nm , Date valid_fr_dt, Date valid_to_dt, String up_usr_id) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update factorying set plant_nm = ?, valid_fr_dt = ?, valid_to_dt = ?, up_usr_id = ? where PLANT_CD = ?")) {
			pstmt.setString(1, plant_nm);
			pstmt.setTimestamp(2, toTimestamp(valid_fr_dt));
			pstmt.setTimestamp(3, toTimestamp(valid_to_dt));
			pstmt.setString(4,up_usr_id);
			pstmt.setInt(5, plant_cd);
			return pstmt.executeUpdate();
		}
	}
	/* 주문 삭제 기능 */
	public int delete(Connection conn, int plant_cd) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("delete from factorying where plant_cd = ?")) {
			pstmt.setInt(1, plant_cd);
			return pstmt.executeUpdate();
		}
	}

}

