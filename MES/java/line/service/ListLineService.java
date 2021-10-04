package line.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import line.dao.LineDao;
import line.model.Line;

public class ListLineService {
	
	private LineDao  lineDao = new LineDao();
	private int size = 10;
	
	public LinePage getLinePage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()){
			int total = lineDao.selectCount(conn);
			List<Line> content = lineDao.select(conn, (pageNum-1)*size, size);
			return new LinePage(total, pageNum, size, content);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
