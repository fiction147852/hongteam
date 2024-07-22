document.addEventListener('DOMContentLoaded', function () {
    function initChart(totalDays, attendanceDays) {
        const ctx = document.getElementById('attendanceChart').getContext('2d');

        const attendanceChart = new Chart(ctx, {
            type: 'doughnut', // 차트 종류
            data: {
                datasets: [{
                    data: [totalDays - attendanceDays, attendanceDays],
                    backgroundColor: [
                        'whitesmoke',
                        '#27587E'
                    ],
                    borderWidth: 1 // 차트 테두리 두께
                }]
            },
            options: {
                cutoutPercentage: 50,
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: false
                    }
                }
            }
        });
    }


    axios.get(`/lms/student/${lectureNumber}/attendanceStatusCount`)
        .then(response => {
            const data = response.data;

            const totalDays = data.totalDays !== null ? data.totalDays : 0;
            const attendanceDays = data.attendanceDays !== null ? data.attendanceDays : 0;

            initChart(totalDays, attendanceDays);

            document.querySelector("#tardyDays").innerText = data.tardyDays !== null ? data.tardyDays : "";
            document.querySelector("#earlyLeaveDays").innerText = data.earlyLeaveDays !== null ? data.earlyLeaveDays : "";

            const attendancePercentage = totalDays !== 0
                ? Math.round((attendanceDays / totalDays) * 1000) / 10
                : null;

            const donutChartLabel = document.querySelector(".donut-chart-label");
            const donutChart = document.querySelector(".donut-chart-container");
            if (attendancePercentage !== null) {
                donutChartLabel.innerText = `${attendancePercentage}%`;
            } else {
                donutChart.style.display = 'none';
                donutChartLabel.innerText = '출석 정보 없음';  // 또는 다른 적절한 메시지
            }
            donutChartLabel.style.display = 'block';  // 항상 레이블을 표시
        })
        .catch(error => {
            console.error(error);
        });
});