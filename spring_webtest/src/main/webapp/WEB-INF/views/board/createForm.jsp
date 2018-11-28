<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
</head>
<body>
<div class="container">
  <h2>글쓰기</h2>
  <hr>
  <form action="./create" method="post" enctype="multipart/form-data">
    <div class="form-group">
      <label for="name">성명</label>
      <input type="text" class="form-control" id="name" placeholder="Enter name" name="name">
    </div>
    <div class="form-group">
      <label for="subject">제목</label>
      <input type="text" class="form-control" id="subject" placeholder="Enter subject" name="subject">
    </div>
    <div class="form-group">
      <label for="content">내용</label>
      <textArea rows="10" class="form-control" id="content" placeholder="Enter content" name="content"></textArea>
    </div>
    <div class="form-group">
      <label for="pwd">비밀번호</label>
      <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="passwd">
    </div>
    <div class="form-group">
      <label for="file">파일</label>
      <input type="file" class="form-control" id="filenameMF" placeholder="Enter file" name="filenameMF">
    </div>
    <button type="reset" class="btn btn-primary">취소</button>
    <button type="submit" class="btn btn-primary">등록</button>
  </form>
</div>
</body>
</html>