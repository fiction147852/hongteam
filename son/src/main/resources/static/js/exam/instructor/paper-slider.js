document.addEventListener('DOMContentLoaded', function() {

	// 슬라이드의 동작과 설정을 제어할 수 있는 Swiper 객체
	const swiper = new Swiper('.swiper', {

		// 슬라이드 화면에 마우스 커서를 올렸을 때 손 모양 커서로 변경할지 여부를 설정하는 속성
		grabCursor: true,

		// 한 화면에 보여질 슬라이드의 개수를 설정하는 속성
		slidesPerView: 2,

		// 슬라이드를 그리드 형태로 배치하는 설정
		grid: {
			// 그리드의 행 수를 설정
			rows: 3,
			// 그리드를 채우는 방식을 설정 ('row'는 행을 우선적으로 채움) column도 가능
			fill: 'row'
		},

		// 슬라이드 사이의 간격을 픽셀 단위로 설정하는 속성
		spaceBetween: 30,

		// 마우스 휠로 슬라이드를 제어할 수 있게 하는 속성
		mousewheel: true,

		// 스크롤바 설정을 제어하는 속성
		scrollbar: {
			// 스크롤바로 사용될 HTML 요소의 선택자를 지정
			el: ".swiper-scrollbar",
			// 사용자가 상호작용하지 않을 때 스크롤바를 숨길지 여부를 설정
			hide: true,
		},

	});
});