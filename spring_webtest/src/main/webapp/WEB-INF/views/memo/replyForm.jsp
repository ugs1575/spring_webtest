<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp"%>
<%
	
%>

<!DOCTYPE html>
<html>
<head>
<script type = "text/javascript">
function incheck(f){
	if(f.title.value==""){
		alert("제목을 입력하세요.");
		f.title.focus();
		return false;
	} 
	else if(f.content.value==""){
		alert("내용을 입력하세요.");
		f.content.focus();
		return false;
	}
}
</script>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<div class="container">
	<h5>등록</h5>
	<form name="frm" method="POST" action="./reply" onsubmit="return incheck(this)">
	<input type="hidden" name="memono" value="${dto.memono }">
	<input type="hidden" name="grpno" value="${dto.grpno }">
	<input type="hidden" name="indent" value="${dto.indent }">
	<input type="hidden" name="ansnum" value="${dto.ansnum }">
		<input type="hidden" name="col" value="${col }">
	<input type="hidden" name="word" value="${word }">
	<input type="hidden" name="nowPage" value="${nowPage }">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" size="30"  /></td>
			</tr>

			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="30" name="content" placeholder="내용 입력"></textarea>
				</td>
			</tr>
		</table>
			<input type="submit" value="전송">
	</form>
	</div>
</body>
</html>
