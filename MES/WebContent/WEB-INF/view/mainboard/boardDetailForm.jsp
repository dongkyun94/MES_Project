<%@page import="mainboard.model.MainBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
	MainBoard board = (MainBoard) request.getAttribute("board");
	String pageno = (String) request.getAttribute("pageno");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>boardDetailForm</title>
</head>
<body>

<div class="form-group input-group">
	<div>제목</div>
	<div class="input-group-prepend">
		${board.boardTitle}
	</div>
</div>

<div class="form-group input-group">
	<div>내용</div>
	<div class="input-group-prepend">
		${board.boardContent}
	</div>
</div>

<c:if test="${board.getBoardFile() != null}" >
	<div class="form-group input-group">
		<div class="input-group-prepend">
			파일
		</div>
	</div>
	<div>
		<a href="boarddownload.do?fn=${board.boardFile}">${board.getBoardFile()}</a>
	</div>			
</c:if>

<div class="form-group input-group">
	<c:if test="${board.memberid eq authUser.id}" >
		<a href="boarddelete.do?boardnum=<%=board.getBoardNum()%>&pageno=<%=pageno%>" class="btn btn-success mr-sm-3">삭제</a>
		<a href="boardmodify.do?boardnum=<%=board.getBoardNum()%>&pageno=<%=pageno%>" class="btn btn-success mr-sm-3">수정</a>
	</c:if>
	<a href="boardlist.do?pageno=<%=pageno%>" class="btn btn-success">목록</a>
</div>

</body>
</html>