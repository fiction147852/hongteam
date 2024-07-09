document.addEventListener("DOMContentLoaded", function () {
    let currentPage = 1;
    const pageSize = 5;

    // 시험 목록 조회 함수
    const loadExamList = (page, testTitle = '', participateStatus = '') => {
        const params = {
            page,
            pageSize,
            testTitle,
            participateStatus
        };

        axios.get(`/lms/parent/${studentNumber}/exam/list`, { params })
            .then(response => {
                const examList = response.data;
                const tbody = document.querySelector('#tbody__exam-list');
                tbody.innerHTML = '';

                examList.forEach(examInfo => {
                    tbody.innerHTML += `<tr>
                                          <td>${examInfo.rowNum}</td>
                                          <td>${examInfo.testTitle}</td>
                                          <td>${examInfo.examDate}</td>
                                          <td>${examInfo.limitTime}분</td>
                                          <td>${examInfo.totalScore == null ? 'X' : examInfo.totalScore}점</td>
                                          <td><span>${examInfo.participateStatus}</span></td>
                                       </tr>`;
                });
            })
            .catch(error => {
                console.error(error);
            });
    };

    // 초기 시험 목록 로드
    loadExamList(currentPage);

    // 검색 버튼 클릭 이벤트
    document.querySelector('#search-form button').addEventListener('click', function (event) {
        event.preventDefault();
        const testTitle = document.querySelector("#search-title").value;
        const participateStatus = document.querySelector("#examListDropDown").value;
        loadExamList(1, testTitle, participateStatus);
    });

    // 드롭다운 변경 이벤트
    document.querySelector("#examListDropDown").addEventListener("change", function () {
        const testTitle = document.querySelector("#search-title").value;
        const participateStatus = this.value;
        loadExamList(1, testTitle, participateStatus);
    });
});
