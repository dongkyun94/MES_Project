package equipment.service;

import java.sql.Connection;
import java.sql.SQLException;

import equipment.dao.EqMasterDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import line.service.PermissionDeniedException;

public class DeleteEqMasterService {

	private EqMasterDao eqMasterDao = new EqMasterDao();
	
	public void delete(String equip_cd) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			eqMasterDao.delete(conn, equip_cd);
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			JdbcUtil.rollback(conn);
			throw e;
		}
		finally {
			JdbcUtil.close(conn);
		}
	}

}

