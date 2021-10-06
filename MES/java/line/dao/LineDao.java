package line.dao;

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
import line.model.Line;
import order.model.Order;

public class LineDao {
	
	/* 주문내용 DB 입력을 위한 insert 메소드*/
	public Line insert(Connection conn, Line line) throws SQLException {
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("insert into line"
					+ " (comp_cd, plant_cd, line_cd, line_nm, use_yn, in_date)"
					+ " values (?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, line.getComp_cd());
			pstmt.setInt(2, line.getPlant_cd());
			pstmt.setString(3, line.getLine_cd());
			pstmt.setString(4, line.getLine_nm());
			pstmt.setString(5, line.getUse_yn());
			pstmt.setTimestamp(6, toTimestamp(line.getIn_date()));
			int insertedCount = pstmt.executeUpdate();
			
			if(insertedCount > 0) {
				return line;
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
			rs = stmt.executeQuery("select count(*) from line");
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
	public List<Line> select (Connection conn, int startRow, int size) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from (select row_number() over (order by line_cd desc) num, o.* from line o order by line_cd desc) "
					+ "where num between ? and ?");
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			List<Line> result = new ArrayList<Line>();
			while(rs.next()) {
				result.add(convertLine(rs));
			}
			return result;
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public Line selectByNo(Connection conn, String line_cd) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement("select * from line where line_cd = ?");
			pstmt.setString(1, line_cd);
			rs = pstmt.executeQuery();
			Line line = new Line();
			while(rs.next()) {
				line = convertLine(rs);
			}
			return line;
		}
		finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	/*DB 에서 조회한 Order 를 Order 객체로 변환하는 메소드*/
	private Line convertLine(ResultSet rs) throws SQLException{
		return new Line(rs.getInt("comp_cd"),
				rs.getInt("plant_cd"),
				rs.getString("line_cd"),
				rs.getString("line_nm"),
				rs.getString("use_yn"),
				rs.getDate("in_date"),
				rs.getDate("up_date"));
			
	}
	

	/*Timestamp -> Date 변환 메소드*/
	/*
	 * private Date toDate(Timestamp timestamp) { if(timestamp != null) { return new
	 * Date(timestamp.getTime()); } else { return null; } }
	 */

	/* 주문 수정 기능*/
	public int update(Connection conn, String line_cd, String line_nm,String use_yn) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("update LINE set line_nm = ?, use_yn = ? where line_cd = ?")) {
			pstmt.setString(1, line_nm);
			pstmt.setString(2, use_yn);
			pstmt.setString(3, line_cd);
			return pstmt.executeUpdate();
		}
	}
	/* 주문 삭제 기능 */
	public int delete(Connection conn, String line_cd) throws SQLException {
		try (PreparedStatement pstmt = conn
				.prepareStatement("delete from LINE where line_cd = ?")) {
			pstmt.setString(1, line_cd);
			return pstmt.executeUpdate();
		}
	}

}
