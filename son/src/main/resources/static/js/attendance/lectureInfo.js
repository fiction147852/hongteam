let swiperTwo;

document.addEventListener('DOMContentLoaded', function () {

    // 슬라이드의 동작과 설정을 제어할 수 있는 Swiper 객체
    const swiper = new Swiper('.swiper', {

        // 슬라이드 화면에 마우스 커서를 올렸을 때 손 모양 커서로 변경할지 여부를 설정하는 속성
        grabCursor: false,

        // 슬라이드의 현재 위치와 전체 개수를 시각적으로 표시하는 페이지네이션의 설정을 제어할 수 있는 속성
        pagination: {
            // 페이지네이션 요소로 사용될 HTML 요소의 선택자를 지정하는 속성
            el: '.swiper-pagination',

            // 페이지네이션 요소를 클릭 가능 여부를 지정하는 속성
            clickable: true,

            // 페이지네이션 요소의 형태(모양)를 설정하는 속성
            type: 'bullets'
        },

        // 슬라이드 화면의 이동을 위한 이전/다음(네이게이션) 버튼의 설정을 제어하는 속성
        navigation: {
            // 다음 버튼으로 사용될 HTML 요소의 선택자를 지정하는 속성
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });


    swiperTwo = new Swiper('.modal__lecture-info .swiper', {
        grabCursor: true,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },

    });
});