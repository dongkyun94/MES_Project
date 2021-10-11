package production.command;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import member.command.CommandHandler;
import order.dao.OrderDao;
import production.service.ListProductionService;
import production.service.ProductionPage;

public class ListProductionHandler implements CommandHandler {
	
	private OrderDao orderDao = new OrderDao();
	private ListProductionService listService = new ListProductionService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		ProductionPage productionPage = listService.getProductionPage(pageNo);
		req.setAttribute("productionPage", productionPage);
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			List<String> orderNoList = orderDao.selectOrderNo(conn);
			req.setAttribute("orderNoList", orderNoList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/WEB-INF/view/Production.jsp";
	}

}
