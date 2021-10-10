package mainboard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mainboard.model.MainBoard;
import mainboard.service.BoardDetailService;
import member.command.CommandHandler;

public class BoardDetailHandler implements CommandHandler{

	private BoardDetailService boardDetailService = new BoardDetailService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int boardNum = Integer.parseInt(req.getParameter("boardnum"));
		String pageNo = req.getParameter("pageno");
		MainBoard board = boardDetailService.getBoard(boardNum);
		
		req.setAttribute("board", board);
		req.setAttribute("pageno", pageNo);

		User user = (User) req.getSession(false).getAttribute("authUser");
		
		return "//WEB-INF/view/mainboard/boardDetailForm.jsp";
	}

}
