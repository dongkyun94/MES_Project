package production.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import production.dao.ProductionDao;
import production.model.Production;

public class ProductionInsertService {
	
	private ProductionDao productionDao = new ProductionDao();
	
	public String insert(ProductionInsertRequest req) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			Production production = toProduction(req);
			Production savedProduction = productionDao.insert(conn, production);
			if(production == null) {
				throw new RuntimeException("fail to insert production");
			}
			
			conn.commit();
			
			return savedProduction.getWo_no();
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
	
	private Production toProduction(ProductionInsertRequest req) throws ParseException {
		Connection conn = null;
		ResultSet rs = null;
		Statement stmt = null;
		String idx = "";
		try {
			conn = ConnectionProvider.getConnection();
			String sql = "select idx_wo_no.nextval seq_num from dual";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			idx = rs.getString("seq_num");
		}catch (SQLException e) {
			
		}
		JdbcUtil.close(conn,stmt,rs);
		
		Date toDay = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String first = sdf.format(toDay);
		String wo_no = "W"+first+ idx;
		return new Production(req.getComp_cd(), req.getPlant_cd(), req.getOrder_no(), wo_no, req.getLine_cd(), req.getEquip_cd(), sdf1.parse(req.getStart_dt()), req.getStart_shift(), sdf1.parse(req.getEnd_dt()), req.getEnd_shift(), req.getFlag_end(), req.getPlan_qty(), req.getRemark());
	}

}
