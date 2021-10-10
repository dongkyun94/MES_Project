<%@page import="mainboard.model.MainBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	MainBoard board = (MainBoard) request.getAttribute("board");
	String pageno = (String) request.getAttribute("pageno");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 수정 폼</title>
</head>
<body>
<form action="boardmodify.do?boardnum=<%=board.getBoardNum()%>&pageno=<%=pageno%>" method="post" enctype="multipart/form-data">
	<div class="form-group">
		<label for="board_title" class="col-lg-2 control-label">제목</label>
		<div class="col-lg-10">	
      		<input type="text" id="board_title" name="boardTitle" value="${board.boardTitle}">
   		</div>
	</div>
	<div>
  		<label for="board_content">내용</label>
  		<div id="provision">
      		<textarea class="form-control" rows="8" style="resize:none" id="board_content" name ="boardContent">${board.boardContent}</textarea>
  		</div>
	</div>
	<div>
  		<div>작성자</div>
  		<div>
      	 	${board.memberid}
  		</div>
	</div>
	<div>
  		<label for=member_file class="col-lg-2 control-label">파일</label>
  		<div>
      		<label>파일 : </label><input id="fileAttachment" type="file" name="fileUpload" multiple="multiple" />
  		</div>
	</div>
	<div class ="text-center">
		<input type="submit" class = "btn btn-primary" value="등록">
	</div>
</form>
</body>
</html>