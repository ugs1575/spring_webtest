<%@ include file="../ssi/ssi.jsp" %>
<%@ page contentType="text/html; charset=UTF-8" %> 
<%
	int gno = (Integer) request.getAttribute("gno");
	boolean flag = (Boolean) request.getAttribute("flag");
	boolean pflag = (Boolean) request.getAttribute("pflag");
%>

<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 

</style> 
<script type="text/javascript">
function gread(){
	var url = "read.do";
	url = url + "?gno=<%=gno%>";
	location.href = url;
}
</script>
</head> 

<body>

 
<DIV class="container" align="center">
<h5>처리결과</h5>
 
 	<c:choose>
 		<c:when test="${pflag == false }">
 		 	비밀번호 불일치
 		 	<br>
 		 	<input type="button" value='다시 시도' onclick="history.back()">
 		 </c:when>
 		 <c:when test="${flag }">
 		 	수정완료
 		 	<br>
 		 	<input type='button' value='내 정보' onclick="gread()">
 		 </c:when>
 		 <c:otherwise>
 		 수정 실패
 		 <br>
 		 <input type='button' value='다시 시도' onclick="history.back()">
 		 </c:otherwise>
 	</c:choose>
 	
 	
	</DIV>
 
 

</body>
</html> 
