
document.addEventListener('DOMContentLoaded', function () {
    // 현재 페이지
    let currentPage = 1;

    console.log(lectureNumber);
    // 한 화면에 보여줄 데이터의 개수
    const pageSize = 5;

    // 페이지 네비게이션에서 한 번에 보여줄 페이지 번호의 개수
    const pagesToShow = 5;

    // 강의 자료 목록 조회
    const loadLectureMaterials = (page, title) => {

        const params = {
            page: page,
            pageSize: pageSize
        };

        if (title != null && title.trim() !== '') {
            params.title = title;
        }

        axios.get(`/lms/student/${lectureNumber}/lectureMaterials/list`, {params})
            .then(response => {
                const materials = response.data;
                console.log(materials);
                const tbody = document.querySelector('#lecture-materials-tbody');
                tbody.innerHTML = "";

                materials.forEach(material => {
                    const tr = document.createElement("tr");
                    const tdOne = document.createElement("td");
                    const tdTwo = document.createElement("td");
                    const tdThree = document.createElement("td");
                    const tdFour = document.createElement("td");

                    tbody.appendChild(tr);
                    tr.appendChild(tdOne);
                    tr.appendChild(tdTwo);
                    tr.appendChild(tdThree);
                    tr.appendChild(tdFour);

                    tdOne.innerText = material.rowNum;
                    tdTwo.innerText = material.title;
                    tdThree.innerText = material.registrationDate;
                    tdFour.innerText = material.views;
                })
                // 페이지 네이션을 갱신하는 함수
                loadPagination(page, title);
            })
            .catch(error => {
                console.error(error);
            });
    }

    // 페이지네이션을 갱신
    const loadPagination = (currentPage, title) => {

        axios.get(`/lms/student/${lectureNumber}/lectureMaterials/count`, {params: {title}})
            .then(response => {
                const lectureMaterialCount = response.data;
                const totalPage = Math.ceil(lectureMaterialCount / pageSize);
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

                        loadLectureMaterials(page, title);
                    })
                })
            })
            .catch(error => {
                console.error(error);
            });

            document.querySelector('#search-form').addEventListener('submit', function(event) {
                event.preventDefault();

                const title = document.querySelector("#search-title").value;
                loadLectureMaterials(1, title);
            });
    }
    loadLectureMaterials(currentPage);
});
