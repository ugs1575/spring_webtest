<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/ssi/ssi.jsp" %>
<!DOCTYPE html> 
<html> 
<head> 
<style>
img {
  max-width: 400px;
  max-height: 300px; 
}
</style>
<meta charset="UTF-8"> 
<title></title> 
<script type = "text/javascript">
function read(gno) {
	var url = "read";
	url = url + "?gno=" + gno;
	url = url +"&col=${col}";
	url = url +"&word=${word}";
	url = url +"&nowPage=${nowPage}";
	location.href = url;
}
</script> 

</head>
<body>
	
	<div class="container">
	<div>
		<form method="post" action="./list">
			<select name="col">
				<option value="writer"
				<c:if test="${col=='writer' }">selected</c:if>
				>작성자</option>
				<option value="title"
				<c:if test="${col=='title' }">selected</c:if>
				>제목</option>
				<option value="content"
				<c:if test="${col=='contnet' }">selected</c:if>
				>내용</option>
				<option value="total">전체출력</option>
			</select> <input type="text" name="word" value="${word }">
			<button>검색</button>
			<button type="button" onclick="location.href='create.do'">등록</button>
		</form>

	</div>
	<h2><span class="glyphicon glyphicon-th-list"></span>이미지 목록</h2>
		<c:choose>
			<c:when test="${empty list }">
		<tr>
			<td colspan="6">등록된 글이 없습니다.</td>
		</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var='dto' items="${list }">
				
		<TABLE class="table table-hover">
		<tbody>
				<tr>
				<td rowspan = '6' width="50px">${dto.gno }</td>
				</tr>	
				
				<TR>
					<TD rowspan='6' width="400px" height="300px"><a href="javascript:read('${dto.gno }')"><img
						src='./storage/${dto.fname }' style="max-width:'400px'; max-height:'300px'"></a>
					</TD>
				</TR>
				
				<TR>
					<TH>작성자</TH>
					<TD>${dto.writer }</TD>
				</TR>
				
				<TR>
					<TH>제목</TH>
					<TD>${dto.title }</TD>
				</TR>
				
				<TR>
					<TH>작성날짜</TH>
					<TD>${dto.udate }</TD>
				</TR>
				
				<TR>
					<TH>조회수</TH>
					<TD>${dto.viewcnt }</TD>
				</TR>
				
			</TABLE>
			</tbody>
</c:forEach>
</c:otherwise>
</c:choose>

 
${paging }
  
</div> 
 

</body>
</html> 