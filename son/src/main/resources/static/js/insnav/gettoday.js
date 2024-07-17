/*
 * gettoday.js
 */

document.addEventListener('DOMContentLoaded', function() {
	const dateElement = document.getElementById('todayDate');
	const today = new Date();
	const options = { month: 'short', day: 'numeric' };
	const formattedDate = today.toLocaleDateString('en-US', options);
	dateElement.textContent = 'Today : ' + formattedDate;
});