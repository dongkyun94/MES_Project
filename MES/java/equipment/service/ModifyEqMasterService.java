package equipment.service;

import java.sql.Connection;
import java.sql.SQLException;

import equipment.dao.EqMasterDao;
import equipment.model.EquimentMaster;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;


public class ModifyEqMasterService {

private EqMasterDao eqMasterDao = new EqMasterDao();
	
	public EquimentMaster loadData(String noVal) {
		Connection conn = null;
		EquimentMaster equimentMaster = new EquimentMaster();
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			equimentMaster = eqMasterDao.selectByNo(conn, noVal);
			if(equimentMaster == null) {
				throw new EqMasterNotFountException();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			throw e;
		}
		finally {
			JdbcUtil.close(conn);
		}
		return equimentMaster;
	}
	
	public void modify(ModifyEqMasterRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			EquimentMaster equimentMaster = eqMasterDao.selectByNo(conn, modReq.getEquip_cd());
			if(equimentMaster == null) {
				throw new EqMasterNotFountException();
			}
			//아래 if 문은 수정 필요 (권한 기능 추가와 함께)
			if(!canModify()) {
				throw new PermissionDeniedException();
			}
			eqMasterDao.update(conn, modReq.getEquip_cd(), modReq.getContents(), modReq.getUse_yn());
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

