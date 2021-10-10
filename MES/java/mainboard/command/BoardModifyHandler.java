package mainboard.command;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import auth.service.User;
import mainboard.model.MainBoard;
import mainboard.service.BoardDetailService;
import mainboard.service.BoardInsertRequest;
import mainboard.service.BoardModifyService;
import member.command.CommandHandler;

public class BoardModifyHandler implements CommandHandler{

	//FORM_VIEW : 기본적으로 가야하는 페이지 주소
	private static final String FORM_VIEW = "/WEB-INF/view/mainboard/boardModifyForm.jsp";
	
	BoardDetailService boardDetailService = new BoardDetailService();
	BoardModifyService boardModifyService = new BoardModifyService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//GET방식으로 들어왔을 경우				
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		//POST방식으로 들어왔을 경우
		}else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		//비정상적인 요청
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	
	}
	
	//수정 버튼을 눌렀을 때
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		   
		int boardNum = Integer.parseInt(req.getParameter("boardnum"));
		String pageNo = req.getParameter("pageno");
		
		MainBoard board = boardDetailService.getBoard(boardNum);
		
		req.setAttribute("board", board);
		req.setAttribute("pageno", pageNo);
		
		return "//WEB-INF/view/mainboard/boardModifyForm.jsp";
	}

	//POST
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{

		/*1. 에러 생성*/		
		//Map 형식으로 에러를 생성함
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		/*2. 다운로드 파일 데이터 생성*/	
		//다운될 파일 경로
		String saveFolder = "D:\\MainProject\\MES\\WebContent\\WEB-INF\\download";
		//파일사이즈
		int filesize = 1024 * 1024 * 5;
		MultipartRequest multi = new MultipartRequest(req, saveFolder, filesize, "utf-8", new DefaultFileRenamePolicy());
		
		User user = (User) req.getSession(false).getAttribute("authUser");
		
		/*3. 받은 데이터로 게시판 생성*/
		BoardInsertRequest boardInsertReq = createBoardInsertRequest(multi, user);
		boardInsertReq.validate(errors);

		//필수 입력사항이 입력되지 않았다면 에러발생
		if(!errors.isEmpty()) { return FORM_VIEW; }
				
		//수정되었는지 확인하는 isModifySuccess 변수
		Boolean isModifySuccess = false;
		
		int boardNum = Integer.parseInt(req.getParameter("boardnum"));
		String pageNo = req.getParameter("pageno");
		
		/*4. 게시판 데이터베이스 입력*/	
		isModifySuccess = boardModifyService.modifyBoard(boardInsertReq, boardNum);
		
		 //수정 성공하면 
		if(isModifySuccess) {
		 	return "boarddetail.do?boardnum=" + boardNum + "&pageno=" + pageNo;
	 	//수정 실패하면	
	 	} else { 
	 		res.setContentType("text/html; charset=utf-8"); 
	 		PrintWriter	out = res.getWriter(); out.println("<script>");
	 		out.println("alert('수정 실패했습니다');"); 
	 		out.println("</script>"); return null; }
		 
	}

	//전달받은 내용을 기반으로 객체를 만듦
	private BoardInsertRequest createBoardInsertRequest(MultipartRequest multi, User user) throws Exception{
		
		return new BoardInsertRequest(
				multi.getParameter("boardTitle"), 
				multi.getParameter("boardContent"),
				user.getId(),
				multi.getOriginalFileName((String) multi.getFileNames().nextElement())
				); 
	}
	
}
