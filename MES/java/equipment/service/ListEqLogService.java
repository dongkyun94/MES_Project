package equipment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import equipment.dao.EqLogDao;
import equipment.dao.EqMasterDao;
import equipment.model.EquimentLog;
import jdbc.connection.ConnectionProvider;

public class ListEqLogService {

	private EqLogDao  eqLogDao = new EqLogDao();
	private int size = 10;
	
	public EqLogPage getEquipmentPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			int total = eqLogDao.selectCount(conn);
			List<EquimentLog> content = eqLogDao.select(conn, (pageNum-1)*size, size);
			return new EqLogPage(total, pageNum, size, content);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
