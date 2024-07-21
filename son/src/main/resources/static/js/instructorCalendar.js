/**
 * fullcalendar.js
 */
document.addEventListener('DOMContentLoaded', function() {
			var calendarEl = document.getElementById('calendar');

			var calendar = new FullCalendar.Calendar(calendarEl, {
				height : '1000px',
				expandRows: true, // 화면에 맞게 높이 재설정
				slotMinTime: '07:00', // Day 캘린더에서 시작 시간
		        slotMaxTime: '22:00', // Day 캘린더에서 종료 시간
		        views: {
		            timeGrid: {
		                slotMaxTime: '23:00' // timeGrid 뷰에 대해 명시적으로 설정
		            }
		        },
		        customButtons: {
		        	myCustomButton: {
		        		text:"일정추가", 
		        		click: function() {
		        			addNewEvent();
		        		}
		        	}
		        },
		        timeZone: 'Asia/Seoul', 
		        locale: 'kr',        // 언어 설정
		        dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
				headerToolbar : {
					left : 'prevYear,prev,next,nextYear today,myCustomButton',
					center : 'title',
					right: 'dayGridMonth,timeGridWeek,timeGridDay'
				},
				// initialView: 'dayGridMonth', // 초기 로드 될때 보이는 캘린더 화면(기본 설정: 달)
				// selectable: true, // 달력 일자 드래그 선택가능
                selectMirror: true, // 오직 TimeGrid view에만 적용됨, default false
                

                navLinks: true, // 날짜,WeekNumber 클릭 여부, default false
                editable: true, // event(일정) 
                /* 시작일 및 기간 수정가능여부
                eventStartEditable: false,
                eventDurationEditable: true,
                */
                
				titleFormat : function(date) {
					// YYYY년 MM월
					return `${date.date.year}년 ${date.date.month + 1}월`;
					
				},
				// 날짜 선택해서 이벤트 추가 못써서 뺐음
                // Create new event
               //select: function (arg) {
  						/*	Swal.fire({
										html: `
										<div class='mb-7'>새로운 일정 추가</div>
										<div class='fw-bold mb-5'>일정 이름:</div>
										<input type='text' class='form-control mb-5' name='event_name' />
										<div class='fw-bold mb-5'>전체 일정인가요?
										<input type='checkbox' id='allDayEvent' name='allDayEvent' checked /></div>
										<div class='fw-bold mb-5'>시작 시간:</div>
										<input type='datetime-local' class='form-control mb-5' name='start_time' />
										<div class='fw-bold mb-5'>종료 시간:</div>
										<input type='datetime-local' class='form-control mb-5' name='end_time' />
										`,
										icon: "info",
										showCancelButton: true,
										buttonsStyling: false,
										confirmButtonText: "네, 만들게요!",
										cancelButtonText: "아니요, 취소할게요.",
										customClass: {
										confirmButton: "btn btn-primary m-2",
										cancelButton: "btn btn-danger"
										}
							}).then(function (result) {
								if (result.value) {
					                var title = document.querySelector("input[name='event_name']").value;
					                var allDay = document.querySelector("input[name='allDayEvent']").checked;
					                var startTime = document.querySelector("input[name='start_time']").value;
					                var endTime = document.querySelector("input[name='end_time']").value;

					                if (title) {
					                    var newEvent = {
					                        title: title,
					                        start: arg.start,
					                        end: arg.end,
					                        allDay: allDay
					                    };

					                    if (!allDay) {
					                        if (startTime) {
					                            var startDateTime = new Date(arg.start);
					                            var [hours, minutes] = startTime.split(':');
					                            startDateTime.setHours(parseInt(hours), parseInt(minutes));
					                            newEvent.start = startDateTime;
					                        }
					                        if (endTime) {
					                            var endDateTime = new Date(arg.end);

					                            var [hours, minutes] = endTime.split(':');
					                            endDateTime.setHours(parseInt(hours), parseInt(minutes));
					                            newEvent.end = endDateTime;
					                        }
					                    } 

					                    calendar.addEvent(newEvent);
					                }
					                calendar.unselect()
							} else if (result.dismiss === "cancel") {
							Swal.fire({
								text: "일정 생성이 취소되었습니다!",
								icon: "error",
								buttonsStyling: false,
								confirmButtonText: "네, 알겠어요.",
								customClass: {
								confirmButton: "btn btn-primary",
								}
							});
							}
						});
						}, */

                // Delete event
                eventClick: function (arg) {
                    Swal.fire({
                        text: "해당 일정을 삭제하실건가요?",
                        icon: "warning",
                        showCancelButton: true,
                        buttonsStyling: false,
                        confirmButtonText: "네, 삭제하겠습니다.",
                        cancelButtonText: "아니요, 그냥 둘래요",
                        customClass: {
                            confirmButton: "btn btn-primary m-2",
                            cancelButton: "btn btn-danger"
                        }
                    }).then(function (result) {
                        if (result.value) {
                            arg.event.remove()
                        } else if (result.dismiss === "cancel") {
                            Swal.fire({
                                text: "일정이 삭제 되지 않았습니다.",
                                icon: "error",
                                buttonsStyling: false,
                                confirmButtonText: "네, 알겠어요.",
                                customClass: {
                                    confirmButton: "btn btn-primary",
                                }
                            });
                        }
                    });
                },
                dayMaxEvents: true, // Row 높이보다 많으면 +숫자 more 링크 보임
                // 이벤트 객체 필드 document : https://fullcalendar.io/docs/event-object

				events: [
                    {
                    title: 'All Day Event',
                    start: '2024-06-24'
                    },
                    {
                    title: 'Long Event',
                    start: '2024-06-24',
                    end: '2024-06-27'
                    },
                    {
                    groupId: 999,
                    title: 'Repeating Event',
                    start: '2024-06-27T16:00:00'
                    },
                    {
                    groupId: 999,
                    title: 'Repeating Event',
                    start: '2024-06-28T16:00:00'
                    },
                    {
                    title: 'Conference',
                    start: '2024-06-11',
                    end: '2024-06-13'
                    },
                    {
                    title: 'Meeting',
                    start: '2024-06-15T10:30:00',
                    end: '2024-06-15T12:30:00'
                    },
                    {
                    title: 'Lunch',
                    start: '2024-06-06T12:00:00'
                    },
                    {
                    title: 'Meeting',
                    start: '2024-06-06T14:30:00'
                    },
                    {
                    title: 'Happy Hour',
                    start: '2024-06-06T17:30:00'
                    },
                    {
                    title: 'Dinner',
                    start: '2024-06-06T20:00:00'
                    },
                    {
                    title: 'Birthday Party',
                    start: '2024-06-09T07:00:00'
                    },
                ]
            });
			
			function addNewEvent() {
			    Swal.fire({
			        html: `
			        <div class='mb-7'>새로운 일정 추가</div>
			        <div class='fw-bold mb-5'>일정 이름:</div>
			        <input type='text' class='form-control mb-5' name='event_name' />
			        <div class='fw-bold mb-5'>전체 일정인가요?
			        <input type='checkbox' id='allDayEvent' name='allDayEvent' /></div>
			        <div class='fw-bold mb-5'>시작 날짜:</div>
			        <input type='date' class='form-control mb-5' name='start_date' />
			        <div class='fw-bold mb-5'>시작 시간:</div>
			        <input type='time' class='form-control mb-5' name='start_time' />
			        <div class='fw-bold mb-5'>종료 날짜:</div>
			        <input type='date' class='form-control mb-5' name='end_date' />
			        <div class='fw-bold mb-5'>종료 시간:</div>
			        <input type='time' class='form-control mb-5' name='end_time' />
			        `,
			        icon: "info",
			        showCancelButton: true,
			        buttonsStyling: false,
			        confirmButtonText: "네, 만들게요!",
			        cancelButtonText: "아니요, 취소할게요.",
			        customClass: {
			            confirmButton: "btn btn-primary m-2",
			            cancelButton: "btn btn-danger"
			        }
			    }).then(function (result) {
			        if (result.value) {
			            var title = document.querySelector("input[name='event_name']").value;
			            var allDay = document.querySelector("input[name='allDayEvent']").checked;
			            var startDate = document.querySelector("input[name='start_date']").value;
			            var startTime = document.querySelector("input[name='start_time']").value;
			            var endDate = document.querySelector("input[name='end_date']").value;
			            var endTime = document.querySelector("input[name='end_time']").value;

			            if (title) {
			                var newEvent = {
			                    title: title,
			                    allDay: allDay
			                };
							// 전체 일정 설정 시 종료일 + 1
			                if (allDay) {
			                    newEvent.start = startDate;
			                    var end = new Date(endDate);
			                    end.setDate(end.getDate() + 1);
			                    newEvent.end = end.toISOString().split('T')[0];
			                } else {
			                	
			                    newEvent.start = startDate + 'T' + startTime;
			                    newEvent.end = endDate + 'T' + endTime;
			                }

			                calendar.addEvent(newEvent);
			            }
			        } else if (result.dismiss === "cancel") {
			            Swal.fire({
			                text: "일정 생성이 취소되었습니다!",
			                icon: "error",
			                buttonsStyling: false,
			                confirmButtonText: "네, 알겠어요.",
			                customClass: {
			                    confirmButton: "btn btn-primary",
			                }
			            });
			        }
			    });
			}

			calendar.render();

		});