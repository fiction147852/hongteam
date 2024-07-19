document.addEventListener('DOMContentLoaded', function () {
    // 세션 스토리지 초기화
    sessionStorage.removeItem('selectedLectures');

    // 현재 페이지
    let currentPage = 1;

    // 한 화면에 보여줄 데이터의 개수
    const pageSize = 6;

    // 페이지 네비게이션에서 한 번에 보여줄 페이지 번호의 개수
    const pagesToShow = 5;

    // 세션 스토리지에서 선택된 강의 불러오기
    const getSelectedLectures = () => {
        const selectedLectures = sessionStorage.getItem('selectedLectures');
        return selectedLectures ? JSON.parse(selectedLectures) : [];
    };

    // 세션 스토리지에 선택된 강의 저장
    const saveSelectedLectures = (selectedLectures) => {
        sessionStorage.setItem('selectedLectures', JSON.stringify(selectedLectures));
    };

    const saveCompletedLectures = (completedLectures) => {
        localStorage.setItem('completedLectures', JSON.stringify(completedLectures));
    };

    const getCompletedLectures = () => {
        const completedLectures = localStorage.getItem('completedLectures');
        return completedLectures ? JSON.parse(completedLectures) : [];
    };

    const updateLectureUI = () => {
        const completedLectures = getCompletedLectures();
        completedLectures.forEach(lectureNumber => {
            const checkbox = document.querySelector(`#checkbox-${lectureNumber}`);
            const button = document.querySelector(`button[onclick="toggleSelect(this, 'checkbox-${lectureNumber}')"]`);
            if (checkbox) {
                checkbox.disabled = true;
            }
            if (button) {
                button.disabled = true;
                button.style.backgroundColor = '#d3d3d3'; // 버튼 배경색 변경
                button.style.color = '#000000'; // 버튼 텍스트 색상 변경 (옵션)
                button.textContent = '신청 완료';
            }
        });
    };

    // 페이지 로드 시 UI 업데이트
    updateLectureUI();

    // 강의 자료 목록 조회
    const courseRegistrationList = (page, subjectName) => {
        const params = {
            page,
            pageSize,
            subjectName
        };

        axios.get(`/lms/student/courseRegistration/list`, { params })
            .then(response => {
                const lectures = response.data;
                const cardContainer = document.querySelector(".course-card");
                cardContainer.innerHTML = "";

                console.log(lectures);
                const selectedLectures = getSelectedLectures();
                lectures.forEach(lecture => {
                    const card = document.createElement("div");
                    card.className = "card";

                    const formattedCost = Number(lecture.lectureCost).toLocaleString();
                    const isSelected = selectedLectures.includes(lecture.lectureNumber.toString());
                    card.innerHTML = `<div class="card-header" style="font-weight: 800">${lecture.lectureTitle}</div>
                                      <div class="card-content">
                                         <p>
                                            <i class="fas fa-book"></i> 
                                            <span>${lecture.subjectName}</span>
                                         </p>   
                                         <p>
                                            <i class="fas fa-book-reader"></i>
                                            <span>${lecture.detailSubjectName}（${lecture.lectureLevelCode}）</span>
                                         </p>      
                                         <p>
                                            <i class="fas fa-clock"></i>
                                            <span>${lecture.weekdaysCode} ${lecture.timeCode} : 00 ~ ${Number(lecture.timeCode) + 1} : 00</span>
                                         </p>
                                         <p>
                                            <i class="fas fa-calendar-alt"></i>
                                            <span>${lecture.lectureStartDate} ~ ${lecture.lectureEndDate}</span>   
                                         </p>
                                         <p>
                                            <i class="fa-solid fa-user"></i>
                                            <span>${lecture.instructorName}</span>
                                         </p>
                                         <p>
                                            <i class="fa-solid fa-sack-dollar"></i>
                                            <span>${formattedCost}원</span>
                                         </p>
                                      </div>
                                      <div class="card-footer">
                                        <button type="button" class="select-btn ${isSelected ? 'selected' : ''}" onclick="toggleSelect(this, 'checkbox-${lecture.lectureNumber}')" style="font-weight: 600">${isSelected ? '선택 취소' : '선택'}</button>
                                        <input type="checkbox" id="checkbox-${lecture.lectureNumber}" style="display: none;" ${isSelected ? 'checked' : ''}>
                                      </div>`
                    cardContainer.appendChild(card);
                });
                updateLectureUI(); // 여기서도 업데이트 UI 호출
                loadPagination(page, subjectName);
            })
            .catch(error => {
                console.error(error);
            });
    };

    // 페이지네이션을 갱신
    const loadPagination = (currentPage, subjectName) => {
        axios.get(`/lms/student/courseRegistration/count`, { params: { subjectName } })
            .then(response => {
                const lectureCount = response.data;
                const totalPage = Math.ceil(lectureCount / pageSize);
                const pagination = document.querySelector('#pagination');
                pagination.innerHTML = "";

                // 페이지네이션에서 표시할 페이지 번호 중 첫 번째 페이지 번호
                const startPage = Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;

                // 페이지네이션에서 표시할 페이지 번호 중 마지막 페이지 번호
                const endPage = Math.min(startPage + pagesToShow - 1, totalPage);

                if (startPage > 1) {
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
                                                <a class="page-link" href="#" data-page="${endPage + 1}">다음</a>
                                             </li>`;
                }

                document.querySelectorAll('.page-link').forEach(link => {
                    link.addEventListener("click", function (event) {
                        event.preventDefault();
                        const page = parseInt(this.getAttribute('data-page'));
                        currentPage = page;
                        courseRegistrationList(page, subjectName);
                    });
                });
            })
            .catch(error => {
                console.error(error);
            });
    };
    courseRegistrationList(currentPage);

    document.querySelector("#subjectNameDropDown").addEventListener("change", function (event) {
        const subjectName = this.value;
        courseRegistrationList(1, subjectName);
    });

    window.toggleSelect = function (button, checkboxId) {
        const checkbox = document.getElementById(checkboxId);
        checkbox.checked = !checkbox.checked;
        button.classList.toggle('selected');
        button.textContent = checkbox.checked ? '선택 취소' : '선택';

        const lectureNumber = checkboxId.split('-')[1];
        let selectedLectures = getSelectedLectures();

        if (checkbox.checked) {
            selectedLectures.push(lectureNumber);
        } else {
            selectedLectures = selectedLectures.filter(id => id !== lectureNumber);
        }

        saveSelectedLectures(selectedLectures);
    };

    document.querySelector(".btn-container button").addEventListener("click", function() {
        const selectedLectures = getSelectedLectures();
        if (selectedLectures.length === 0) {
            Swal.fire({
                title: '선택된 강의가 없습니다',
                text: '수강할 강의를 선택해주세요.',
                icon: 'warning',
                confirmButtonText: '확인',
                confirmButtonColor: '#28587E'
            });
            return;
        }

        Swal.fire({
            title: '정말로 강의를 신청하시겠습니까?',
            text: '수강 신청 후에도 변경이나 취소가 가능합니다.',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '신청합니다',
            cancelButtonText: '취소합니다',
            reverseButtons: true,
            confirmButtonColor: '#28587E',
            cancelButtonColor: '#808080b8',
            customClass: {
                icon: 'registration__icon',
                cancelButton: 'registration__icon__cancel-button',
                title: 'registration__icon__title',
                text: 'registration__icon__text'
            }
        }).then(result => {
            if (result.isConfirmed) {
                const selectedLecturesData = selectedLectures.map(lectureNumber => {
                    return {
                        lectureNumber: parseInt(lectureNumber),
                        studentNumber: studentNumber // 이 변수가 실제로 선언되어 있는지 확인하세요.
                    };
                });

                axios.post('/lms/student/courseRegistration/insert', selectedLecturesData)
                    .then(() => {
                        const lectureNumbers = selectedLectures.map(lectureNumber => lectureNumber.toString());
                        const completedLectures = getCompletedLectures().concat(lectureNumbers);
                        saveCompletedLectures(completedLectures); // 로컬 스토리지에 저장

                        selectedLectures.forEach(lectureNumber => {
                            const checkbox = document.querySelector(`#checkbox-${lectureNumber}`);
                            const button = document.querySelector(`button[onclick="toggleSelect(this, 'checkbox-${lectureNumber}')"]`);
                            if (checkbox) {
                                checkbox.disabled = true;
                            }
                            if (button) {
                                button.disabled = true;
                                button.style.backgroundColor = '#d3d3d3'; // 버튼 배경색 변경
                                button.style.color = '#000000'; // 버튼 텍스트 색상 변경 (옵션)
                                button.textContent = '신청 완료';
                            }
                        });
                        sessionStorage.removeItem('selectedLectures');

                        Swal.fire({
                            title: '신청 완료',
                            text: '강의 신청이 완료되었습니다.',
                            icon: 'success',
                            confirmButtonText: '확인',
                            confirmButtonColor: '#28587E'
                        });
                    })
                    .catch(error => {
                        console.error('수강 신청 중 오류가 발생했습니다:', error);
                        Swal.fire({
                            title: '오류 발생',
                            text: '수강 신청 중 오류가 발생했습니다. 다시 시도해 주세요.',
                            icon: 'error',
                            confirmButtonText: '확인',
                            confirmButtonColor: '#28587E'
                        });
                    });
            }
        });
    });
});
