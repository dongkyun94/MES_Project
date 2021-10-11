package equipment.command;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import equipment.model.EquimentMaster;
import equipment.service.EqMasterNotFountException;
import equipment.service.ModifyEqMasterRequest;
import equipment.service.ModifyEqMasterService;
import member.command.CommandHandler;
import order.service.PermissionDeniedException;

public class ModifyEqMasterHandler implements CommandHandler{

private static final String FORM_VIEW = "/WEB-INF/view/EqMasterModify.jsp";
	
	private ModifyEqMasterService modifyService = new ModifyEqMasterService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
		
		try {
			String noVal = req.getParameter("no");
			EquimentMaster loadData = modifyService.loadData(noVal);
			User user = (User) req.getSession().getAttribute("authUser");
			
			if(!canModify(user)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return FORM_VIEW;
			}
			ModifyEqMasterRequest modReq = new ModifyEqMasterRequest(noVal, loadData.contents(), loadData.getUse_yn());
			req.setAttribute("EqMasterdata", loadData);
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		}  catch (EqMasterNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(User user) {
		return true;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		User user = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("equip_cd");
		String nmVal = req.getParameter("contents");
		String ynVal = req.getParameter("use_yn");
		
		ModifyEqMasterRequest modReq = new ModifyEqMasterRequest(noVal, nmVal, ynVal);
		req.setAttribute("modReq", modReq);
		
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "eqMasterlist.do";
		} catch (EqMasterNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}

