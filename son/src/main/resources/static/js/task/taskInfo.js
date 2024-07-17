document.addEventListener("DOMContentLoaded", function() {
    const tbody = document.querySelector("#submitted-files");
    const fileInput = document.querySelector("#file-input");

    // 파일 데이터를 임시로 저장하기 위해서 사용하였다.
    let dataTransfer = new DataTransfer();

    if(attachmentFileList.length > 0) {
        attachmentFileList.forEach(file => {
            const fileName = file.originalFileName;
            const fileSize = file.fileSize;
            const fileType = fileName.split(".")[1] ;

            const existingFile = new File([new ArrayBuffer(fileSize)], fileName, {
                type: fileType,
                lastModified: new Date().getTime()
            });

            dataTransfer.items.add(existingFile);
        });
        fileInput.files = dataTransfer.files;

        updateFileTable();
    }

    // 파일 입력 요소의 값이 변경될 때 발생하는 이벤트
    fileInput.addEventListener("change", function(event) {
        const files = this.files;

        Array.from(files).forEach(file => {
            if (!isFileAlreadyAdded(file.name)) {
                // DataTransferItemList 객체에 파일을 담는다.
                dataTransfer.items.add(file);
            }
        });
        fileInput.files = dataTransfer.files;
        updateFileTable();
    });

    // 목록 버튼 눌렀을 때 목록 페이지로 이동
    document.querySelector(".header-right span").addEventListener("click", function () {
        window.location.href = `/lms/student/${lectureNumber}/task`;
    });

    // 제출 버튼 눌렀을 때 서버로 전송
    const submitButton = document.querySelector(".btn-submit");
    if (submitButton) {
        submitButton.addEventListener("click", function(event) {
            event.preventDefault();

            if(fileInput.files.length === 0) {
                Swal.fire({
                    icon: 'error',
                    title: '제출 실패',
                    text: '하나 이상의 과제를 제출해야 합니다.',
                    customClass: {
                        icon: 'task__icon',
                        title: 'task__title',
                        text: 'task__text',
                        confirmButton: 'task__confirm-button'
                    }
                });
            }
            else {
                document.getElementById('taskForm').submit();
            }

        });
    }

    const modifyButton = document.querySelector(".btn-modify");
    if (modifyButton) {
        modifyButton.addEventListener("click", function(event) {
            event.preventDefault();
            if(fileInput.files.length === 0) {
                Swal.fire({
                    icon: 'error',
                    title: '제출 실패',
                    text: '하나 이상의 과제를 제출해야 합니다.',

                    customClass: {
                        icon: 'task__icon',
                        title: 'task__title',
                        text: 'task__text',
                        confirmButton: 'task__confirm-button'
                    }
                });
            }
            else {
                Swal.fire({
                    title: '정말로 과제를 수정하시겠습니까 ?',
                    text: '기존에 제출한 파일은 삭제됩니다.',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: '수정합니다',
                    cancelButtonText: '취소합니다',
                    reverseButtons: true,

                    // 디자인
                    confirmButtonColor: '#28587E',
                    cancelButtonColor: '#808080b8',
                    customClass: {
                        icon: 'task__icon',
                        cancelButton: 'task__cancel-button',
                        title: 'task__title',
                        text: 'task__text'
                    }
                }).then(result => {
                    if(result.isConfirmed) {
                        axios.delete(`/lms/student/${lectureNumber}/task/${taskNumber}`)
                            .then(response => {
                                document.getElementById('taskForm').submit();
                            })
                            .catch(error => {
                                console.error(error);
                            });
                    }
                });
            }
        });
    }

    // 테이블 업데이트 함수
    function updateFileTable() {
        tbody.innerHTML = ''; // 테이블 내용 초기화
        Array.from(dataTransfer.files).forEach(file => {
            addFileToTable(file.name, file.size);
        });
    }

    // 파일 존재 여부 확인 함수
    function isFileAlreadyAdded(fileName) {
        return Array.from(dataTransfer.files).some(file => file.name === fileName);
    }

    // 파일을 테이블에 추가하는 함수
    function addFileToTable(fileName, fileSize) {
        const tr = document.createElement("tr");
        tr.style.height = "30px";

        const fileType = fileName.split(".")[1];

        tr.innerHTML += `<td>${fileName.split(".")[0]}</td>
                         <td>${fileType}</td>
                         <td>${formatFileSize(fileSize)}</td>
                         <td>
                             <span class="delete-btn" style="cursor: pointer">X</span>
                         </td>`;

        tbody.appendChild(tr);

        tr.querySelector(".delete-btn").addEventListener("click", function (event) {
            const deleteFileName = this.closest("tr").querySelector("td:nth-of-type(1)").innerText;
            const deleteFileType = this.closest("tr").querySelector("td:nth-of-type(2)").innerText;
            const fullFileName = `${deleteFileName}.${deleteFileType}`;

            dataTransfer = new DataTransfer();
            Array.from(fileInput.files).forEach(file => {
                if (file.name !== fullFileName) {
                    dataTransfer.items.add(file);
                }
            });
            fileInput.files = dataTransfer.files;

            this.closest("tr").remove();
        });
    }

    // 파일 크기 단위 함수
    function formatFileSize(bytes) {
        const sizes = ['B', 'KB', 'MB', 'GB', 'TB'];
        const i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)), 10); // Calculate the index of the appropriate unit
        return `${(bytes / Math.pow(1024, i)).toFixed(2)} ${sizes[i]}`; // Convert the size to the appropriate unit and format it
    }
});