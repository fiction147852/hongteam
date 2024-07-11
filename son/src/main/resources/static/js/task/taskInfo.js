document.addEventListener("DOMContentLoaded", function() {
    const fileInput = document.querySelector("#file-input");
    const uploadedFiles = new Set();

    fileInput.addEventListener("change", function(event) {
        const files = event.currentTarget.files;
        const tbody = document.querySelector("#submitted-files");

        Array.from(files).forEach(file => {
            if (!uploadedFiles.has(file.name)) {
                uploadedFiles.add(file.name);
                console.log(uploadedFiles);
                const tr = document.createElement("tr");
                tr.style.height = "30px";

                const fileType = file.type === "" ? file.name.split(".")[1] : file.type.split("/")[1];


                tr.innerHTML += `<td>${file.name.split(".")[0]}</td>
                                 <td>${fileType}</td>
                                 <td>${formatFileSize(file.size)}</td>
                                 <td>
                                     <span class="delete-btn" style="cursor: pointer">X</span>
                                 </td>`;

                tr.querySelector(".delete-btn").addEventListener("click", function (event) {
                    const deleteFileName = this.closest("tr").querySelector("td:nth-of-type(1)").innerText;
                    const deleteFileType = this.closest("tr").querySelector("td:nth-of-type(2)").innerText;

                    uploadedFiles.delete(`${deleteFileName}.${deleteFileType}`);
                    console.log(uploadedFiles);
                    event.currentTarget.closest("tr").remove();
                });

                tbody.appendChild(tr);
            }
        });
    });

    document.querySelector(".header-right span").addEventListener("click", function () {
        window.location.href = `/lms/student/${lectureNumber}/task`;
    });

    // 함수 단위 함수
    function formatFileSize(bytes) {
        const sizes = ['B', 'KB', 'MB', 'GB', 'TB'];
        const i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)), 10); // Calculate the index of the appropriate unit
        return `${(bytes / Math.pow(1024, i)).toFixed(2)} ${sizes[i]}`; // Convert the size to the appropriate unit and format it
    }
});