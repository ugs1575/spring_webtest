<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function mlist(){
	var url = "list";
	url+="?col=${col}";
	url+="&word=${word}";
	url+="&nowPage=${nowPage}";
	
	location.href=url;
	
}
</script>
</head>
<body>

<c:choose>
	<c:when test="${flag }">
	<div class="container">
	<h5>삭제</h5>
	답변이 달려있어 삭제가 불가능합니다.
	
	<button onclick="mlist()">목록</button>
	<button onclick="history.back()">돌아가기</button>
	</div>
	</c:when>
	<c:otherwise>
	<div class="container">
	<h5>삭제 확인</h5>
	<form method="POST" action="delete">
		<input type="hidden" name="memono" value="${param.memono }"> <input
			type="hidden" name="col" value="${param.col }">
		<input type="hidden" name="word"
			value="${param.word }"> <input
			type="hidden" name="nowPage"
			value="${param.nowPage }">
		<div>
			삭제를 하면 복구 될 수 없습니다.<br> <br> 삭제하시려면 삭제버튼을 클릭하세요.<br> <br>
			취소는 '목록'버튼을 누르세요.<br> <br> <input type="submit"
				value="삭제처리"> <input type="button" value="목록"
				onclick="mlist()">
		</div>
	</form>
	</div>
	</c:otherwise>
	
</c:choose>

</body>
</html>
