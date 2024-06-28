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
        events: [],

        // event | 클릭된 이벤트에 대한 상세 정보를 담고 있는 속성.
        // el | 클릭된 이벤트를 나타내는 HTML 요소를 나타내는 속성.
        // jsEvent | 클릭 이벤트에 대한 상세 정보를 담고 있는 속성.
        // 이 세 가지 속성을 모두 담고 있는 매개변수를 전달받는다.
        eventClick: function(eventInfo) {
            const eventDate = eventInfo.event.start.getFullYear() + "-0" + (eventInfo.event.start.getMonth() + 1) + "-" + eventInfo.event.start.getDate();

            console.log(eventDate);
            axios.get("student/scheduleDetail?deadlineDate=" +  eventDate)
                .then(response => {
                    const events = response.data;
                    events.forEach(event => {
                        console.log(event);





                    })
                })
                .catch(error => {
                    console.error(error);
                })

        },

        // 이벤트(일정)의 콘텐츠를 커스터마이징하는 데 사용된다. 이벤트의 콘텐츠를 구성하는 DOM 요소를 반환한다. (이벤트가 렌더링될 때 호출된다.)
        eventContent: function(scheduleWithMetadata) {
            // 해당 이벤트 객체와 그와 관련된 추가 정보를 포함하는 객체를 매개변수로 전달받는다.
            const schedule = scheduleWithMetadata.event;

            const container = document.createElement('div');
            container.style.display = 'flex';
            container.style.justifyContent = 'space-between';
            container.style.alignItems = 'center';
            container.style.width = "100%";
            container.style.padding = "3px 12px";

            switch(schedule.extendedProps.type) {
                case "test" :
                    container.style.backgroundColor = "#342649"
                    break;
                case "task" :
                    container.style.backgroundColor = "#2C3D4F";
                    break;
                default :
                    container.style.backgroundColor = "#0D3E2A";
            }

            const title = document.createElement('div');
            title.innerHTML = schedule.title;
            title.style.color = "white";
            container.appendChild(title);

            return { domNodes: [container] };
        },
        // 날짜 셀이 렌더링된 후에 호출 (셀이 DOM 에 추가된 이후 실행)
        dayCellDidMount: function(date) {
            const day = date.date.getDay();

            // if (day === 6) {
            //
            // } else if (day === 0) {
            //
            // }
        },
    });

    axios.get("student/schedule")
        .then(response => {
            const eventData = response.data;

            // (날짜, 제목) 중복된 이벤트를 하나의 이벤트로 결합하기 위해 객체 정의
            const groupedEvents = {};

            eventData.forEach(event => {
                // 날짜(문자열)을 T(날짜와 시간 구분자) 문자를 기준으로 문자열을 나누어 배열을 반환한다. 그 배열의 첫 번째 원소인 날짜를 반환
                const eventDate = event.start.split('T')[0];
                const eventKey = `${eventDate}-${event.title}`;

                if (!groupedEvents[eventKey]) {
                    groupedEvents[eventKey] = {
                        ...event,
                        count: 0
                    };
                }
                groupedEvents[eventKey].count += 1;
            });

            // 그룹화된 이벤트를 캘린더에 추가
            for (const key in groupedEvents) {
                const event = groupedEvents[key];
                if (event.count > 1) {
                    event.title = `${event.title}（${event.count}）`;
                }
                calendar.addEvent(event);
            }

            calendar.render();
        })
        .catch(error => {
            console.error(error);
        });

    // function createSlideTag() {
    //     const swiperDiv = document.querySelector("#eventModal .swiper-wrapper");
    //
    //     const slideDiv = document.createElement("div");
    //     const slideContentDiv = document.createElement("div");
    //     const infoDiv = document.createElement("div");
    //
    //     slideContentDiv.className = "slide-content";
    //     slideDiv.className = "swiper-slide";
    //     infoDiv.className = "subject-info";
    //
    //     swiperDiv.appendChild(slideContentDiv);
    //     slideContentDiv.appendChild(slideDiv);
    //     slideDiv.appendChild(infoDiv);
    // }

    calendar.render();
});

