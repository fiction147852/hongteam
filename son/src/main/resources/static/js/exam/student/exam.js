document.addEventListener("DOMContentLoaded", function () {
    let currentPage = 1;

    // 한 화면에 보여줄 데이터의 개수
    const pageSize = 5;

    // 페이지 네비게이션에서 한 번에 보여줄 페이지 번호의 개수
    const pagesToShow = 5;

    let testTitle = '';
    let participateStatus = '';

    // 시험 목록 조회
    const loadExamList = (page, testTitle, participateStatus) => {

        const params = {
            page: page,
            pageSize: pageSize,
            testTitle: testTitle,
            participateStatus: participateStatus,
        };

        axios.get(`/lms/student/${lectureNumber}/exam/list`, {params})
            .then(response => {
                const examList = response.data;
                const tbody = document.querySelector('#lecture-materials-tbody');
                tbody.innerHTML = "";

                examList.forEach(examInfo => {
                    const tr = document.createElement("tr");
                    const tdOne = document.createElement("td");
                    const tdTwo = document.createElement("td");
                    const tdThree = document.createElement("td");
                    const tdFour = document.createElement("td");
                    const tdFive = document.createElement("td");

                    tbody.appendChild(tr);
                    tr.appendChild(tdOne);
                    tr.appendChild(tdTwo);
                    tr.appendChild(tdThree);
                    tr.appendChild(tdFour);
                    tr.appendChild(tdFive);

                    tdOne.innerText = examInfo.rowNum;
                    tdTwo.innerText = examInfo.testTitle;
                    tdThree.innerText = examInfo.examDate;
                    tdFour.innerText = examInfo.participateStatus;
                    tdFive.innerText = examInfo.totalScore == null ? 'X' : examInfo.totalScore;
                })
                // 페이지 네이션을 갱신하는 함수
                loadPagination(page, testTitle, participateStatus);
            })
            .catch(error => {
                console.error(error);
            });
    }

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

