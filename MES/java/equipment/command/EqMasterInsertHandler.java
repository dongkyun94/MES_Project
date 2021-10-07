package equipment.command;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import equipment.service.EqMasterInsertRequest;
import equipment.service.EqMasterInsertService;
import member.command.CommandHandler;

public class EqMasterInsertHandler implements CommandHandler {

	private static final String FORM_VIEW = "/WEB-INF/view/newEquipMaster.jsp";
	private EqMasterInsertService equipMasterInsertService = new EqMasterInsertService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		EqMasterInsertRequest equipMasterInsertReq = creatEquipmentInsertRequest(req, user);
		equipMasterInsertReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		String newEquipmentNo = equipMasterInsertService.insert(equipMasterInsertReq);
		req.setAttribute("newEquipmentNo", newEquipmentNo);
		return "eqMasterlist.do";
	
	}

	private EqMasterInsertRequest creatEquipmentInsertRequest(HttpServletRequest req, User user) {
		Date today = new Date();
		return new EqMasterInsertRequest(
				Integer.parseInt(req.getParameter("comp_cd")), Integer.parseInt(req.getParameter("plant_cd")), 
				req.getParameter("line_cd"), req.getParameter("equip_cd"), Integer.parseInt(req.getParameter("index_cd")), 
				req.getParameter("contents"), req.getParameter("grade"), req.getParameter("use_yn"), user.getId(), 
				today);
				
	}


	
}
