package order.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import order.dao.OrderDao;
import order.model.Order;

public class ModifyOrderStatusService {
	
	private OrderDao orderDao = new OrderDao();
	
	public void modify(ModifyStatusRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Order order = orderDao.selectByNo(conn, modReq.getOrder_no());
			if(order == null) {
				throw new OrderNotFountException();
			}
			//아래 if 문은 수정 필요 (권한 기능 추가와 함께)
			if(!canModify()) {
				throw new PermissionDeniedException();
			}
			orderDao.updateStatus(conn, modReq.getOrder_no(), modReq.getOrder_status());
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
	
	private boolean canModify() {
		return true;
	}

}
