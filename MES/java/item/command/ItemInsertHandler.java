package item.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import item.service.ItemInsertRequest;
import item.service.ItemInsertService;
import member.command.CommandHandler;

public class ItemInsertHandler implements CommandHandler{
	private static final String FORM_VIEW = "itemlist.do";
	private ItemInsertService itemInsertService = new ItemInsertService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		}
		else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		}
		else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		ItemInsertRequest itemInsertReq = createItemInsertRequest(req);
		itemInsertReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		int newItemNo = itemInsertService.insert(itemInsertReq);
		req.setAttribute("newItemNo", newItemNo);
		return "itemlist.do";
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		return FORM_VIEW;
	}
	
	private ItemInsertRequest createItemInsertRequest(HttpServletRequest req) {
		User user =(User) req.getSession(false).getAttribute("authUser");
		return new ItemInsertRequest(Integer.parseInt(req.getParameter("comp_cd")), Integer.parseInt(req.getParameter("plant_cd")), req.getParameter("acct_id"), Integer.parseInt(req.getParameter("item_cd")), req.getParameter("item_nm"), req.getParameter("item_spec"), req.getParameter("item_spec2"), 
				req.getParameter("item_color"), req.getParameter("cust_cd"), Integer.parseInt(req.getParameter("acct_price")), req.getParameter("currency"), req.getParameter("unit_cd"), req.getParameter("remark"), user.getId());
	}

}
