package item.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ItemInsertService {
	
	private ItemDao itemDao = new ItemDao();
	
	public int insert(ItemInsertRequest req) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Item item = toItem(req);
			Item savedItem = itemDao.insert(conn, item);
			if(savedItem == null) {
				throw new RuntimeException("fail to insert item");
			}
			
			conn.commit();
			
			return savedItem.getItem_cd();
		}
		catch (SQLException e) {
			JdbcUtil.rollback(conn);
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		}catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	private Item toItem(ItemInsertRequest req) {
		Date today = new Date();
		return  new Item(req.getComp_cd(), req.getPlant_cd(),req.getAcct_id(), req.getItem_cd(), req.getItem_nm(), req.getItem_spec(), req.getItem_spec2()
				, req.getItem_color(), req.getCust_cd(), req.getAcct_price(), req.getCurrency(), req.getUnit_cd(), req.getRemark(), req.getIn_usr_id(), today, null, null);
	}

}
