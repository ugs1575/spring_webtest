<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.rcreate{
  font-size: 20px;
  font-weight:bold;
  text-align: left;
  border-style: solid;   /* 실선 */
  border-width: 1px;     /* 선 두께 */
  border-color: #AAAAAA; /* 선 색깔 */
  color: #000000;        /* 글자 색깔 */
  width: 35%;            /* 화면의 30% */ 
  padding: 10px;         /* 위 오른쪽 아래 왼쪽: 시간 방향 적용 */
  
  /* padding: 50px 10px;  50px: 위 아래, 10px: 좌우 */
  /* padding-top: 30px;  상단만 간격을 30px 지정   */
  
  margin: 20px auto; /* 가운데 정렬 기능, 20px: 위 아래, auto: 오른쪽 왼쪽*/
}
.rlist{
  line-height:1.2em;
  font-size: 10px;
  font-weight:bold;
  text-align: left;
  border-style: solid;   /* 실선 */
  border-width: 1px;     /* 선 두께 */
  border-color: #AAAAAA; /* 선 색깔 */
  color: #000000;        /* 글자 색깔 */
  width: 35%;            /* 화면의 30% */ 
  padding: 10px;         /* 위 오른쪽 아래 왼쪽: 시간 방향 적용 */
  
  /* padding: 50px 10px;  50px: 위 아래, 10px: 좌우 */
  /* padding-top: 30px;  상단만 간격을 30px 지정   */
  
  margin: 20px auto; /* 가운데 정렬 기능, 20px: 위 아래, auto: 오른쪽 왼쪽*/
}
hr{
  text-align: center;
  border-style: solid;   /* 실선 */
  border-width: 1px;     /* 선 두께 */
  border-color: #AAAAAA; /* 선 색깔 */
  width: 45%;            /* 화면의 30% */ 
}
</style>
<script type="text/javascript">
function rdelete(rnum){
	if(confirm("Would you like to delete this coment?")){
		var url = "./rdelete";
		url += "?num=${dto.num}";
		url += "&nPage=${nPage}";
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		url += "&nowPage=${param.nowPage}";
		url += "&rnum="+rnum;
		
		location.href=url;
	} else {
		return false;
	}
}

function fileDown(){
	var url = "${pageContext.request.contextPath}/download";
	url += "?filename=${dto.filename}";
	url += "&dir=/storage";
	
	location.href=url;
}

function listb(){					// 병원
	var url = "list";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";

	
	location.href = url;
}

function updateb(){
	var url = "update";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	url += "&num=${dto.num}";
	
	location.href = url;
}

function replyb(){
	var url = "reply";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	url += "&num=${dto.num}";
	
	location.href = url;
}

function deleteb(){
	var url = "delete";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	url += "&num=${dto.num}";
	url += "&filename=${dto.filename}";
	location.href = url;
}
</script>
</head>
<body>
<div class="container">
  <h2>조회</h2>
  <p>The .table class adds basic styling (light padding and horizontal dividers) to a table:</p>            
  <table class="table">
      <tr>
        <th>성명</th>
        <td>${dto.name}</td>
      </tr>
      <tr>
        <th>제목</th>
        <td>${dto.subject}</td>
      </tr>
      <tr>
        <th>내용</th>
        <td>${content}</td>
      </tr>
      <tr>
        <th>파일</th>
        <td>
        <c:choose>
        	<c:when test="${empty dto.filename }">파일없음</c:when>
        	<c:otherwise>
       		 <a href="javascript:fileDown()">${dto.filename}(${dto.filesize})</a>
       		</c:otherwise>
        </c:choose>

        </td>
      </tr>
      <tr>
        <th>등록일</th>
        <td>${dto.regdate}</td>
      </tr>
      <tr>
        <th>조회수</th>
        <td>${dto.count}</td>
      </tr>
      <tr>
        <th>IP</th>
        <td>${dto.ip}</td>
      </tr>
  </table>
  <button onclick="listb()">목록</button>		<!-- 병원 (new 이미지 중괄호 열고 닫고 넣어줘야함.. 놓침 ㅎㅎ;) -->
  <button onclick="updateb()">수정</button>
  <button onclick="deleteb()">삭제</button>
  <button onclick="replyb()">답변</button>
</div>
<hr>
	<c:forEach var="rdto" items="${rlist}">
	<div class="rlist">
	<p>${rdto.id }</p> | ${rdto.content }    ${rdto.regdate }
	<c:if test="${sessionScop.id == rdto.id}">
	<span style="float:right"><a href="javascript:rupdate('${rdto.rnum}','${rdto.content }')">수정</a>
 		|<a href="javascript:rdelete('${rdto.rnum}')">
 		삭제</a></span>
 	</c:if>
	</div>
	
	</c:forEach>


</body>
</html>