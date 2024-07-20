document.addEventListener("DOMContentLoaded", function () {
    const modalElement = document.querySelector('#eventModal');
    const modal = new bootstrap.Modal(modalElement, {
        // 모달 외부 클릭 시 닫히지 않게 설정
        backdrop: 'static',

        // ESC 키로 닫히지 않게 설정
        keyboard: false
    });

    let currentPage = 1;
    const pageSize = 5;
    const pagesToShow = 5;

    let testTitle = '';
    let participateStatus = '';

    let submissionStatus = false;

    // 시간 포맷 함수
    function formatTime(minutes, seconds) {
        const formattedMinutes = String(minutes).padStart(2, '0');
        const formattedSeconds = String(seconds).padStart(2, '0');

        return `${formattedMinutes}분 ${formattedSeconds}초`;
    }

    // 제한 시간 함수
    function countDown(minutes, seconds, timeOutCall) {
        let currentMinutes = minutes;
        let currentSeconds = seconds;

        const timeId = setInterval(() => {
            if(currentMinutes === 0 && currentSeconds === 0) {
                clearInterval(timeId);
                timeOutCall();
            }
            else {
                if(currentSeconds === 0 ) {
                    if(currentMinutes > 0) {
                        currentMinutes--;
                        currentSeconds = 59
                    }
                }
                else {
                    currentSeconds--;
                }
                document.querySelector(".modal__exam-info .time").innerHTML = `<i class="fa-regular fa-clock"></i> ${formatTime(currentMinutes, currentSeconds)}`;
            }
        }, 1000);

        return timeId;
    }

    // 시험 목록 조회
    const loadExamList = (page, testTitle, participateStatus) => {
        const params = {
            page,
            pageSize,
            testTitle,
            participateStatus
        };

        axios.get(`/lms/student/${lectureNumber}/exam/list`, {params})
            .then(response => {
                const examList = response.data;

                const today = new Date();
                const year = today.getFullYear();
                const month = String(today.getMonth() + 1).padStart(2, '0');
                const day = String(today.getDate()).padStart(2, '0');
                const localToday = `${year}-${month}-${day}`;

                const tbody = document.querySelector('#tbody__exam-list');

                tbody.innerHTML = '';

                examList.forEach(examInfo => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                                          <td>${examInfo.rowNum}</td>
                                          <td>${examInfo.testTitle}</td>
                                          <td>${examInfo.examDate}</td>
                                          <td>
                                              <span>${examInfo.participateStatus}</span>
                                          </td>
                                          <td>${examInfo.totalScore == null ? 'X' : examInfo.totalScore}</td>
                                       `;
                    tbody.appendChild(row);

                    if(examInfo.participateStatus === "미응시" && examInfo.examDate === localToday) {
                        const participateStatusTag = row.querySelector("td:nth-child(4) span");
                        participateStatusTag.style.cursor = "pointer";

                        participateStatusTag.addEventListener("click", function(event) {
                            Swal.fire({
                                title: '정말로 시험을 응시하시겠습니까 ?',
                                text: '시험을 응시하면 되돌릴 수 없습니다.',
                                icon: 'warning',
                                showCancelButton: true,
                                confirmButtonText: '응시합니다',
                                cancelButtonText: '취소합니다',
                                reverseButtons: true,

                                // 디자인
                                confirmButtonColor: '#28587E',
                                cancelButtonColor: '#808080b8',
                                customClass: {
                                    icon: 'exam__icon',
                                    cancelButton: 'exam__cancel-button',
                                    title: 'exam__title',
                                    text: 'exam__text'
                                }
                            }).then(result => {
                                if (result.isConfirmed) {
                                    const modalHeader = document.querySelector(".modal__exam-info .modal-header");
                                    const swiperWrapper = document.querySelector(".swiper-wrapper");

                                    axios.get(`/lms/student/${lectureNumber}/exam/${examInfo.testNumber}`)
                                        .then(response => {
                                            const exams = response.data;
                                            const gradingResult = [];

                                            const limitTime = exams[0].limitTime;
                                            const limitTimeFormatted = String(limitTime).padStart(2, '0');

                                            modalHeader.innerHTML = `<div class="time"><i class="fa-regular fa-clock"></i> ${limitTimeFormatted}분 00초</div>
                                                                 <div class="title">${exams[0].detailSubjectName}</div>
                                                                 <button type="button" class="btn btn-secondary" id="subjectModalButton">제출</button>`;

                                            const countDownId = countDown(limitTime, 0, () => {
                                                Swal.fire({
                                                    title: '시험을 종료합니다',
                                                    text: '시험 시간이 모두 경과되었습니다.',
                                                    icon: 'error',
                                                    confirmButtonText: '확인',
                                                    confirmButtonColor: '#28587E',

                                                    customClass: {
                                                        icon: 'exam-end__icon',
                                                        title: 'exam__title',
                                                        text: 'exam__text'
                                                    }
                                                }).then(() => {
                                                    clearInterval(countDownId);

                                                    submissionStatus = true;

                                                    // 객관식
                                                    document.querySelectorAll(".options input:checked").forEach(input => {
                                                        const index = gradingResult.findIndex(element => element.questionNumber === parseInt(input.name));
                                                        gradingResult[index].studentAnswer = input.value;
                                                    })

                                                    // 주관식
                                                    document.querySelectorAll(".subjective__input").forEach(input => {
                                                        const index= gradingResult.findIndex(element => element.questionNumber === parseInt(input.name));
                                                        gradingResult[index].studentAnswer = input.value;
                                                    });

                                                    axios.post(`/lms/student/${lectureNumber}/exam/${examInfo.participateNumber}/insert`, gradingResult, {
                                                        headers: {
                                                            'Content-Type': 'application/json'
                                                        }
                                                    }).then(response => {
                                                        modal.hide();
                                                        loadExamList(currentPage, testTitle, participateStatus);
                                                    }).catch(error => {
                                                        console.error(error);
                                                    });
                                                });
                                            });

                                            modal.show();
                                            document.querySelector(".modal__exam-info #subjectModalButton").addEventListener("click", function() {
                                                Swal.fire({
                                                    title: '정말로 시험을 제출하시겠습니까 ?',
                                                    text: '시험을 제출하시면 되돌릴 수 없습니다.',
                                                    icon: 'warning',
                                                    showCancelButton: true,
                                                    confirmButtonText: '제출합니다',
                                                    cancelButtonText: '취소합니다',
                                                    reverseButtons: true,

                                                    confirmButtonColor: '#28587E',
                                                    cancelButtonColor: '#808080b8',
                                                    customClass: {
                                                        icon: 'exam__icon',
                                                        cancelButton: 'exam__cancel-button',
                                                        title: 'exam__title',
                                                        text: 'exam__text'
                                                    }
                                                }).then(result => {
                                                    if (result.isConfirmed) {
                                                        clearInterval(countDownId);
                                                        submissionStatus = true;
                                                        // 객관식
                                                        document.querySelectorAll(".options input:checked").forEach(input => {
                                                            const index = gradingResult.findIndex(element => element.questionNumber === parseInt(input.name));
                                                            gradingResult[index].studentAnswer = input.value;
                                                        })

                                                        // 주관식
                                                        document.querySelectorAll(".subjective__input").forEach(input => {
                                                            const index= gradingResult.findIndex(element => element.questionNumber === parseInt(input.name));
                                                            gradingResult[index].studentAnswer = input.value;
                                                        });

                                                        axios.post(`/lms/student/${lectureNumber}/exam/${examInfo.participateNumber}/insert`, gradingResult, {
                                                            headers: {
                                                                'Content-Type': 'application/json'
                                                            }
                                                        }).then(response => {
                                                            modal.hide();
                                                            loadExamList(currentPage, testTitle, participateStatus);
                                                        }).catch(error => {
                                                            console.error(error);
                                                        });
                                                    }
                                                });
                                            });

                                            swiperWrapper.innerHTML = ''; // 기존 슬라이드 내용 초기화
                                            swiperThree.update();

                                            // 반복문
                                            exams.forEach((exam, index) => {
                                                gradingResult.push({
                                                    paperNumber: exam.paperNumber,
                                                    participateNumber: examInfo.participateNumber,
                                                    questionNumber: exam.questionNumber,
                                                    studentAnswer: ''
                                                });

                                                const slide = document.createElement('div');
                                                slide.className = 'swiper-slide';

                                                let contentHTML = `<div class="swiper-content">
                                                        <div class="question mb-3">${index + 1}. ${exam.text}（${exam.score}점）</div>`;

                                                if (exam.attachmentFileVO != null) {
                                                    contentHTML += `<div class="image">
                                                                   <img src="/lms/${exam.attachmentFileVO.filePath}/${exam.attachmentFileVO.originalFileName}" alt="보기 이미지" />
                                                                </div>`;
                                                }

                                                if (exam.questionTypeCode === '객관식') {
                                                    contentHTML += `<div class="options row">
                                                                    <div class="option">
                                                                        <label class="checkbox__label mr-3">
                                                                            <input type="radio" name="${exam.questionNumber}" value="1">
                                                                            <span class="checkmark">1</span>
                                                                        </label>
                                                                        <div class="align-middle">
                                                                            <span class="optionContent">${exam.optionOne}</span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="option">
                                                                        <label class="checkbox__label mr-3">
                                                                            <input type="radio" name="${exam.questionNumber}" value="2">
                                                                            <span class="checkmark">2</span>
                                                                        </label>
                                                                        <div class="align-middle">
                                                                            <span class="optionContent">${exam.optionTwo}</span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="option">
                                                                        <label class="checkbox__label mr-3">
                                                                            <input type="radio" name="${exam.questionNumber}" value="3"/>
                                                                            <span class="checkmark">3</span>
                                                                        </label>
                                                                        <span class="optionContent">${exam.optionThree}</span>
                                                                    </div>
                                                                    <div class="option">
                                                                        <label class="checkbox__label mr-3">
                                                                            <input type="radio" name="${exam.questionNumber}" value="4">
                                                                            <span class="checkmark">4</span>
                                                                        </label>
                                                                        <span class="optionContent">${exam.optionFour}</span>
                                                                    </div>
                                                                    <div class="option">
                                                                        <label class="checkbox__label mr-3">
                                                                            <input type="radio" name="${exam.questionNumber}" value="5">
                                                                            <span class="checkmark">5</span>
                                                                        </label>
                                                                        <span class="optionContent">${exam.optionFive}</span>
                                                                    </div>
                                                                </div>`;
                                                } else {
                                                    contentHTML += `<div class="subjective align-middle">
                                                                    <input class="subjective__input" type="text" placeholder="정답을 작성하세요." name= "${exam.questionNumber}" autocomplete="off"/>
                                                                </div>`;
                                                }
                                                contentHTML += `</div>`;

                                                slide.innerHTML = contentHTML;
                                                swiperWrapper.appendChild(slide);

                                            });
                                            // 브라우저가 종료될 때 이벤트
                                            window.addEventListener('beforeunload', function (event) {
                                                clearInterval(countDownId);

                                                if(submissionStatus) {
                                                    return;
                                                }
                                                // 객관식
                                                document.querySelectorAll(".options input:checked").forEach(input => {
                                                    const index = gradingResult.findIndex(element => element.questionNumber === parseInt(input.name));
                                                    gradingResult[index].studentAnswer = input.value;
                                                })

                                                // 주관식
                                                document.querySelectorAll(".subjective__input").forEach(input => {
                                                    const index= gradingResult.findIndex(element => element.questionNumber === parseInt(input.name));
                                                    gradingResult[index].studentAnswer = input.value;
                                                });

                                                axios.post(`/lms/student/${lectureNumber}/exam/${examInfo.participateNumber}/insert`, gradingResult, {
                                                    headers: {
                                                        'Content-Type': 'application/json'
                                                    }
                                                }).then(response => {
                                                    modal.hide();
                                                    loadExamList(currentPage, testTitle, participateStatus);
                                                }).catch(error => {
                                                    console.error(error);
                                                });
                                            })
                                        })
                                        .catch(error => {
                                            console.error(error);
                                        })
                                }
                            });
                        });
                    }
                });
                loadPagination(page, testTitle, participateStatus);
            })
            .catch(error => {
                console.error(error);
            });
    };

    // 페이지네이션을 갱신
    const loadPagination = (currentPage, testTitle, participateStatus) => {

        axios.get(`/lms/student/${lectureNumber}/exam/count`, {params: {testTitle, participateStatus}})
            .then(response => {
                const examListCount = response.data;
                const totalPage = Math.ceil(examListCount / pageSize);
                const pagination = document.querySelector('#pagination');
                pagination.innerHTML = "";

                // 페이지네이션에서 표시할 페이지 번호 중 첫 번째 페이지 번호
                const startPage = Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;

                // 한 화면의 페이지네이션에서 표시할 페이지 번호 중 마지막 페이지 번호
                const endPage = Math.min(startPage + pagesToShow - 1, totalPage);

                if(startPage > 1) {
                    // 링크 클릭 시 상단으로 이동 X
                    pagination.innerHTML = `<li class="page-item">
                                                <a class="page-link" href="#" data-page="${startPage - 1}">이전</a>
                                             </li>`;
                }

                for (let i = startPage; i <= endPage; i++) {
                    const pageElement = `<li class="page-item ${i === currentPage ? 'active' : ''}">
                                                    <a class="page-link" href="#" data-page="${i}">${i}</a>
                                                 </li>`;
                    pagination.innerHTML += pageElement;
                }

                if (endPage < totalPage) {
                    pagination.innerHTML += `<li class="page-item">
                                                <a class="page-link"  href="#" data-page="${endPage + 1}">다음</a>
                                             </li>`;
                }

                document.querySelectorAll('.page-link').forEach(link => {

                    link.addEventListener("click", function(event) {
                        event.preventDefault();

                        const page = parseInt(this.getAttribute('data-page'));
                        currentPage = page;

                        loadExamList(page, testTitle, participateStatus);
                    })
                })
            })
            .catch(error => {
                console.error(error);
            });
    }
    loadExamList(currentPage);

    document.querySelector('#search-form button').addEventListener('click', function(event) {
        event.preventDefault();
        const participateStatus = document.querySelector("#examListDropDown").value;
        const testTitle = document.querySelector("#search-title").value;
        loadExamList(1, testTitle, participateStatus);
    });

    document.querySelector("#examListDropDown").addEventListener("change", function (event) {
        const participateStatus = this.value;
        const testTitle = document.querySelector("#search-title").value;
        loadExamList(1, testTitle, participateStatus);
    });
});