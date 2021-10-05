package factory.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import factory.dao.FactoryDao;
import factory.model.Factory;

public class ModifyFactoryService {
	
	private FactoryDao factoryDao = new FactoryDao();
	
	public Factory loadData(int noVal) {
		Connection conn = null;
		Factory factory = new Factory();
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			factory = factoryDao.selectByNo(conn, noVal);
			if(factory == null) {
				throw new FactoryNotFountException();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			throw e;
		}
		finally {
			JdbcUtil.close(conn);
		}
		return factory;
	}
	
	public void modify(ModifyFactoryRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Factory factory = factoryDao.selectByNo(conn, modReq.getPlant_cd());
			if(factory == null) {
				throw new FactoryNotFountException();
			}
			//아래 if 문은 수정 필요 (권한 기능 추가와 함께)
			if(!canModify()) {
				throw new PermissionDeniedException();
			}
			factoryDao.update(conn, modReq.getPlant_cd(), modReq.getPlant_nm(), modReq.getValid_fr_dt(), modReq.getValid_to_dt(),modReq.getUp_usr_id());
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
