<%@ include file="../ssi/ssi.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>
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
function read(){
	var url = "read.do";
	url = url + "?gno=${gno}";
	location.href = url;
}
</script>
</head>

<body>


	<DIV class="container" align="center">
	<h5>비밀번호 변경</h5>
	
	<c:choose>
		<c:when test="${gflag==false}">
			비밀번호가 틀렸습니다.
			<br>
			<input type='button' value='다시 시도' onclick="history.back()">
		</c:when>
		<c:when test="${flag }">
			비밀번호 변경 완료!
			<br>
			<input type='button' value='내 정보' onclick="read()">
		</c:when>
		<c:otherwise>
			비밀번호 변경에 실패했습니다.
			<br>
			<input type='button' value='다시 시도' onclick="history.back()">
		</c:otherwise>
	</c:choose>

</DIV>


</body>
</html>
