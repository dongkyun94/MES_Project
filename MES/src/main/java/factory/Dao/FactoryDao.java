package factory.Dao;

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
	

	/*Timestamp -> Date 변환 메소드*/
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

}	

