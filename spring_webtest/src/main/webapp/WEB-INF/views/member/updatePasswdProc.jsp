<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>
<%
%>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 
<script type="text/javascript">
function read(id){
	var url = "read";
	url += "?id=${param.id}";
	location.href=url;
	
}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head> 
<body>
 <div class="container">
<h5>처리 결과</h5>

	<c:choose>
		<c:when test="${mflag==false }">
			현재 비밀번호가 일치하지 않습니다. <br>
			    <input type='button' value='다시시도' onclick="history.back()">
    	</c:when>
    	<c:when test="${mflag2 }">
    		잘못된 비밀번호 입니다.<br>
    		    <input type='button' value='다시시도' onclick="history.back()">
    	</c:when>
    	<c:when test="${flag }">
    		비밀번호가 변경되었습니다.<br>
    	  <input type='button' value='나의정보' onclick="read('${param.id}')">
    </c:when>
    	<c:otherwise>
    		비밀번호 변경에 실패하였습니다.<br>
    <input type='button' value='다시시도' onclick="history.back()">
    	</c:otherwise>
    </c:choose>
    		
  
  </DIV>

 
 
</body>
</html> 