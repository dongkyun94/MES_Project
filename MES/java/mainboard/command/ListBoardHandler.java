package mainboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainboard.model.PageInfo;
import mainboard.service.ListBoardService;
import member.command.CommandHandler;

public class ListBoardHandler implements CommandHandler{

	private ListBoardService listBoardService = new ListBoardService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		 
		String pageNo = req.getParameter("pageno");
		
		int page = 1;
		int limit = 10;
		if(pageNo != null) { page = Integer.parseInt(pageNo); }
		
		PageInfo boardlist = listBoardService.getBoardList(page, limit);
		
		req.setAttribute("boardlist", boardlist);
		
		return "/WEB-INF/view/mainboard/boardListForm.jsp";
		
	}

}
