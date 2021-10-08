package item.service;

import java.sql.Connection;
import java.sql.SQLException;

import item.dao.ItemDao;
import item.model.Item;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;

public class ModifyItemService {
	
	private ItemDao itemDao = new ItemDao();
	
	public Item loadData(int noVal) {
		Connection conn = null;
		Item item = new Item();
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			item = itemDao.selectByNo(conn, noVal);
			if(item == null) {
				throw new ItemNotFoundException();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		return item;
	}
	
	public void modify(ModifyItemRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Item item = itemDao.selectByNo(conn, modReq.getItem_cd());
			if(item == null) {
				throw new ItemNotFoundException();
			}
			itemDao.update(conn, modReq.getItem_cd(), modReq.getAcct_id(), 
					modReq.getItem_nm(), modReq.getItem_spec(), modReq.getItem_spec2(), 
					modReq.getItem_color(), modReq.getCust_cd(), modReq.getAcct_price(), 
					modReq.getCurrency(), modReq.getUnit_cd(), modReq.getRemark(), 
					modReq.getUp_usr_id(), modReq.getUp_date());
			conn.commit();
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
	}
}