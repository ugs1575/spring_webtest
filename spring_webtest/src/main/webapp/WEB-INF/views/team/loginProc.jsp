<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="../ssi/ssi.jsp"%>
<%
boolean flag = (Boolean) request.getAttribute("flag");
%>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
</style> 
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<body>
 
<DIV class="container">
<h2>로그인처리</h2>
<c:choose>
	<c:when test="${flag }">
		로그인 되었습니다.
	</c:when>
	<c:otherwise>
		아이디/비밀번호를 잘못 입렵하였습니다.<br>
		혹은 회원이 아닙니다. 회원가입을 하십시오.
	</c:otherwise>
</c:choose>
  
    <input type='button' value='홈' onclick="location.href='../index.jsp'">
    <input type='button' value='다시시도' onclick="history.back()">
  </DIV>

 
 
</body>
</html> 