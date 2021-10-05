package line.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.command.CommandHandler;
import member.model.Member;
import line.service.ModifyLineStatusService;
import line.service.ModifyStatusRequest;
import line.service.LineNotFountException;
import line.service.PermissionDeniedException;

public class ModifyLineStatusHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/modifyForm.jsp";
	
	private ModifyLineStatusService modifyService = new ModifyLineStatusService();

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

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		try {
			String cdVal = req.getParameter("line_cd");
			String ynVal = req.getParameter("use_yn");
			Member member = (Member) req.getSession().getAttribute("authUser");
			if(!canModify(member)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}
			ModifyStatusRequest modReq = new ModifyStatusRequest(cdVal, ynVal);
			
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		}  catch (LineNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(Member member) {
		return true;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		Member member = (Member) req.getSession().getAttribute("authUser");
		String cdVal = req.getParameter("line_cd");
		String ynVal = req.getParameter("use_yn");
		
		ModifyStatusRequest modReq = new ModifyStatusRequest(cdVal, ynVal);
		req.setAttribute("modReq", modReq);
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "/WEB-INF/view/modifySuccess.jsp";
		} catch (LineNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
