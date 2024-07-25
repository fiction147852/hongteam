/*
* autoscore.js
*/

function setAutoScoreForAllQuestions() {
	const autoScoreSelect = document.getElementById('autoScore');
	const scoreInputs = document.querySelectorAll('#scores');
	const totalQuestions = scoreInputs.length;
	
	    if (autoScoreSelect.value === '') {
        // 모든 점수 입력 칸을 비움
        scoreInputs.forEach(input => {
            input.value = '';
        });
        updateTotalScore();
        return;
    }

	if (autoScoreSelect.value === 'equal') {
		let remainingScore = 100;
		let baseScore = Math.floor(100 / totalQuestions);
		let extraPoints = 100 % totalQuestions;

		scoreInputs.forEach((input, index) => {
			let score = baseScore;
			if (extraPoints > 0) {
				score += 1;
				extraPoints -= 1;
			}
			input.value = score;
			remainingScore -= score;
		});

		// 반올림 오차로 인한 남은 점수가 있다면 마지막 문제에 추가
		if (remainingScore > 0) {
			scoreInputs[scoreInputs.length - 1].value = parseInt(scoreInputs[scoreInputs.length - 1].value) + remainingScore;
		}
		
	} else if (autoScoreSelect.value === 'random') {
		let remainingScore = 100;
		let remainingQuestions = totalQuestions;

		scoreInputs.forEach((input, index) => {
			if (index === totalQuestions - 1) {
				input.value = remainingScore;
			} else {
				const minScore = 2;
				const maxScore = Math.min(10, remainingScore - (remainingQuestions - 1) * 2);
				const randomScore = Math.floor(Math.random() * (maxScore - minScore + 1)) + minScore;
				input.value = randomScore;
				remainingScore -= randomScore;
				remainingQuestions--;
			}
		});
	} else if(autoScoreSelect.value === '') {
		
	}
	updateTotalScore();
}

function setAutoScore() {
	const autoScoreSelect = document.getElementById('autoScore');
	const scoreInputs = document.querySelectorAll('#scores');
	
	    if (autoScoreSelect.value === '') {
        // 모든 점수 입력 칸을 비움
        scoreInputs.forEach(input => {
            input.value = '';
        });
        updateTotalScore();
        return;
    }

	if (document.querySelector('input[name="selectedQuestions"]')) {
		// 문제 선택 폼의 경우
		const selectedQuestions = document.querySelectorAll('input[name="selectedQuestions"]:checked');

        if (autoScoreSelect.value === 'equal') {
            let remainingScore = 100;
            let baseScore = Math.floor(100 / selectedQuestions.length);
            let extraPoints = 100 % selectedQuestions.length;

            selectedQuestions.forEach((q, index) => {
                const input = q.closest('.swiper-slide').querySelector('#scores');
                let score = baseScore;
                if (extraPoints > 0) {
                    score += 1;
                    extraPoints -= 1;
                }
                input.value = score;
                remainingScore -= score;
            });

            // 반올림 오차로 인한 남은 점수가 있다면 마지막 문제에 추가
            if (remainingScore > 0) {
                const lastInput = selectedQuestions[selectedQuestions.length - 1]
                    .closest('.swiper-slide')
                    .querySelector('#scores');
                lastInput.value = parseInt(lastInput.value) + remainingScore;
            }
            
		} else if (autoScoreSelect.value === 'random') {
			let remainingScore = 100;
			let remainingQuestions = selectedQuestions.length;

			selectedQuestions.forEach((q, index) => {
				const input = q.closest('.swiper-slide').querySelector('#scores');
				if (index === selectedQuestions.length - 1) {
					input.value = remainingScore;
				} else {
					const minScore = 2;
					const maxScore = Math.min(10, remainingScore - (remainingQuestions - 1) * 2);
					const randomScore = Math.floor(Math.random() * (maxScore - minScore + 1)) + minScore;
					input.value = randomScore;
					remainingScore -= randomScore;
					remainingQuestions--;
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
	const scoreInputs = document.querySelectorAll('#scores');
	let total = 0;
	scoreInputs.forEach(input => {
		if (input.value) {
			total += parseInt(input.value);
		}
	});
	document.getElementById('totalScore').textContent = total;
}

document.addEventListener('DOMContentLoaded', function() {
	const scoreInputs = document.querySelectorAll('#scores');
	scoreInputs.forEach(input => {
		input.addEventListener('input', updateTotalScore);
	});

	if (document.querySelector('input[name="selectedQuestions"]')) {
		// 문제 선택 폼의 경우
		const checkboxes = document.querySelectorAll('input[name="selectedQuestions"]');
		checkboxes.forEach(checkbox => {
			checkbox.addEventListener('change', function() {
				if (this.checked) {
					this.closest('.swiper-slide').querySelector('#scores').required = true;
				} else {
					this.closest('.swiper-slide').querySelector('#scores').required = false;
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

function toggleAllCheckboxes(source) {
    var checkboxes = document.getElementsByName('selectedQuestions');
    var limit = Math.min(20, checkboxes.length); // 20개와 실제 체크박스 수 중 작은 값을 선택
    
    for (var i = 0; i < limit; i++) {
        checkboxes[i].checked = source.checked;
    }
}