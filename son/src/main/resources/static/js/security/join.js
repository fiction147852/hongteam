/**
 * 
 */


const email_rule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

let mailConfirm;
let phoneConfirm;

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

function certPhone() {
	let certNo = $('#phoneCertNumber').val();

	if (certNo == phoneConfirm) {
		alert('인증되었습니다');
		$('#phonConfirm').val('Y');
		$('#phone_number').css('display', 'none');
	}
}

$(function() {
	$('#signUpForm').on('submit', function(e) {

		let confirmMail = $('#mailConfirm').val();
		let confirmPhone = $('#phoneConfirm').val();

		if (confirmMail == 'Y' && confirmPhone == 'Y') {
			return true;
		}
		console.log(confirmMail, confirmPhone, '안됨');

		return false;
	})
})
