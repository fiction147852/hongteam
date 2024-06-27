/**
 * 
 */

$(document).ready(()=>{
	
})


var email_rule =  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
var email_id =$('#email_id');
var email_domain =$('#email_domain');
var mail = '';


function setEmailDomain(domain){
	$('#email_domain').val(domain);
}

function validateEmail(email_address){
	email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	if(!email_regex.test(email_address)){
		return false; 
	}else{
		return true;
	}
}

function checkEmail() {
	var emailInput = document.getElementById('email');

	var email = emailInput.value;

	if (validateEmail(email)) {
		
		$('#emailCheckBtn').val('인증번호 확인');
	} else {
		alert('잘못된 이메일 주소 형식입니다.');
	}
}

function emailCheck(email_address){
	
}
  /*if(!email_id){
      alert("이메일을 입력해주세요");
    $("#email_id").focus();
  }
  
  if(!email_domain){
      alert("도메인을 입력해주세요");
    $("#email_domain").focus();
  }
  
  
  $("#mail").val(mail);  
  
  if(!email_rule.test(mail)){
      alert("이메일을 형식에 맞게 입력해주세요.");
    return false;
  }*/
  
  