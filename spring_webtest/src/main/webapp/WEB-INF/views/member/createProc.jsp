<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>
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
	function mlist() {
		var url;
		url = "list.do";
		location.href = url;
	}
</script>
<%-- <link href="<%=root%>/css/style.css" rel="Stylesheet" type="text/css"> --%>
</head>
<body>


	<div class="container">
		<c:choose>
			<c:when test="${flag }">
		축하합니다. 우리사이트의 회원입니다. 지금부터!
		<br>
				<input type='button' value='로그인'
					onclick="loacation.href='./login'">
				<input type='button' value='홈'
					onclick="location.href='${root}'">
				<input type='button' value='회원목록' onclick="mlist()">
			</c:when>
			<c:otherwise>
		실패
		<br>
				<input type='button' value='다시시도' onclick="history.back()">
				<input type='button' value='홈'
					onclick="location.href='/'">
<!-- 				<input type='button' value='회원목록' onclick="mlist()"> -->
			</c:otherwise>
</c:choose>
</div>


</body>
</html>
