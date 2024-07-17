document.addEventListener('DOMContentLoaded', function () {
    function initChart(totalDays, attendanceDays) {
        const ctx = document.getElementById('attendanceChart').getContext('2d');

        const attendanceChart = new Chart(ctx, {
            type: 'doughnut', // 차트 종류
            data: {
                datasets: [{
                    data: [totalDays - attendanceDays, attendanceDays], // 결석 일수와 출석 일수
                    backgroundColor: [
                        'white',
                        '#27587E'
                    ],
                    borderWidth: 1 // 차트 테두리 두께
                }],
                labels: ['결석', '출석'] // 범례에 표시될 라벨
            },
            options: {
                cutout: "50%", // 도넛 차트의 가운데 부분 비율 (0-100%)
                responsive: true, // 차트가 반응형으로 크기가 조절되도록 설정
                maintainAspectRatio: false, // 차트의 가로 세로 비율을 유지하지 않음
                plugins: {
                    legend: {
                        display: true, // 범례를 표시함
                        position: 'bottom' // 범례 위치를 아래로 설정
                    },
                    tooltip: {
                        enabled: false // 툴팁을 표시하지 않음
                    }
                }
            }
        });
    }


    axios.get(`/lms/student/${lectureNumber}/attendanceStatusCount`)
        .then(response => {
            const data = response.data;

            const totalDays = data.totalDays;
            const attendanceDays = data.attendanceDays;

            initChart(totalDays, attendanceDays);

            document.querySelector("#tardyDays").innerText = data.tardyDays;
            document.querySelector("#earlyLeaveDays").innerText = data.earlyLeaveDays;
        })
        .catch(error => {
            console.error(error);
        })
});