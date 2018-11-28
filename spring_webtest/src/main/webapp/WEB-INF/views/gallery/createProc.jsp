<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="../ssi/ssi.jsp"%>
<%
%>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 

<script type="text/javascript">
function glist(){
	var url = "list.do";
	location.href = url;	
}
function gcreate(){
	var url = "create.do";
	location.href = url;
}

</script>
</head> 
<body>
 
<div
 class="container" align="center">
 <h5>처리결과</h5>
	<c:choose>
		<c:when test="${flag }">
			사진을 게시하였습니다.
			<br>
			<button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
  <button type="button" class="btn btn-default btn-sm" onclick="gcreate()"><span class="glyphicon glyphicon-pencil"></span> 계속등록</button>
  		</c:when>
  		<c:otherwise>
  			사진등록에 실패하였습니다.
  			<br>
  			 <button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
  <button type="button" class="btn btn-default btn-sm" onclick="history.back()"><span class="glyphicon glyphicon-repeat"></span> 다시시도</button>
  		</c:otherwise>
  	</c:choose>
  </DIV>

 
 
</body>
</html> 