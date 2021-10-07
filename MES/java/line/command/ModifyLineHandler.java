package line.command;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import line.model.Line;
import line.service.LineNotFountException;
import line.service.ModifyLineRequest;
import line.service.ModifyLineService;
import member.command.CommandHandler;
import order.service.PermissionDeniedException;

public class ModifyLineHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/LineModify.jsp";
	
	private ModifyLineService modifyService = new ModifyLineService();

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
			Line loadData = modifyService.loadData(noVal);
			User user = (User) req.getSession().getAttribute("authUser");
			
			if(!canModify(user)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return FORM_VIEW;
			}
			ModifyLineRequest modReq = new ModifyLineRequest(noVal, loadData.getLine_nm(), loadData.getUse_yn(),
					loadData.getRemark(), loadData.getUp_usr_id(), loadData.getUp_date());
			req.setAttribute("linedata", loadData);
			req.setAttribute("modReq", modReq);
			return FORM_VIEW;
		}  catch (LineNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}
	
	private boolean canModify(User user) {
		return true;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		User user = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("line_cd");
		String nmVal = req.getParameter("line_nm");
		String ynVal = req.getParameter("use_yn");
		String remarkVal = req.getParameter("remark");
		String id = user.getId();
		Date today = new Date();
		
		ModifyLineRequest modReq = new ModifyLineRequest(noVal, nmVal, ynVal, remarkVal, id, today);
		req.setAttribute("modReq", modReq);
		
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "linelist.do";
		} catch (LineNotFountException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
