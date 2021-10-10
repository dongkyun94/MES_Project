<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript" src="/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/fileupload.js"></script>
<head>
<meta charset="UTF-8">
<title>게시판입력</title>
</head>
<body>
<form action="boardinsert.do" method="post" enctype="multipart/form-data">
	<div class="form-group">
		<label for="board_title" class="col-lg-2 control-label">제목</label>
		<div class="col-lg-10">	
      		<input type="text" id="board_title" name="boardTitle">
   		</div>
	</div>
	<div>
  		<label for="board_content">내용</label>
  		<div id="provision">
      		<textarea class="form-control" cols= "100" rows="30" style="resize:none" id="board_content" name ="boardContent"></textarea>
  		</div>
	</div>
	<div>
  		<label for=member_file class="col-lg-2 control-label">파일</label>
  		<div>
      		<label>파일 : </label><input id="fileAttachment" type="file" name="fileUpload" multiple="multiple" />
  		</div>
	</div>
	<div class ="text-center">
		<input id="uploadBtn" type="submit" class="btn btn-primary" value="등록">
	</div>
</form>
</body>
</html>