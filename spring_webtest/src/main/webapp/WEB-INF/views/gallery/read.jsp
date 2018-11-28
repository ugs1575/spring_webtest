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

.img{
	max-width:800px;
	max-height:600px;
}
.imglist{
	max-width:200px;
	max-height:200px;
}
.curImg {
	margin-right: 0;
	border-style: solid;
	border-width: 3px;
	border-color: red;
	max-width:200px;
	max-height:200px;
}

.td_padding {
	padding: 5px 5px;
}

</style>
<script type="text/javascript">
	function gupdate(){
		var url = "update";
		url += "?gno=${dto.gno}";
		url += "&oldfile=${dto.fname}";
		
		location.href = url;
	}
	function gdelete(){
		var url = "delete";
		url += "?gno=${dto.gno}";
		url += "&fname=${dto.fname}"
		location.href = url;
	}
	function glist(){
		var url = "list";
				location.href = url;
	}
	function readGo(gno){
		var url = "read";
		url += "?gno="+gno;
		
		location.href = url;
	}
</script>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
</head>
<body>

	<h2 style="text-align: center"><span class="glyphicon glyphicon-picture"></span>${dto.writer }'s</h2>

	<TABLE class="table table-hover">
	<tr>
			<TD rowspan="6" style="width:800px; height:600px; overflow:hidden; text-align: center">
			<img class="img" src="./storage/${dto.fname }" style="max-width:800px; max-height:600px"></TD>
			<tr>
			<TD style="width:400px; height:50px" >
			<div class="form-group" >
					 <label for="title">Title:</label><br>
			${dto.title }</div></TD>
			</tr>
			<tr>
			<TD style="width:400px; height:50px">
			<div class="form-group" >
					 <label for="usr">Writer:</label><br>
					 ${dto.writer }</div></TD>
			</tr>
			<tr>
			
			<TD style="width:400px">
			<div class="form-group" >
					 <label for="content">Content:</label><br>
			${content }</div></TD>
			</tr>
			<tr>
			
			<TD style="width:400px; height:50px">
			<div class="form-group" >
					 <label for="date">Date:</label><br>
					 ${dto.udate }</div></TD>
			</tr>
			<tr>
			
			<TD style="width:400px; height:50px">
			<div class="form-group" >
					 <label for="view">View:</label><br>
					 ${dto.viewcnt }</div></TD>
			</tr>
	</TABLE>
	<table style="width: 1000px; margin:0 auto" >
	<tr>
		<c:forEach var='i' begin='0' end='4'>
			<c:choose>
				<c:when test="${empty lFname[i] }">
		<td class="td_padding"><img class="imglist" src="./storage/noimage.jpg" 
			style="max-width:'200px'; max-height:'200px'"></td>
				</c:when>
				<c:otherwise>	
					<c:choose>
						<c:when test="${lGno[i] == dto.gno }">		
			<td class="td_padding"><a
			href="javascript:readGo('${lGno[i] }')"> <img class="curImg"
				src="./storage/${lFname[i] }" style="max-width:'200px'; max-height:'200px'" border="0"></a></td>
						</c:when>
						<c:otherwise>
						<td class="td_padding" ><a
			href="javascript:readGo('${lGno[i]}')"> <img class="imglist" 
				src="./storage/${lFname[i] }" style="max-width:'200px'; max-height:'200px'" border="0"></a></td>
				</c:otherwise>
				</c:choose>
			</c:otherwise>
			</c:choose>
		</c:forEach>
	
		</tr>
	</table>
	

	<DIV class='bottom' style="text-align:center">
		<button type="button" class="btn btn-default btn-sm" onclick="gupdate()"><span class="glyphicon glyphicon-edit"></span> 수정</button>
		<button type="button" class="btn btn-default btn-sm" onclick="gdelete()"><span class="glyphicon glyphicon-trash"></span> 삭제</button>
		<button type="button" class="btn btn-default btn-sm" onclick="glist()"><span class="glyphicon glyphicon-list"></span> 목록</button>
	</DIV>

</body>
</html>