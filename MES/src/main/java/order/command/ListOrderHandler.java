package order.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.CommandHandler;
import order.service.ListOrderService;
import order.service.OrderPage;

public class ListOrderHandler implements CommandHandler{
	
	private ListOrderService listService = new ListOrderService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		OrderPage orderPage = listService.getOrderPage(pageNo);
		req.setAttribute("orderPage", orderPage);
		return "/WEB-INF/view/Order.jsp";
	}

}
