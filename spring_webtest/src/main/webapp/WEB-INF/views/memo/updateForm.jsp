<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

</head>
<body>
	<div class="container">
	<h5>수정</h5>
	<form name="frm" method="POST" action="./update" onsubmit="return input(this)">
	<input type="hidden" name="memono" value="${dto.memono }">
	<input type="hidden" name="col" value="${col }">
	<input type="hidden" name="word" value="${word }">
	<input type="hidden" name="nowPage" value="${nowPage }">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" size="30" value="${dto.title }" /></td>
			</tr>

			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="30" name="content">${dto.content }</textarea>
				</td>
			</tr>
		</table>
		
			<input type="submit" value="수정">
		
	</form>
	</div>
</body>
</html>