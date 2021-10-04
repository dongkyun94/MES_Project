package factory.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.service.FactoryPage;
import factory.service.ListFactoryService;
import member.command.CommandHandler;
import order.service.ListOrderService;
import order.service.OrderPage;

public class ListFactoryHandler implements CommandHandler{
	
	private ListFactoryService listService = new ListFactoryService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		FactoryPage factoryPage = listService.getFactoryPage(pageNo);
		req.setAttribute("factoryPage", factoryPage);
		return "/WEB-INF/view/Factory.jsp";
	}

}
