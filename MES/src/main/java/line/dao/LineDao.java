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
	

	/*Timestamp -> Date 변환 메소드*/
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

}
