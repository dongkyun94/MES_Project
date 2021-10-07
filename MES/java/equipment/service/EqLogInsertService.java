package equipment.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import equipment.dao.EqLogDao;
import equipment.dao.EqMasterDao;
import equipment.model.EquimentLog;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class EqLogInsertService {

private EqLogDao eqLogDao = new EqLogDao();
	
	public String insert(EqLogInsertRequest req) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			EquimentLog eqLog = toeqLog(req);
			EquimentLog savedEqLog = eqLogDao.insert(conn, eqLog);
			if (savedEqLog == null) {
				throw new RuntimeException("fail to insert eqLog");
			}
			
			conn.commit();
			
			return savedEqLog.getLine_cd();
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

	private static EquimentLog toeqLog(EqLogInsertRequest req) throws ParseException{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return new EquimentLog(req.getComp_cd(), req.getPlant_cd(), req.getLine_cd(), req.getequip_cd(), 
				req.getIndex_cd(), 
				sdf1.parse(req.getStart_time().toString()), sdf1.parse( req.getEnd_time().toString()));
		
	}

}
