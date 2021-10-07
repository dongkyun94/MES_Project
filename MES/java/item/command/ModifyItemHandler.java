package item.command;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import item.service.ItemNotFoundException;
import item.service.ModifyItemRequest;
import item.service.ModifyItemService;
import member.command.CommandHandler;
import order.service.PermissionDeniedException;

public class ModifyItemHandler implements CommandHandler{
	
	private static final String FORM_VIEW = "/WEB-INF/view/modifyOrderModal.jsp";
	
	private ModifyItemService modifyService = new ModifyItemService();

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
	// get 방식의 접근은 없습니다.
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException, ParseException {
		
		return null;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		User user = (User) req.getSession().getAttribute("authUser");
		
		int item_cd = Integer.parseInt(req.getParameter("item_cd"));
		String acct_id = req.getParameter("acct_id");
		String item_nm = req.getParameter("item_nm");
		String item_spec = req.getParameter("item_spec");
		String item_spec2 = req.getParameter("item_spec2");
		String item_color = req.getParameter("item_color");
		int acct_price = Integer.parseInt(req.getParameter("acct_price"));
		String currency = req.getParameter("currency");
		String unit_cd = req.getParameter("unit_cd");
		String remark = req.getParameter("remark");
		String up_usr_id = user.getId();
		Date up_date = new Date();
		
		ModifyItemRequest modReq = new ModifyItemRequest(item_cd, acct_id, item_nm, item_spec, item_spec2, 
				item_color, acct_price, currency, unit_cd, remark, up_usr_id, up_date);
		//req.setAttribute("modReq", modReq);
		
		
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		try {
			modifyService.modify(modReq);
			return "itemlist.do";
		} catch (ItemNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
	}

}
