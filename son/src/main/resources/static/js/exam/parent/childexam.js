document.addEventListener("DOMContentLoaded", function () {

    // 시험 목록 조회 함수
    const loadExamList = (lectureNumber) => {
        const params = {
            lectureNumber
        };

        axios.get(`/lms/parent/${studentNumber}/exam/list`, { params })
            .then(response => {
                const examList = response.data;
                const tbody = document.querySelector('#tbody__exam-list');
                tbody.innerHTML = '';

                examList.forEach(examInfo => {
                    tbody.innerHTML += `<tr>
                                          <td>${examInfo.testNumber}</td>
                                          <td>${examInfo.testTitle}</td>
                                          <td>${examInfo.examDate}</td>
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
    loadExamList(1);
    
});
