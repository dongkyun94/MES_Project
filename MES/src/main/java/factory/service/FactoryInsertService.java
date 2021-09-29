package factory.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import factory.Dao.FactoryDao;
import factory.model.Factory;

public class FactoryInsertService {

	private FactoryDao factoryDao = new FactoryDao();
	
	public int insert(FactoryInsertRequest req) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Factory factory = toFactory(req);
			Factory savedFactory = factoryDao.insert(conn, factory);
			if (savedFactory == null) {
				throw new RuntimeException("fail to insert factory");
			}
			
			conn.commit();
			
			return savedFactory.getPlant_cd();
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

	private Factory toFactory(FactoryInsertRequest req) throws ParseException{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		return new Factory(req.getComp_cd(), req.getPlant_cd(), req.getPlant_nm(),
	            sdf1.parse(req.getValid_fr_dt()), sdf1.parse(req.getValid_to_dt()), req.getRemark(), null, sdf1.parse(req.getIn_date()), null, null);
	}
}
