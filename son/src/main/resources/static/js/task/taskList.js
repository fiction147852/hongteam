document.addEventListener('DOMContentLoaded', function () {
    // 현재 페이지
    let currentPage = 1;

    console.log(lectureNumber);
    // 한 화면에 보여줄 데이터의 개수
    const pageSize = 5;

    // 페이지 네비게이션에서 한 번에 보여줄 페이지 번호의 개수
    const pagesToShow = 5;

    // 강의 자료 목록 조회
    const loadTaskList = (page, title, taskSubmitStatus) => {

        const params = {
            page: page,
            pageSize: pageSize,
            title,
            taskSubmitStatus
        };

        axios.get(`/lms/student/${lectureNumber}/task/list`, {params})
            .then(response => {
                const tasks = response.data;

                const tbody = document.querySelector('#task-tbody');
                tbody.innerHTML = "";

                tasks.forEach(task => {
                    const tr =  document.createElement("tr");
                    tr.innerHTML = `
                                           <td>${task.rowNum}</td>
                                           <td style="cursor: pointer;"><a>${task.title}</a></td>
                                           <td>${task.postDate} ~ ${task.submitDeadline}</td>
                                           <td>${task.taskSubmitStatus}</td>
                                           <td>${task.feedbackStatus}</td>
                                       `;

                    tbody.appendChild(tr);
                    // tdTwo.addEventListener("click", function() {
                    //     window.location.href = `/lms/student/${material.lectureNumber}/lectureMaterials/${material.lectureMaterialNumber}`;
                    // });
                })
                // 페이지 네이션을 갱신하는 함수
                loadPagination(page, title, taskSubmitStatus);
            })
            .catch(error => {
                console.error(error);
            });
    }

    // 페이지네이션을 갱신
    const loadPagination = (currentPage, title, taskSubmitStatus) => {

        axios.get(`/lms/student/${lectureNumber}/task/count`, {params: {title, taskSubmitStatus}})
            .then(response => {
                const taskCount = response.data;
                const totalPage = Math.ceil(taskCount / pageSize);
                const pagination = document.querySelector('#pagination');
                pagination.innerHTML = "";

                // 페이지네이션에서 표시할 페이지 번호 중 첫 번째 페이지 번호
                const startPage = Math.floor((currentPage - 1) / pagesToShow) * pagesToShow + 1;

                // 페이지네이션에서 표시할 페이지 번호 중 마지막 페이지 번호
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

                        loadTaskList(page, title, taskSubmitStatus);
                    })
                })
            })
            .catch(error => {
                console.error(error);
            });
    }
    loadTaskList(currentPage);

    document.querySelector('#search-form button').addEventListener('click', function(event) {
        event.preventDefault();
        const taskSubmitStatus = document.querySelector("#taskStatusDropDown").value;
        const taskTitle = document.querySelector("#search-title").value;
        loadTaskList(1, taskTitle, taskSubmitStatus);
    });

    document.querySelector("#taskStatusDropDown").addEventListener("change", function (event) {
        const taskSubmitStatus = this.value;
        const taskTitle = document.querySelector("#search-title").value;
        loadTaskList(1, taskTitle, taskSubmitStatus);
    });
});
