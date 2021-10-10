package mainboard.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mainboard.service.BoardDeleteService;
import member.command.CommandHandler;

public class BoardDeleteHandler implements CommandHandler{

	private static String FORM_VIEW = "index.jsp";
	
	BoardDeleteService boardDeleteService = new BoardDeleteService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//삭제 되었는지 확인하는 isModifySuccess 변수
		boolean isDeleteSuccess = false;

		int boardNum = Integer.parseInt(req.getParameter("boardnum"));
		int pageNo = Integer.parseInt(req.getParameter("pageno"));
		
		//게시판 삭제 메소드
		isDeleteSuccess = boardDeleteService.deleteBoard(boardNum);
		
		if(isDeleteSuccess) {
			FORM_VIEW = "boardlist.do?pageno="+pageNo;
		} else {
			res.setContentType("text/html; charset=utf-8");
			PrintWriter out = res.getWriter();
			out.println("<script>");
			out.println("alert('게시글 삭제를 실패했습니다');");
			out.println("</script>");	
		}
		
		return FORM_VIEW;
	}

}
