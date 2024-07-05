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
                const tbody = document.querySelector('#tbody__exam-list');
                tbody.innerHTML = "";

                examList.forEach(examInfo => {
                    const tr = document.createElement("tr");
                    tr.innerHTML = `<td>${examInfo.rowNum}</td>
                                    <td>${examInfo.testTitle}</td>
                                    <td>${examInfo.examDate}</td>
                                    <td>${examInfo.participateStatus}</td>
                                    <td>${examInfo.totalScore == null ? 'X' : examInfo.totalScore}</td>`;
                    tbody.appendChild(tr);

                    tr.addEventListener("click", function(event) {
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
                                        console.log(exams);

                                        modalHeader.innerHTML = `<div class="time">남은 시간 : ${exams[0].limitTime}분 00초<br>남은 문제 : ${exams.length}</div>
                                                                 <div class="title">${exams[0].detailSubjectName}</div>
                                                                 <button type="button" class="btn btn-secondary" id="subjectModalButton">제출</button>`;

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
                                                        modal.hide();
                                                    }
                                                });
                                        });

                                        swiperWrapper.innerHTML = ''; // 기존 슬라이드 내용 초기화
                                        swiperThree.update();


                                        // 반복문
                                        exams.forEach((exam, index) => {
                                            const slide = document.createElement('div');
                                            slide.className = 'swiper-slide';

                                            let contentHTML = `<div class="swiper-content">
                                                        <div class="question mb-3">${index + 1}. ${exam.text}（${exam.score}점）</div>`;

                                            if (exam.attachmentFileVO != null) {
                                                contentHTML += `<div class="image">
                                                                    <img src="/lms/images/exam/1.png" alt="도형 이미지">
                                                                </div>`;
                                            }

                                            if (exam.questionTypeCode === '객관식') {
                                                contentHTML += `<div class="options row">
                                                                    <div class="option">
                                                                        <label class="checkbox__label mr-4">
                                                                            <input type="radio" name= "${exam.questionNumber}" value="1">
                                                                            <span class="checkmark">1</span>
                                                                        </label>
                                                                        <div class="align-middle">
                                                                            <span class="optionContent">${exam.optionOne}</span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="option">
                                                                        <label class="checkbox__label mr-4">
                                                                            <input type="radio" name= "${exam.questionNumber}" value="2">
                                                                            <span class="checkmark">2</span>
                                                                        </label>
                                                                        <div class="align-middle">
                                                                            <span class="optionContent">${exam.optionTwo}</span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="option">
                                                                        <label class="checkbox__label">
                                                                            <input type="radio" name= "${exam.questionNumber}" value="3"/>
                                                                            <span class="checkmark">3</span>
                                                                        </label>
                                                                        <span class="optionContent">${exam.optionThree}</span>
                                                                    </div>
                                                                    <div class="option">
                                                                        <label class="checkbox__label">
                                                                            <input type="radio" name= "${exam.questionNumber}" value="4">
                                                                            <span class="checkmark">4</span>
                                                                        </label>
                                                                        <span class="optionContent">${exam.optionFour}</span>
                                                                    </div>
                                                                    <div class="option">
                                                                        <label class="checkbox__label">
                                                                            <input type="radio" name= "${exam.questionNumber}" value="5">
                                                                            <span class="checkmark">5</span>
                                                                        </label>
                                                                        <span class="optionContent">${exam.optionFive}</span>
                                                                    </div>
                                                                </div>`;
                                            } else {
                                                contentHTML += `<div class="subjective align-middle">
                                                                    <input class="subjective__input" type="text" placeholder="정답을 작성하세요." name= "q${exam.questionNumber}"/>
                                                                </div>`;
                                            }
                                            contentHTML += `</div>`;

                                            slide.innerHTML = contentHTML;
                                            swiperWrapper.appendChild(slide);

                                        });
                                    })
                                    .catch(error => {
                                        console.error(error);
                                    })
                            }
                        });
                    });
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
                                                <a class="page-link" style="background-color: #27587E; border-color: #27587E" href="#" data-page="${startPage - 1}">이전</a>
                                             </li>`;
                }

                for (let i = startPage; i <= endPage; i++) {
                    const pageElement = `<li class="page-item ${i === currentPage ? 'active' : ''}">
                                                    <a class="page-link" style="background-color: #27587E; border-color: #27587E" href="#" data-page="${i}">${i}</a>
                                                 </li>`;
                    pagination.innerHTML += pageElement;
                }

                if (endPage < totalPage) {
                    pagination.innerHTML += `<li class="page-item">
                                                <a class="page-link" style="background-color: #27587E; border-color: #27587E"  href="#" data-page="${endPage + 1}">다음</a>
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

        document.querySelector('#search-title').addEventListener('input', function(event) {
            // event.preventDefault();

            const testTitle = this.value;
            loadExamList(1, testTitle, participateStatus);
        });

        document.querySelector("#examListDropDown").addEventListener("change", function (event) {
           const participateStatus = this.value;
            loadExamList(1, testTitle, participateStatus);
        });
    }
    loadExamList(currentPage);
});
// const swiperWrapper = document.querySelector(".modal__exam-info .swiper-wrapper");
// swiperWrapper.innerHTML = "";
//
// const swiperSlide = document.createElement("div");
// const swiperContent = document.createElement("div");
// swiperSlide.className = "swiper-slide";
// swiperContent.className = "swiper-content";
//
// const questionDiv = document.createElement("div");
//
// if('이미지가 있으면') {
//     const imageDiv = document.createElement("div");
//
//     imageDiv.className = "image";
//     swiperContent.appendChild(imageDiv);
// }

    // 없으면 input 태그 위에 마진 값 30px 밑에 마진 값 60px 페이지네이션 top 200px
//
// questionDiv.className = "question";
//
// if('객관식 이면') {
//     for(let i = 1; i <= 5; i++) {
//         const optionsDiv = document.createElement("div");
//         const optionDiv = document.createElement("div");
//         const optionLabel = document.createElement("label");
//         const labelInput = document.createElement("input");
//         const labelNumber = document.createElement("span");
//         const optionSpan = document.createElement("span");
//
//         optionsDiv.className = "options";
//         optionDiv.className = "option";
//         optionLabel.className = "checkbox__label";
//         labelInput.type = "checkbox";
//         labelInput.value = "";
//         labelNumber.className = "checkmark";
//         optionSpan.className = "optionContent";
//
//         swiperContent.appendChild(optionsDiv);
//         optionsDiv.appendChild(optionDiv);
//         optionDiv.appendChild(optionLabel);
//         optionDiv.appendChild(optionSpan);
//         optionLabel.appendChild(labelInput);
//         optionLabel.appendChild(labelNumber);

            // 페이지 네이션.top 속성 : 560px;
//     }
// }
// else {
// const subjectiveDiv = document.createElement("div");
// const subjectiveInput = document.createElement("input");
//
// subjectiveDiv.className ="subjective";
// subjectiveInput.type = "text";
// subjectiveInput.placeholder = "정답을 입력하세요.";
//
// swiperContent.appendChild(subjectiveDiv);
// subjectiveDiv.appendChild(subjectiveInput);
// 페이지 네이션.top 속성 : 620px
// }
//
// swiperWrapper.appendChild(swiperSlide);
// swiperSlide.appendChild(swiperContent);
// swiperContent.appendChild(questionDiv);




/* const modalHeader = document.querySelector(".modal__exam-info .modal-header");

const modalHeaderTime = document.createElement("div");
const modalHeaderTitle = document.createElement("div");

modalHeaderTime.className = "time";
modalHeaderTitle.className = "title";

modalHeader.prepend(modalHeaderTitle);
modalHeader.prepend(modalHeaderTime);
--------------------------------------------






                                  <div class="options">
                                            <div class="option">
                                                <label class="checkbox__label">
                                                    <input type="checkbox" value="1">
                                                    <span class="checkmark">1</span>
                                                </label>
                                                <span class="optionContent">1 + √2 / 5</span>
                                            </div>
                                            <div class="option">
                                                <label class="checkbox__label">
                                                    <input type="checkbox" value="2">
                                                    <span class="checkmark">2</span>
                                                </label>
                                                <span class="optionContent">2 + √3 / 5</span>
                                            </div>
                                            <div class="option">
                                                <label class="checkbox__label">
                                                    <input type="checkbox" value="3">
                                                    <span class="checkmark">3</span>
                                                </label>
                                                <span class="optionContent">1 + √3 / 5</span>
                                            </div>
                                            <div class="option">
                                                <label class="checkbox__label">
                                                    <input type="checkbox" value="4">
                                                    <span class="checkmark">4</span>
                                                </label>
                                                <span class="optionContent">1 + √3 / 6</span>
                                            </div>
                                            <div class="option">
                                                <label class="checkbox__label">
                                                    <input type="checkbox" value="5">
                                                    <span class="checkmark">5</span>
                                                </label>
                                                <span class="optionContent">2 + √2 / 6</span>
                                            </div>
                                        </div>





 */