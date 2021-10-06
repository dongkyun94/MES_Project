package equipment.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import equipment.dao.EqMasterDao;
import equipment.model.EquimentMaster;
import jdbc.connection.ConnectionProvider;


public class ListEqMasterService {

	private EqMasterDao  equipmentDao = new EqMasterDao();
	private int size = 10;
	
	public EqMasterPage getEquipmentPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			int total = equipmentDao.selectCount(conn);
			List<EquimentMaster> content = equipmentDao.select(conn, (pageNum-1)*size, size);
			return new EqMasterPage(total, pageNum, size, content);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

