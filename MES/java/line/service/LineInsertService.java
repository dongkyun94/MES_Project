package line.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import line.dao.LineDao;
import line.model.Line;

public class LineInsertService {
	
	private LineDao lineDao = new LineDao();
	
	public String insert(LineInsertRequest req) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Line line = toLine(req);
			Line savedLine = lineDao.insert(conn, line);
			if (savedLine == null) {
				throw new RuntimeException("fail to insert line");
			}
			
			conn.commit();
			
			return savedLine.getLine_cd();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (ParseException e) {
			JdbcUtil.rollback(conn);
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private Line toLine(LineInsertRequest req) throws ParseException{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		return new Line(req.getComp_cd(), req.getPlant_cd(), req.getLine_cd(), req.getLine_nm(), req.getUse_yn(), sdf1.parse(req.getIn_date()), null);
	}

}
