/**
 * 
 */
function toggleOptions() {
	    var questionType = document.getElementById('questionType').value;
	    var optionsContainer = document.getElementById('optionsContainer');
	
	    if (questionType === '객관식') {
	        optionsContainer.style.display = 'block';
	    } else {
	        optionsContainer.style.display = 'none';
	    }
	}
	
	function updateDetailSubjects() {
	    var category = document.getElementById('category');
	    var detailSubject = document.getElementById('detailSubject');
	    var selectedCategory = category.value;
	    
	    // 기존 옵션을 모두 제거
	    detailSubject.innerHTML = '';
	    
	    // 선택된 카테고리에 따라 새로운 옵션 추가
	    if (selectedCategory === 'A001') {  // 수학
	        addOption(detailSubject, 'B001', '미적분');
	        addOption(detailSubject, 'B002', '확률과통계');
	        addOption(detailSubject, 'B003', '기하와벡터');
	    } else if (selectedCategory === 'A002') {  // 영어
	        addOption(detailSubject, 'B004', '독해');
	        addOption(detailSubject, 'B005', '문법');
	        addOption(detailSubject, 'B006', '듣기');
	    } else if (selectedCategory === 'A003') {  // 국어
	        addOption(detailSubject, 'B007', '화법과작문');
	        addOption(detailSubject, 'B008', '언어와매체');
	        addOption(detailSubject, 'B009', '문학');
	    }
	   }
	
	function addOption(selectElement, value, text) {
	    var option = document.createElement('option');
	    option.value = value;
	    option.textContent = text;
	    selectElement.appendChild(option);
	}
	
	// 페이지 로드 시 초기 상태 설정
	document.addEventListener('DOMContentLoaded', function() {
	    toggleOptions();
	    updateDetailSubjects();
	// 기존 선택값 복원
    var category = document.getElementById('category');
    if (category.value) {
        updateDetailSubjects();
    }
});