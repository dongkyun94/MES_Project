package equipment.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import equipment.dao.EquipmentDao;
import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import line.model.Line;
import line.service.LineInsertRequest;
import equipment.model.EquimentMaster;
import equipment.service.EqMasterInsertRequest;


public class EqMasterInsertService {

	private EquipmentDao equipmentDao = new EquipmentDao();
	
	public String insert(EqMasterInsertRequest req) {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			EquimentMaster equipment = toEquipment(req);
			EquimentMaster savedEquipment = equipmentDao.insert(conn, equipment);
			if (savedEquipment == null) {
				throw new RuntimeException("fail to insert equipment");
			}
			
			conn.commit();
			
			return savedEquipment.getLine_cd();
		}catch (SQLException e) {
			JdbcUtil.rollback(conn);
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (ParseException e) {
			JdbcUtil.rollback(conn);
			System.out.println(e.getMessage());
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			JdbcUtil.rollback(conn);
			throw e;
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private static EquimentMaster toEquipment(EqMasterInsertRequest req) throws ParseException{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		return new EquimentMaster(req.getComp_cd(), req.getPlant_cd(), req.getLine_cd(), req.getequip_cd(), 
				req.getIndex_cd(), req.getContents(), req.getGrade(), req.getUse_yn(), req.getIn_usr_id(),sdf1.parse( req.getIn_date().toString()), 
				null, null);
		
	}

}

	


