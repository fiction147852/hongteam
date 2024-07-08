document.addEventListener("DOMContentLoaded", function() {

    document.querySelector(".header-right span").addEventListener("click", function () {
        window.location.href = `/lms/student/${lectureNumber}/lectureMaterials`;
    })
})