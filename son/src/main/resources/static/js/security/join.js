/**
 * 
 */


const email_rule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
const pwd_rule = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
const id_rule = /^[A-Za-z0-9][A-Za-z0-9]*$/;

let mailConfirm;
let phoneConfirm;
let childPhoneConfirm;
let childPhoneNumber;

function setEmailDomain(domain) {
	$('#email_domain').val(domain);
}

function validateEmail(email_address) {
	email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	if (!email_regex.test(email_address)) {
		return false;
	} else {
		return true;
	}
}

function checkEmail() {
	let email = $('#email_id').val() + '@' + $('#email_domain').val();

	if (validateEmail(email)) {

		if (emailDoubleCheck(email) != 0) {
			alert('이미 가입된 이메일입니다');
		} else {
			sendEmail(email);
			$('#mail_number').css('display', 'block');
		}
	} else {
		alert('잘못된 이메일 주소 형식입니다.');
	}
}

function sendEmail(email) {
	console.log('이메일 주소 in js', email);
	$.ajax({
		url: '/lms/sec/emailConfirm',
		type: 'post',
		data: { "mail": email },
		//dataType:'json',
		success: function(data) {
			console.log(data);
			alert('인증번호를 발송했습니다');
			mailConfirm = data;
			//$('#mailConfirm').attr("value", data);
		}
	})
}

function certMail() {
	let certNo = $('#emailCertNumber').val();
	//let confirmNo = $('#mailConfirm').val();

	if (certNo == mailConfirm) {
		alert('인증되었습니다');
		$('#mailConfirm').val('Y');
		$('#mail_number').css('display', 'none');
	} else {
		alert('번호가 다릅니다. 다시 입력하세요');
	}

	console.log($('#mailConfirm').val());
	
}

function emailDoubleCheck(mail) {
	let cnt;

	$.ajax({
		url: '/lms/sec/emailDoubleCheck',
		type: 'post',
		async: false,
		data: { "mail": mail },
		success: function(data) {
			cnt = data;
		}
	})

	return cnt;
}

function childMailCheck(){
	let mail = $('#childMail').val();
	
	$.ajax({
		url: '/lms/sec/childMailCheck',
		type: 'post',
		data: {'mail': mail},
		success: function(data){
			if(data != null && data.parentNumber == -1){
				alert('등록 가능한 이메일입니다.');
				$('#child_phone').css('display', 'block');
				childPhoneNumber = data.phone;
				$('#studentNumber').val(data.studentNumber);
			}else{
				$('#child_phone').css('display', 'none');
				alert('등록 불가능한 이메일입니다.');
			}
		}
	})
}

// 발송된 인증번호 맨 위 전역변수에 저장
function checkPhone() {
	alert('번호 발송이 완료되었습니다.\n휴대폰에서 인증번호 확인을 해주십시오.');
	$('#phone_number').css('display', 'block');
	$.ajax({
		url: '/lms/sec/phoneConfirm',
		type: 'post',
		data: { "phone": $('#phone').val() },
		success: function(data) {
			console.log(data);
			phoneConfirm = data;
		}
	})
}

function childCheckPhone(){
	if(childPhoneNumber == $('#childPhone').val()){
		alert('번호 발송이 완료되었습니다.\n휴대폰에서 인증번호 확인을 해주십시오.');
		$('#child_phone_number').css('display', 'block');
		
		$.ajax({
			url: '/lms/sec/phoneConfirm',
			type: 'post',
			data: { "phone": $('#childPhone').val() },
			success: function(data) {
				console.log(data);
				childPhoneConfirm = data;
			}
		})
		
	}else{
		alert('휴대폰 번호를 다시 확인해주세요');
	}
}

// 전역변수로 받은 인증번호와 입력받은 인증번호가 일치하는지 확인
function certPhone() {
	let certNo = $('#phoneCertNumber').val();

	if (certNo == phoneConfirm) {
		alert('인증되었습니다');
		$('#phoneConfirm').val('Y');
		$('#phone_number').css('display', 'none');
	} else{
		alert('인증번호가 일치하지 않습니다.');
	}
}

function childCertPhone(){
	let certNo = $('#childPhoneCertNumber').val();
	
	if(certNo == childPhoneConfirm){
		alert('인증 완료');
		$('#childConfirm').val('Y');
		$('#child_phone_number #child_phone').css('display', 'none');
	} else{
		alert('인증번호가 일치하지 않습니다.');
	}
}

// 다음 주소검색 api
function searchAddr(){
	new daum.Postcode({
        oncomplete: function(data) {
			var addr;
			
			if (data.userSelectedType === 'R'){
				addr = data.roadAddress;
			}else{
				addr = data.jibunAddress;
			}
			$('#addr').val(addr);
			$('#detailAddr').focus();
        }
    }).open();
}

$(function() {
	
	$('input[type=radio]').click(()=>{
		let type = $('input[type=radio]:checked').val();
		if(type == 1){
			$('#studentCert').css('display', 'none');
		}else{
			$('#studentCert').css('display', 'block');
		}
	});
	
	$('#signUpForm').on('submit', (event) => {
		
		console.log($('#birth').val());
		
		let confirmMail = $('#mailConfirm').val();
		let confirmPhone = $('#phoneConfirm').val();
		let childPhoneConfirm = $('#childConfirm').val();
		
		let type = $('input[type=radio]:checked').val();
		
		let dAddr = $('#detail_address').val();
		if(typeof dAddr == "undefined"){
			dAddr = ' ';
		}
		
		let addr = $('#addr').val() + ' ' + dAddr;
		$('#address').val(addr);
		let email = $('#email_id').val() + '@' + $('#email_domain').val();
		$('#email').val(email);
		
		if(type == 2 && childPhoneConfirm != 'Y'){
			
			alert('자녀 인증');
			
			return false;
		}
		
		if (confirmMail != 'Y' || confirmPhone != 'Y') {
			
			alert('인증을 완료해주세요');
			
			return false;
			
		}
		
	});
	
})
