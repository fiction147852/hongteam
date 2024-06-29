/**
 * 
 */


const email_rule =  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

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
	let email = $('#email_id').val() + '@' + $('#email_domain').val();

	if (validateEmail(email)) {
		sendEmail(email);
		$('#mail_number').css('display', 'block');
	} else {
		alert('잘못된 이메일 주소 형식입니다.');
	}
}

function sendEmail(email){
	console.log('이메일 주소 in js', email);
	$.ajax({
		url:'/lms/emailConfirm',
		type:'post',
		data:{"mail" : email},
		//dataType:'json',
		success:function(data){
			console.log(data);
			alert('인증번호를 발송했습니다');
			$('#confirm').attr("value", data);
		}
	})
}

function compareNumber(){
	let certNo = $('#emailCertNumber').val();
	let confirmNo = $('#confirm').val();
	
	if(certNo == confirmNo){
		alert('인증되었습니다');
		$('#confirm').val('Y');
		$('#mail_number').css('display', 'block');
	}else{
		alert('번호가 다릅니다. 다시 입력하세요');
	}
	
	console.log($('#confirm').val());
}