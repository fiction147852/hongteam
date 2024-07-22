/**
 * qinfo.js
 */

document.addEventListener('DOMContentLoaded', function() {
    const optionsDiv = document.getElementById('questionOptions');
    
    console.log('Question Type Code:', questionTypeCode);
    console.log('Options Div:', optionsDiv);

    if (typeof questionTypeCode !== 'undefined' && questionTypeCode === '주관식') {
        console.log('Hiding options');
        console.log(questionTypeCode);
        optionsDiv.style.display = 'none';
    } else {
        console.log('Not hiding options');
    }
});