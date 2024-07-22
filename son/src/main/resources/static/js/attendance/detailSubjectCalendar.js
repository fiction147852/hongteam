document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');
    let currentPopover = null;

    if (!calendarEl) {
        console.error('캘린더 요소를 찾을 수 없습니다.');
        return;
    }

    const calendar = new FullCalendar.Calendar(calendarEl, {
        // 캘린더 높이 설정
        height: '1000px',

        // 모든 주(row)의 높이가 동일하게 설정되어 균일한 높이를 유지한다.
        expandRows: true,

        // 언어 및 시간에 대한 설정
        timeZone: 'Asia/Seoul',

        // 하루에 표시할 수 있는 최대 이벤트 수를 설정. 특정 숫자로 설정하면 그 숫자만큼의 이벤트만 한 번에 표시하고, 추가 이벤트는 "+n more" 링크로 표시된다.
        dayMaxEvents: true,

        // 캘린더의 상단에 위치한 도구를 설정한다. 이 도구 모음에는 이전/다음 버튼, 오늘 버튼, 제목 표시, 뷰 전환 버튼 등이 포함된다.
        headerToolbar: {
            left: 'title',
            right: 'prev,today,next'
        },

        // 주간 및 일간 보기의 시간 범위 설정
        slotMinTime: '10:00:00',
        slotMaxTime: '22:00:00',

        // 날짜나 주 번호를 클릭할 수 있게 되며, 클릭하면 해당 날짜에 대한 일간 보기 또는 해당 주에 대한 주간 보기로 전환된다.
        navLinks: false,

        // 이벤트 객체의 속성을 변경할 수 있는지에 대한 여부
        editable: false,

        // 캘린더에서 사용하는 언어 및 형식을 설정.
        locale: {
            code: 'ko',
            allDayText: '하루 종일'
        },

        // 이벤트 객체를 포함하는 배열이며, 각 이벤트 객체는 일정(이벤트)을 나타낸다.
        events: [],

        // 이벤트(일정)의 콘텐츠를 커스터마이징하는 데 사용된다. 이벤트의 콘텐츠를 구성하는 DOM 요소를 반환한다. (이벤트가 렌더링될 때 호출된다.)
        // eventContent: function (scheduleWithMetadata) {
        //         //     // 해당 이벤트 객체와 그와 관련된 추가 정보를 포함하는 객체를 매개변수로 전달받는다.
        //         //     const schedule = scheduleWithMetadata.event;
        //         //
        //         //     const container = document.createElement('div');
        //         //     container.style.display = 'flex';
        //         //     container.style.justifyContent = 'flex-end';
        //         //     container.style.alignItems = 'center';
        //         //     container.style.width = "100%";
        //         //     container.style.padding = "3px 12px";
        //         //
        //         //     const title = document.createElement('div');
        //         //     title.innerHTML = schedule.title;
        //         //     title.style.fontSize = "17px";
        //         //     title.style.fontWeight = "700";
        //         //
        //         //     if(scheduleWithMetadata.event.title === "결석") {
        //         //         title.style.color = "#973131";
        //         //     }
        //         //
        //         //     container.appendChild(title);
        //         //
        //         //     return {domNodes: [container]};
        //         // },
        // eventClick: function (info) {
        //     // Close any currently open popovers
        //     if (currentPopover) {
        //         currentPopover.dispose();
        //         currentPopover = null;
        //     }
        //
        //     // Create a new popover if the clicked event is "조퇴"
        //     if (info.event.title === "조퇴") {
        //         const popover = new bootstrap.Popover(info.el, {
        //             title: '조퇴',
        //             content: `${info.event.extendedProps.remark}`,
        //             trigger: 'manual'
        //         });
        //
        //         popover.show();
        //         currentPopover = popover;
        //
        //         // Add event listener for closing the popover
        //         document.addEventListener('click', closePopover);
        //     }
        // }
    });

    // axios.get(`/lms/student/${lectureNumber}/attendanceStatus`)
    //     .then(response => {
    //         const attendanceList = response.data;
    //
    //         const events = attendanceList.map(attendance => {
    //             return {
    //                 title: attendance.title,
    //                 start: attendance.start,
    //                 extendedProps: {
    //                     remark: attendance.remark
    //                 }
    //             };
    //         });
    //         events.forEach(event => {
    //             calendar.addEvent(event);
    //         });
    //     })
    //     .catch(error => {
    //         console.error(error);
    //     })
    //
    // function closePopover(event) {
    //     if (currentPopover && !event.target.closest('.popover') && !event.target.closest('.fc-event')) {
    //         currentPopover.dispose();
    //         currentPopover = null;
    //         document.removeEventListener('click', closePopover);
    //     }
    // }

    calendar.render();
});