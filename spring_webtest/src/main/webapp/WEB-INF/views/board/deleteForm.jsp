<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
  <h2>글삭제</h2>
  <hr>
<c:choose>
	<c:when test="${flag }">
	<div class="container" align="center">
		부모글이 존재하여 삭제 할 수 없습니다.
		</div>
		<button onclick="history.back()" class="btn btn-primary">돌아가기</button>
	</c:when>
	<c:otherwise>	
		<form action="./delete" method="post">
  
  <input type = "hidden" name = "num" value="${param.num }">
  <input type = "hidden" name = "oldfile" value="${param.filename }">

    <div class="form-group">
      <label for="passwd">비밀번호</label>
      <input type="password" class="form-control" id="password" placeholder="Enter password" name="passwd">
    </div>
    
    <button onclick="history.back()" class="btn btn-primary">돌아가기</button>
    <button type="submit" class="btn btn-primary">삭제</button>
  </form>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>