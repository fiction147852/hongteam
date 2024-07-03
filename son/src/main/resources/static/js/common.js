/**
 * 
 */

function getDateFormat(today, type = 2) {
	// 한글을 사용하는 요일을 위해서는 추가적으로 배열을 만들어서 사용합니다.
	let day = ['일', '월', '화', '수', '목', '금', '토'];

	if (type == 1) {

		//1번 포맷
		let dateFormat1 = today.getFullYear() + '년 ' + (today.getMonth() + 1) + '월 '
			+ today.getDate() + '일 ' + day[today.getDay()] + '요일 '
			+ today.getHours() + '시 ' + today.getMinutes() + '분 '
			+ today.getSeconds() + '초'
		return dateFormat1;
	} else if (type == 2) {


		//2번 포맷
		let dateFormat2 = today.getFullYear() +
			'-' + ((today.getMonth() + 1) < 9 ? "0" + (today.getMonth() + 1) : (today.getMonth() + 1)) +
			'-' + ((today.getDate()) < 9 ? "0" + (today.getDate()) : (today.getDate()));
		return dateFormat2;
	} else if(type == 3) {

		// 3번 포맷
		let dateFormat3 = today.getFullYear() + '년 ' + (today.getMonth() + 1) + '월 '
			+ today.getDate() + '일 ' + day[today.getDay()] + '요일'
		return dateFormat3;
	} else if (type == 4){
		let dateFormat4 =day[today.getDay()] + '요일 '
		return dateFormat4;
	}

}