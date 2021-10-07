package line.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import line.dao.LineDao;
import line.model.Line;


public class ModifyLineService {
	
	private LineDao lineDao = new LineDao();
	
	public Line loadData(String noVal) {
		Connection conn = null;
		Line line = new Line();
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			line = lineDao.selectByNo(conn, noVal);
			if(line == null) {
				throw new LineNotFountException();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (PermissionDeniedException e) {
			throw e;
		}
		finally {
			JdbcUtil.close(conn);
		}
		return line;
	}
	
	public void modify(ModifyLineRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Line line = lineDao.selectByNo(conn, modReq.getLine_cd());
			if(line == null) {
				throw new LineNotFountException();
			}
			//아래 if 문은 수정 필요 (권한 기능 추가와 함께)
			if(!canModify()) {
				throw new PermissionDeniedException();
			}
			lineDao.update(conn, modReq.getLine_cd(), modReq.getLine_nm(), modReq.getUse_yn(), modReq.getRemark(),
					modReq.getUp_usr_id(), modReq.getUp_date());
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
