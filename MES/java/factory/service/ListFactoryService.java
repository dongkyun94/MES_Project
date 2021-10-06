package factory.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import factory.dao.FactoryDao;
import factory.model.Factory;

public class ListFactoryService {
	
	private FactoryDao factoryDao = new FactoryDao();
	private int size = 10;
	
	public FactoryPage getFactoryPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			int total = factoryDao.selectCount(conn);
			List<Factory> content = factoryDao.select(conn, (pageNum-1)*size, size);
			return new FactoryPage(total, pageNum, size, content);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
