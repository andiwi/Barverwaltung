function initDatePicker() {
	$('#datefield').val($.format.date(new Date(), 'dd.MM.yyyy'))
	
	// initialisiert den Datepicker und aktualisiert das Datumsfeld wenn ein neues
	// Datum in Datepicker ausgewählt wurde.
	var datepicker = $('#datepicker').datepicker({
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#datefield').val($.format.date(ev.date, 'dd.MM.yyyy'))
	})
	
	//aktualisiert den DatePicker wenn in Feld ein neues Datum eingegeben wurde.
	$('#datefield').change(function(){
		refreshDatePicker();
	});
}

function refreshDatePicker() {
	var dateddMMYYYYRegex = /^(?!3[2-9]|00|02-3[01]|04-31|06-31|09-31|11-31)[0-3][0-9].(?!1[3-9]|00)[01][0-9].(?!10|28|29)[12][089][0-9][0-9]/;

	if (dateddMMYYYYRegex.test($('#datefield').val())) {
		var dateStr = $("#datefield").val().split(".");
		date = new Date(dateStr[2], dateStr[1] - 1, dateStr[0]);
		$('#datepicker').datepicker('setValue', date);
	}
}

function resetDatePicker() {
	$('#datefield').val($.format.date(new Date(), 'dd.MM.yyyy'));
	refreshDatePicker();
}