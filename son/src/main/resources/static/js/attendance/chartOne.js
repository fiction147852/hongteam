document.addEventListener('DOMContentLoaded', function () {
    console.log(lectureNumber);
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
            console.log(data);

            const totalDays = data.totalDays;
            const attendanceDays = data.attendanceDays;

            initChart(totalDays, attendanceDays);

            document.querySelector("#tardyDays").innerText = data.tardyDays;
            document.querySelector("#earlyLeaveDays").innerText = data.earlyLeaveDays;
            document.querySelector(".donut-chart-label").innerText = `${Math.round((attendanceDays / totalDays) * 1000) / 10}%`;
        })
        .catch(error => {
            console.error(error);
        })
});