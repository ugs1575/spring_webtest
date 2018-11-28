<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ include file="/ssi/ssi.jsp"%>
<%String email = request.getParameter("email"); %>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 

</style>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript">
$.ajaxSetup({
    error: function(jqXHR, exception) {
        if (jqXHR.status === 0) {
            alert('Not connect.\n Verify Network.');
        }
        else if (jqXHR.status == 400) {
            alert('Server understood the request, but request content was invalid. [400]');
        }
        else if (jqXHR.status == 401) {
            alert('Unauthorized access. [401]');
        }
        else if (jqXHR.status == 403) {
            alert('Forbidden resource can not be accessed. [403]');
        }
        else if (jqXHR.status == 404) {
            alert('Requested page not found. [404]');
        }
        else if (jqXHR.status == 500) {
            alert('Internal server error. [500]');
        }
        else if (jqXHR.status == 503) {
            alert('Service unavailable. [503]');
        }
        else if (exception === 'parsererror') {
            alert('Requested JSON parse failed. [Failed]');
        }
        else if (exception === 'timeout') {
            alert('Time out error. [Timeout]');
        }
        else if (exception === 'abort') {
            alert('Ajax request aborted. [Aborted]');
        }
        else {
            alert('Uncaught Error.n' + jqXHR.responseText);
        }
    }
});
function use(){
	opener.frm.email.value = document.frm.email.value;
	self.close();
}
function emailCheck(email){
	if(email==""){
		alert("이메일을 입력해 주세요");
		document.frm.email.focus();
	} else{
		var url = "emailCheck";
		
		// ajax로 비동기 통신
		$.ajax({
			url : url,
			dataType : 'text',
			type : "GET",
			data : {"email" : email},
			
			success : function(data){
				$("#emailcheck").text(data.trim()).css("color","red");
				if(data.trim().indexOf("사용가능")!=-1)
					$("#emailcheck").append("<button onclick='use()'>적용</button>")
			}
		});
}
}
</script> 
</head> 
<body>

 
<DIV class="container">
<h2>Email 중복 확인</h2>
<FORM name='frm' method='POST'>
  Email를 입력해주세요.<br><br>
  <TABLE>
    <TR>
      <TH>이메일</TH>
      <TD><input type="text" name="email" size="50" value="<%=email %>" >
      <br> <span id="emailcheck"></span>
      </TD>
    </TR>
  </TABLE>
  
    <input type='button' value='중복확인' onclick="emailCheck(this.email)">
    <input type='button' value='취소' onclick="window.close()">
</FORM>
 </DIV>
 

</body>
</html> 