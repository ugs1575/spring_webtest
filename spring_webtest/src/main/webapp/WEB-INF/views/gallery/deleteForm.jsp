<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp" %>
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
function incheck(f){
	if(f.passwd.value==""){
		alert("비밀번호을 입력하세요");
		f.passwd.focus();
		return false;
	}
}
</script>
</head> 
<body>
 
<DIV class="container" align="center">
<h5>이미지삭제</h5>

<FORM name='frm' method='POST' action='./delete' 
onsubmit="return incheck(this)">>
	<input type="hidden" name="gno" value=${gno }>
	<input type="hidden" name="fname" value=${fname }>>
삭제하면 복구 할 수 없습니다.<br>
삭제 하시겠습니까?
    <TABLE>
    <TR>
      <TH>비밀번호</TH>
      <TD><input type="password" name="pw"></TD>
    </TR>
  </TABLE>
  <DIV class='bottom'>
    <button type="submit" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-edit"></span>삭제</button>
			 <button type="button" class="btn btn-default btn-sm" onclick="history.back()"><span class="glyphicon glyphicon-repeat"></span>뒤로가기</button>
  </DIV>
</FORM>
 </DIV>
 
</body>
</html> 
