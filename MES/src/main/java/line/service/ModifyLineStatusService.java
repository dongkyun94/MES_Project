package line.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import line.dao.LineDao;
import line.model.Line;

public class ModifyLineStatusService  {
	
	private LineDao lineDao = new LineDao();
	
	public void modify(ModifyStatusRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Line line = lineDao.selectByNo(conn, modReq.getLine_cd());
			if(line == null) {
				throw new LineNotFountException();
			}
			//아래 if 문은 수정 필요 (권한 기능 추가와 함께)

			lineDao.updateStatus(conn, modReq.getLine_cd(), modReq.getUse_yn());
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
