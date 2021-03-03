const tForm = '#ticket-form';
const errorClass = 'error-input-highlight';

function writeNumberIntoIndexColumn() {
	let indexColumn = $('.index-col');
	let countAmount = indexColumn.parent().parent().children().length;

	for (let i = 1; i < countAmount; i++) {
		indexColumn[i].innerHTML = i + '.';
	}
}

writeNumberIntoIndexColumn();

function sortByColumn() {
	let rows = Array.from(document.querySelectorAll('.block__row'));
	let sortSelector = '.' + this.innerHTML.toLowerCase();
	let orderedRows = rows.sort(function (a, b) {
		if (a.querySelector(sortSelector) != null && b.querySelector(sortSelector) != null) {
			if (a.querySelector(sortSelector).innerHTML < b.querySelector(sortSelector).innerHTML) {
				return -1;
			} else {
				return1;
			}
		}
	});

	orderedRows.forEach(node => {
		document.querySelector('.block').appendChild(node);
	});

	writeNumberIntoIndexColumn();
}

$(".block__title:not(.index-col > .block__title)").click(sortByColumn);

$(tForm).validate({
	rules: {
		summary: {
			required: true,
			minlength: 3,
			maxlength: 30,
		},
		type: { required: true },
		priority: { required: true },
		status: { required: true },
		projectId: { required: true },
		assignedToId: { required: true }
	},
	messages: {
		summary: {
			required: "Please enter summary",
			minlength: "Should be at least 3 symbols",
			maxlength: "Shouldn't exceed 30 symbols"
		},
		type: "Please select an option",
		priority: "Please select an option",
		status: "Please select an option",
		projectId: "Please select the project",
		assignedToId: "Please select the assignee user"
	},
	errorPlacement: function (error, element) {
		let name = $(element).attr("name");
		let el = $("#" + name + "_validate");
		error.appendTo(el);
	},
	highlight: function (element) {
		$(tForm).find("input[id=" + element.id + "]").addClass(errorClass);
		$(tForm).find("select[id=" + element.id + "]").addClass(errorClass);
	},
	unhighlight: function (element) {
		$(tForm).find("input[id=" + element.id + "]").removeClass(errorClass);
		$(tForm).find("select[id=" + element.id + "]").removeClass(errorClass);
	}
});