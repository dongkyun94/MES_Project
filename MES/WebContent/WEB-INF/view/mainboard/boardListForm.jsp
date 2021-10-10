<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--FONT AWESOME-->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
<title>table</title>
</head>
<body>
 
 <div class="container" align="center">
	<div class="jumbotron">
		<h3>게시판 글목록 보기</h3>
		<c:if test="${authUser.id eq 'admin'}">
   	     	<div>
   	     		<a href="boardinsert.do" class="btn btn-success">글쓰기</a>	
   	     	</div>
        </c:if>
		<c:choose>
			<c:when test="${boardlist.hasNoBoards()}">
			<h5>
				<p class="bg-danger text-white">등록된 게시글이 존재하지 않습니다</p>
			</h5>
			</c:when>
			<c:otherwise>
				<!--게시판 시작-->
				<table class="table table-hover">
					<tr class="table-dark ">
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>첨부파일</th>
						<th>조회수</th>
					</tr>
					<c:forEach var="board" items="${boardlist.content}">
						<tr>
							<!--번호-->
							<td>${board.boardNum}</td>
							<!--제목-->
							<td>
								<a href="boarddetail.do?boardnum=${board.boardNum}&pageno=${boardlist.page}">
								${board.boardTitle}
								</a>
							</td>
							<!--작성자-->
							<td>${board.memberid}</td>
							<!--날짜-->
							<td>${board.boardDate}</td>
							<!--파일-->
							<td>
								<c:if test="${!empty board.getBoardFile()}">
									<a href="boarddownload.do?fn=${board.boardFile}"><i class="fas fa-file-download"></i></a>
								</c:if>
							</td>
							<!--조회수 -->
							<td><span class="badge badge-success ml-sm-3">${board.boardReadCount}</span></td>
						</tr>
					</c:forEach>
				</table>
				<!--게시판 끝-->
				<!--페이지네이션 시작-->
			    <div class="container">
					<ul class="pagination justify-content-center">	
						<c:if test="${boardlist.startPage!=1}">
							<li class="page-item"><a href="boardlist.do?pageno=1" class="page-link">[시작]</a></li>
							<li class="page-item"><a href="boardlist.do?pageno=${boardlist.startPage-10}" class="page-link">[10페이지 전]</a></li>				
						</c:if>
						<c:forEach var="pageNum" begin="${boardlist.startPage}" end="${boardlist.endPage}">
							<li class="page-item"><a class="page-link" href="boardlist.do?pageno=${pageNum}">${pageNum}</a></li>			
						</c:forEach>
						<c:if test="${boardlist.endPage < boardlist.totalPage}">
							<li class="page-item"><a href="boardlist.do?pageno=${boardlist.endPage+1}"  class="page-link">[10페이지 후]</a></li>
							<li class="page-item"><a href="boardlist.do?pageno=${boardlist.totalPage}"  class="page-link">[끝]</a></li>			
						</c:if> 
					</ul>
				</div> 
				<!--페이지네이션 끝-->
			</c:otherwise>
		</c:choose>
	</div>
</div>
</body>
</html>