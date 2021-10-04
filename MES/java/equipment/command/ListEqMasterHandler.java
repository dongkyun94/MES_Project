package equipment.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import equipment.service.EqMasterPage;
import equipment.service.ListEqMasterService;
import member.command.CommandHandler;


public class ListEqMasterHandler implements CommandHandler{

	private ListEqMasterService listService = new ListEqMasterService();

	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if(pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		EqMasterPage equipmentPage = listService.getEquipmentPage(pageNo);
		req.setAttribute("equipmentPage", equipmentPage);
		return "/WEB-INF/view/EquipMaster.jsp";
	}

}
