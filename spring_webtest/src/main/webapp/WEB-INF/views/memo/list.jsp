<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<script type = "text/javascript">
function read(memono){
	var url = "./read";
	url = url + "?memono="+memono;
	url = url+"&col=${col}";
	url = url+"&word=${word}";
	url = url+"&nowPage=${nowPage}";
	location.href=url;
}
</script>


</head>
<body>
<div class="container">

<form method="post" action="list">
<select name="col">
	<option value="title" <c:if test="${title }">selected</c:if>>제목</option>
	
	<option value="content" <c:if test="${content }">selected</c:if>>내용</option>
	
	<option value="total">전체출력</option>

</select>
<input type="text" name="word">
<button>검색</button>
<button type="button" onclick="location.href='create'">등록</button>
</form>

<h2>
<span class="glyphicon glyphicon-list"></span>목록</h2>
<table class="table table-hover">
	<thead>
	<tr>
		<th>번호</th>
		<th>제목</th>
		<th>날짜</th>
		<th>조회수</th>
		<th>grpno</th>
		<th>indent</th>
		<th>ansnum</th>
	</tr>
	</thead>
	<c:choose>
		<c:when test="${empty list }">
	<tbody>
	<tr>
	<td colspan="7">등록된 메모가 없습니다.</td>
	</tr>
	</tbody>
	</c:when>
	<c:otherwise>
		<c:forEach var="dto" items="${list }">
	<tbody>
	<tr>
	<td>${memono }</td>
	<td><div style="text-align:left">
			<c:forEach begin="1" end="${dto.indent }">&nbsp;&nbsp;</c:forEach>
			<c:if test="${dto.indent }>0">
				<img src='../template/images/re.jpg' style='width:30px; height:30px'>
			</c:if>
			
	<a href="javascript:read('${dto.memono }')">${dto.title }</a></div></td>
	<td>${dto.wdate }</td>
	<td>${dto.viewcnt }</td>
	<td>${dto.grpno }</td>
	<td>${dto.indent }</td>
	<td>${dto.ansnum }</td>
	
	</tr>
	</tbody>
	</c:forEach>
	</c:otherwise>
	</c:choose>
</table>
<div>
${paging }
</div>
</div>
</body>
</html>
