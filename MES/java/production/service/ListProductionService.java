package production.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import production.dao.ProductionDao;
import production.model.Production;

public class ListProductionService {
	
	private ProductionDao productionDao = new ProductionDao();
	private int size = 10;
	
	public ProductionPage getProductionPage(int pageNum) {
		try (Connection conn =ConnectionProvider.getConnection()){
			int total = productionDao.selectCount(conn);
			List<Production> content = productionDao.select(conn, (pageNum-1)*size, size);
			return new ProductionPage(total, pageNum, size, content);
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
