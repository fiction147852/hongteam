/**
 * 
 */

		//해당 날 불가능한 시간대를 가진리스트 불러오기 
		async function getDayOfImp(date){
			let data = [];
			await $.ajax({
				type: 'get',
				url: 'ad/'+date, // 데이터를 주고받을 파일 주소 입력
				success: function(result){
// 					console.log(result);
					data = result;
				},
				error: function(){  
					console.error('서버 요청 실패');
				}
			});
			return data;
		}
		
		async function getWeekDayPos(weekday){
			let koParam = decodeURI(decodeURIComponent(weekday));
			let str = "";
			await $.ajax({
				type: 'get',
				url: `wkday/${koParam}`,
				success : function(result){
					 str = result
				},
				error: function () {}
				
			})
			return str;
			
		}
		
		
		
		document.addEventListener('DOMContentLoaded', function() {
	        var calendarEl = document.getElementById('calendar');


            var eventSchedule = counselEvent.map(function(counEv) {
                var dateTimeString = dayjs(counEv.reservationDate).format('YYYY-MM-DD') 
                                    + 'T' + counEv.timeCode + ':00:00';
                return {
                    title: counEv.parentName + "상담",
                    start: dateTimeString
                };
            });

	        
			plugins: [ 'dayGrid','interaction' ]
			var calendar = new FullCalendar.Calendar(calendarEl, {
				height : '1000px',
				expandRows: true, // 화면에 맞게 높이 재설정
				slotMinTime: '12:00', // Day 캘린더에서 시작 시간
		        slotMaxTime: '22:00', // Day 캘린더에서 종료 시간
		        views: {
		            timeGrid: {
		                slotMaxTime: '23:00' // timeGrid 뷰에 대해 명시적으로 설정
		            }
		        },
		        customButtons: {
		        	myCustomButton: {
		        		text:"주간시간", 
		        		click: function() {
		        			$("#weekTime").modal("show")
		        		}
		        	},		        
		        	myCustomButton1: {
		        		text:"일정", 
		        		click: function() {
		        			$("#weekTime").modal("show")
		        		}
		        	}		        
		        },
		        //해당날 클릭시 이벤트 
		        dateClick: async function(info) {
					
					
		            var dayOfWeek = info.date.getDay(); // 0: 일요일, 6: 토요일

		            if (dayOfWeek === 0 || dayOfWeek === 6) { // 주말인 경우
		                return; // 클릭 이벤트 무시
		            }
					let dateObj = info.date; 
	        	       
	        	    // 모달 날짜변경
                    $('#modalDate').text(getDateFormat(dateObj, 3));
                    $('#modalDateParam').val(getDateFormat(dateObj, 2));
                    $('#dayTime').modal('show');


			        //예약 시간대 
			        updateDayTimeSetting();

                },
		        
		        timeZone: 'Asia/Seoul', 
		        locale: 'ko',        // 언어 설정
		        dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)
				headerToolbar : {
					left : 'prev,today,next myCustomButton'/*myCustomButton1*/,
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
					return `${date.date.year}년 ${date.date.month + 1}월 `;
					
				},
				// Delete event
                dayMaxEvents: true, // Row 높이보다 많으면 +숫자 more 링크 보임
                // 이벤트 객체 필드 document : https://fullcalendar.io/docs/event-object

                events: eventSchedule
                
            });

			calendar.render();
			


		});
		
		