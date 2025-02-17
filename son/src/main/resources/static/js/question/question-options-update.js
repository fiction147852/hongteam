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
	    	addOption(detailSubject, 'B001', '문학');
	        addOption(detailSubject, 'B002', '화법과 작문');
	        addOption(detailSubject, 'B003', '언어와 매체');
	    } else if (selectedCategory === 'A002') {  // 영어
	        addOption(detailSubject, 'B004', '독해');
	        addOption(detailSubject, 'B005', '문법');
	    } else if (selectedCategory === 'A003') {  // 국어
	        addOption(detailSubject, 'B006', '미적분');
	        addOption(detailSubject, 'B007', '확률과 통계');
	        addOption(detailSubject, 'B008', '기하와 벡터');
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