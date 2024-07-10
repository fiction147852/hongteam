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

                console.log(tasks);

                const tbody = document.querySelector('#task-tbody');
                tbody.innerHTML = "";

                tasks.forEach(task => {
                    const feedbackIcon = task.feedbackStatus === '피드백 미완료' ? '<i class="fas fa-times"></i>' : `<i class="fa-regular fa-comment-dots feedback-icon" tabindex="0" role="button" data-bs-toggle="popover" data-bs-placement="left" data-bs-custom-class="custom-popover" data-bs-content="${escapeHtml(task.feedback || '')}"></i>`;
                    const tr =  document.createElement("tr");
                    tr.innerHTML = `<td>${task.rowNum}</td>
                                    <td style="cursor: pointer;"><a>${task.title}</a></td>
                                    <td>${task.postDate} ~ ${task.submitDeadline}</td>
                                    <td>${task.taskSubmitStatus}</td>
                                    <td>${feedbackIcon}</td>`;
                    tbody.appendChild(tr);

                    const titleTd = tr.querySelector("td:nth-child(2) a");
                    titleTd.addEventListener("click", (event) => {
                        window.location.href = `/lms/student/${lectureNumber}/task/${task.taskNumber}`;
                    });


                })
                // Popover 초기화
                initPopovers();

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


    // 팝오버 관련
    function escapeHtml(unsafe) {
        if (unsafe == null) return '';
        return unsafe
            .toString()
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#039;");
    }

    function initPopovers() {
        const popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
        popoverTriggerList.forEach(function (popoverTriggerEl) {
            const feedback = popoverTriggerEl.getAttribute('data-bs-content');
            new bootstrap.Popover(popoverTriggerEl, {
                trigger: 'click',
                html: true,
                template: '<div class="popover custom-popover" role="tooltip">' +
                                '<div class="popover-body"></div>' +
                           '</div>',
                content: function() {
                    return `<div class="popover-header">
                                <span></span>
                                <span>피드백</span>
                                <span class="popover-close-button" style="cursor: pointer">X</span>
                            </div>
                            <div class="popover-contentBody">
                                <div class="popover-content">${feedback}</div>
                            </div>`;
                }
            });
        });
    }

    function closePopover(button) {
        const popoverElement = button.closest('.popover');
        const popoverTrigger = document.querySelector('[aria-describedby="' + popoverElement.id + '"]');
        const popover = bootstrap.Popover.getInstance(popoverTrigger);
        if (popover) {
            popover.hide();
        }
    }
});
