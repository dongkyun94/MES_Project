package line.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import line.service.LinePage;
import line.service.ListLineService;
import member.command.CommandHandler;
import order.service.ListOrderService;
import order.service.OrderPage;

public class ListLineHandler implements CommandHandler{
	
	private ListLineService listService = new ListLineService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		LinePage linePage = listService.getLinePage(pageNo);
		req.setAttribute("linePage", linePage);
		return "/WEB-INF/view/Line.jsp";
	}

}
