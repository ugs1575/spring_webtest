<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../ssi/ssi.jsp"%>
<%	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* * { */
/* 	font-size: 20px; */
/* } */

/* div#title { */
/* 	width: 30%; */
/* 	margin: 20px auto; */
/* 	margin-top: 20 px; */
/* 	border: 1px solid black; */
/* } */

/* div { */
/* 	text-align: center; */
/* 	margin-top: 10px; */
/* } */
</style>
<script type = "text/javascript">
function tlist(){
	var url = "list.do";
	
	location.href=url;
}
</script>
</head>
<body>
	<div class="container">
	<h5>처리 결과</h5>
		<c:choose>
			<c:when test="${flag }">
				답변이 등록되었습니다.
			</c:when>
			<c:otherwise>
				답변 등록에 실패하였습니다.
				<br><button onclick="history.back()">다시시도</button>
			</c:otherwise>
		</c:choose>
		
		<button onclick="tlist()">목록</button>
	</div>
</body>
</html>