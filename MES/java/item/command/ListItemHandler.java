package item.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.service.ItemPage;
import item.service.ListItemService;
import member.command.CommandHandler;

public class ListItemHandler implements CommandHandler{
	
	private ListItemService listService = new ListItemService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		ItemPage itemPage = listService.getItemPage(pageNo);
		req.setAttribute("itemPage", itemPage);
		return "/WEB-INF/view/Item.jsp";
	} 

}
