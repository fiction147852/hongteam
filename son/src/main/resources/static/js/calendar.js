document.addEventListener('DOMContentLoaded', function () {
    const calendarEl = document.getElementById('calendar');

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
        locale: 'kr',

        // 하루에 표시할 수 있는 최대 이벤트 수를 설정. 특정 숫자로 설정하면 그 숫자만큼의 이벤트만 한 번에 표시하고, 추가 이벤트는 "+n more" 링크로 표시된다.
        dayMaxEvents: true,

        // 캘린더의 상단에 위치한 도구를 설정한다. 이 도구 모음에는 이전/다음 버튼, 오늘 버튼, 제목 표시, 뷰 전환 버튼 등이 포함된다.
        headerToolbar: {
            left: 'prev,today,next',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },

        // 주간 및 일간 보기의 시간 범위 설정
        slotMinTime: '10:00:00',
        slotMaxTime: '22:00:00',

        // 날짜나 주 번호를 클릭할 수 있게 되며, 클릭하면 해당 날짜에 대한 일간 보기 또는 해당 주에 대한 주간 보기로 전환된다.
        navLinks: true,

        // 이벤트 객체의 속성을 변경할 수 있는지에 대한 여부
        editable: false,

        // 캘린더에서 사용하는 언어 및 형식을 설정.
        locale: {
            code: 'ko',
            allDayText: '하루 종일'
        },

        // 이벤트 객체를 포함하는 배열이며, 각 이벤트 객체는 일정(이벤트)을 나타낸다.
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

                // FullCalendar 의 이벤트 객체에 추가적인 사용자 정의 속성을 저장하기 위해 사용된다.
                extendedProps: {
                    startTime: '오후 7시'
                }
            },
            {
                // 이벤트 객체의 정보
                title: '확률과 통계',
                start: '2024-06-24T20:00:00',
                end: '2024-06-24T21:00:00',

                // 이벤트 객체의 색상
                backgroundColor: '#F4F5FA',
                borderColor: '#F4F5FA',
                textColor: '#000000',

                // FullCalendar 의 이벤트 객체에 추가적인 사용자 정의 속성을 저장하기 위해 사용된다.
                extendedProps: {
                    startTime: '오후 9시'
                }
            },
            {
                // 이벤트 객체의 정보
                title: '미적분',
                start: '2024-06-30T19:00:00',
                end: '2024-06-30T20:00:00',

                // 이벤트 객체의 색상
                backgroundColor: '#F4F5FA',
                borderColor: '#F4F5FA',
                textColor: '#000000',

                // FullCalendar 의 이벤트 객체에 추가적인 사용자 정의 속성을 저장하기 위해 사용된다.
                extendedProps: {
                    startTime: '오후 7시'
                }
            }
        ],

        // 이벤트(일정)의 콘텐츠를 커스터마이징하는 데 사용된다. 이벤트의 콘텐츠를 구성하는 DOM 요소를 반환한다.
        eventContent: function(scheduleWithMetadata) {
            // 해당 이벤트 객체와 그와 관련된 추가 정보를 포함하는 객체를 매개변수로 전달받는다.

            console.log(scheduleWithMetadata);
            const schedule = scheduleWithMetadata.event;

            const container = document.createElement('div');
            container.style.display = 'flex';
            container.style.justifyContent = 'space-between';
            container.style.alignItems = 'center';
            container.style.width = "100%";
            container.style.padding = "0 12px";

            const title = document.createElement('div');
            title.innerHTML = ` ` + schedule.title;
            title.style.color = "#132743";
            title.style.fontWeight = "800";
            title.style.fontSize = "14px";

            const time = document.createElement('div');
            time.innerHTML = schedule.extendedProps.startTime;
            time.style.color = "#132743";
            time.style.fontWeight = "800";
            time.style.fontSize = "14px";

            container.appendChild(title);
            container.appendChild(time);

            return { domNodes: [container] };
        },
        // 이벤트가 캘린더에 렌더링된 후 실행되는 콜백 함수를 정의하는 데 사용된다.
        eventClick: function(scheduleWithMetadata) {

            // 이벤트 요소에 클릭 이벤트 추가
            const schedule = scheduleWithMetadata.event;

            console.log(schedule.title);

            document.getElementById('eventAssignment').innerText = schedule.extendedProps.assignment || 'X';
            document.getElementById('eventExam').innerText = schedule.extendedProps.exam || 'X';

            const eventModal = new bootstrap.Modal(document.getElementById('eventModal'), {
                keyboard: false
            });
            eventModal.show();
        }
    });

    calendar.render();
});

