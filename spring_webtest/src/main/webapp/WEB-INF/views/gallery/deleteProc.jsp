<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="../ssi/ssi.jsp" %>

<%


%>
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 

</style> 
<script type="text/javascript">
function glist(){
	var url = "list.do";
	location.href = url;	
}
</script>
</head> 
<body>
 
<DIV class="container" align="center">
<h5>처리결과</h5>
	<c:choose>
		<c:when test="${pflag==false }">
			비밀번호 오류
			<br>
	 <input type='button' value='다시시도' onclick="history.back()">
 		    <input type='button' value='홈' onclick="location.href='../index.jsp'">
 		    </c:when>
 		<c:when test="${flag }">
 			삭제완료
 			<br>
 			<button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
 		</c:when>
 		<c:otherwise>
 			삭제 실패
 			<br>
 			<button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
  <button type="button" class="btn btn-default btn-sm" onclick="history.back()"><span class="glyphicon glyphicon-repeat"></span> 다시시도</button>
 		</c:otherwise>
	

</c:choose>
</DIV>

 
 
</body>
</html> 
