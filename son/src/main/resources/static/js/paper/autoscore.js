/*
* autoscore.js
*/

function setAutoScoreForAllQuestions() {
    const autoScoreSelect = document.getElementById('autoScore');
    const scoreInputs = document.querySelectorAll('input[name="scores"]');
    const totalQuestions = scoreInputs.length;

    if (autoScoreSelect.value === 'equal') {
        const score = Math.floor(100 / totalQuestions);
        scoreInputs.forEach((input, index) => {
            input.value = index < totalQuestions - 1 ? score : 100 - (score * (totalQuestions - 1));
        });
    } else if (autoScoreSelect.value === 'random') {
        let remainingScore = 100;
        scoreInputs.forEach((input, index) => {
            if (index === totalQuestions - 1) {
                input.value = remainingScore;
            } else {
                const randomScore = Math.floor(Math.random() * (remainingScore - (totalQuestions - index - 1))) + 1;
                input.value = randomScore;
                remainingScore -= randomScore;
            }
        });
    }
    updateTotalScore();
}

function setAutoScore() {
    const autoScoreSelect = document.getElementById('autoScore');
    const scoreInputs = document.querySelectorAll('input[name="scores"]');
    
    if (document.querySelector('input[name="selectedQuestions"]')) {
        // 문제 선택 폼의 경우
        const selectedQuestions = document.querySelectorAll('input[name="selectedQuestions"]:checked');
        
        if (autoScoreSelect.value === 'equal') {
            const score = Math.floor(100 / selectedQuestions.length);
            selectedQuestions.forEach((q, index) => {
                const input = q.closest('.swiper-slide').querySelector('input[name="scores"]');
                input.value = index < selectedQuestions.length - 1 ? score : 100 - (score * (selectedQuestions.length - 1));
            });
        } else if (autoScoreSelect.value === 'random') {
            let remainingScore = 100;
            selectedQuestions.forEach((q, index) => {
                const input = q.closest('.swiper-slide').querySelector('input[name="scores"]');
                if (index === selectedQuestions.length - 1) {
                    input.value = remainingScore;
                } else {
                    const randomScore = Math.floor(Math.random() * (remainingScore - (selectedQuestions.length - index - 1))) + 1;
                    input.value = randomScore;
                    remainingScore -= randomScore;
                }
            });
        }
    } else {
        // 자동 생성 폼의 경우
        setAutoScoreForAllQuestions();
    }
    updateTotalScore();
}

function updateTotalScore() {
    const scoreInputs = document.querySelectorAll('input[name="scores"]');
    let total = 0;
    scoreInputs.forEach(input => {
        if (input.value) {
            total += parseInt(input.value);
        }
    });
    document.getElementById('totalScore').textContent = total;
}

document.addEventListener('DOMContentLoaded', function() {
    const scoreInputs = document.querySelectorAll('input[name="scores"]');
    scoreInputs.forEach(input => {
        input.addEventListener('input', updateTotalScore);
    });

    if (document.querySelector('input[name="selectedQuestions"]')) {
        // 문제 선택 폼의 경우
        const checkboxes = document.querySelectorAll('input[name="selectedQuestions"]');
        checkboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function() {
                if (this.checked) {
                    this.closest('.swiper-slide').querySelector('input[name="scores"]').required = true;
                } else {
                    this.closest('.swiper-slide').querySelector('input[name="scores"]').required = false;
                }
                updateTotalScore();
            });
        });
    }

    updateTotalScore();
});

// 기존의 limitSelection 함수 (문제 선택 폼에만 해당)
function limitSelection(checkbox) {
    var checkboxes = document.getElementsByName('selectedQuestions');
    var checkedCount = 0;
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            checkedCount++;
        }
    }
    if (checkedCount > 20) {
        checkbox.checked = false;
        alert('최대 20개의 문제만 선택할 수 있습니다.');
    }
}