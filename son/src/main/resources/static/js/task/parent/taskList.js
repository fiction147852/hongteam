$(document).ready(function() {
	console.log("Document ready");
	loadTaskList();
});

function loadTaskList() {
	$.ajax({
		url: `/lms/parent/${studentNumber}/${lectureNumber}/task/list`,
		type: 'GET',
		success: function(data) {
			console.log(data)
			updateTaskTable(data);
		},
		error: function(xhr, status, error) {
			console.error("Error loading task list:", error);
		}
	});
}

function updateTaskTable(tasks) {
	console.log("Updating table with", tasks.length, "tasks");
	let tbody = $("#task-tbody");
	if (tbody.length === 0) {
		console.error("tbody not found");
		return;
	}
	tbody.empty();
	tasks.forEach(function(task) {
		let row = $(`<tr>
            <td>${task.taskNumber}</td>
            <td>${task.title}</td>
            <td>${formatDate(task.postDate)}</td>
            <td>${formatDate(task.submitDeadline)}</td>
            <td>${task.taskSubmitStatus}</td>
        </tr>`);
		tbody.append(row);
	});
	console.log("Table updated");
}

function formatDate(dateString) {
	let date = new Date(dateString);
	return date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월 ' + date.getDate() + '일';
}
