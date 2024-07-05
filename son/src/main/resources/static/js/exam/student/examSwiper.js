let swiperThree;

document.addEventListener("DOMContentLoaded", function() {

    swiperThree = new Swiper('.modal__exam-info .swiper', {
        grabCursor: false,
        simulateTouch: false, // 드래그 이동 비활성화
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
            type: 'fraction'
        },

        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });
})