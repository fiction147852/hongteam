document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');

    if (!calendarEl) {
        console.error('캘린더 요소를 찾을 수 없습니다.');
        return;
    }

    const style = document.createElement('style');
    style.innerHTML = `
                .fc-event-title {
                    font-size: 12px;
                }
                .custom-event {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                }
                .event-day-count {
                    background-color: #F4F5FA;
                    color: white;
                    width: 30px;
                    height: 30px;
                    display: flex;
                    justify-content: center;
                    align-items: center;
                    font-size: 12px;
                    color: black;
                }
            `;
    document.head.appendChild(style);

    const calendar = new FullCalendar.Calendar(calendarEl, {
        height: '1000px',
        expandRows: true, // 화면에 맞게 높이 재설정
        timeZone: 'Asia/Seoul',
        locale: 'kr',        // 언어 설정
        dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
        headerToolbar: {
            left: 'prev,today,next',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        selectMirror: true, // 오직 TimeGrid view에만 적용됨, default false
        navLinks: true, // 날짜,WeekNumber 클릭 여부, default false

        // 이벤트 객체(일정)에 대한 수정 여부를 설정하는 속성 (이벤트 객체에 대해 수정할 수 없다.)
        editable: false,

        // FullCalendar 의 event 객체는 일정을 나타낸다.
        events: [
            {
                // 이벤트 객체의 정보
                title: '미적분',
                start: '2024-06-24T19:00:00',
                end: '2024-06-24T20:00:00',

                // 이벤트 객체의 색상
                backgroundColor: '#F4F5FA',
                borderColor: '#F4F5FA',
                textColor: '#000000',

                // FullCalendar 의 이벤트 객체에 추가적인 사용자 정의 데이터를 저장하기 위해 사용되는 속성
                extendedProps: {
                    startTime: '오후 7시'
                }
            }
        ],
        eventContent: function(arg) {                    const event = arg.event;
            const eventI = arg.event;
            const startTime = eventI.extendedProps.startTime;
            const endTime = eventI.extendedProps.endTime;
            const title = document.createElement('div');
            title.innerHTML = eventI.title;
            const timeEl = document.createElement('div');
            timeEl.className = 'event-time';
            timeEl.innerHTML = `${startTime}`;

            const container = document.createElement('div');
            container.className = 'custom-event';
            container.appendChild(title);
            container.appendChild(timeEl);

            return { domNodes: [container] };
        }
    });

    calendar.render();
});