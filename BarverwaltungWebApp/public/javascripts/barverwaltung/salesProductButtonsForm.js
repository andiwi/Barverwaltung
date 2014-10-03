//Shorthand for $( document ).ready()
$(function() {
	$('#datefield').val($.format.date(new Date(), 'dd-MM-yyyy'));
	$("#infoBox").fadeTo("1", 0);
	//$("#infoBox").hide();
});

// initialisiert den Datepicker und aktualisiert das Datumsfeld wenn ein neues
// Datum in Datepicker ausgewählt wurde.
$(function() {
	var datepicker = $('#datepicker').datepicker({
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#datefield').val($.format.date(ev.date, 'dd-MM-yyyy'));
	});
});

// aktualisiert den DatePicker wenn in Feld ein neues Datum eingegeben wurde.
$('#datefield').change(refreshDatePicker());

function refreshDatePicker() {
	var dateddMMYYYYRegex = /(^(((0[1-9]|[12][0-8])[-](0[1-9]|1[012]))|((29|30|31)[-](0[13578]|1[02]))|((29|30)[-](0[4,6,9]|11)))[-](19|[2-9][0-9])\d\d$)|(^29[-]02[-](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;

	if (dateddMMYYYYRegex.test($('#datefield').val())) {
		var dateStr = $("#datefield").val().split("-");
		date = new Date(dateStr[2], dateStr[1] - 1, dateStr[0]);
		$('#datepicker').datepicker('setValue', date);
	}
}

/*
@for(product <- salesProductList) {
	$( "#salesProductSpan_@product.getId()" ).click(function(e)
	{
		e.preventDefault();
		var value = $("#pieces_@product.getId()").val();
		value++;
		$("#amount_@product.getProductName()").val(value);
	});
}
 */
$("#discard").click(function(e) {
	e.preventDefault();

	// Reset form
	$('input[type=number]').val('0');
	$('#datefield').val($.format.date(new Date(), 'dd-MM-yyyy'));
})
/*
$("#saleForm").submit(function(e) {

	// Stop form from submitting normally
	e.preventDefault();

	$.ajax({
		url : "/sale",
		type : "POST",
		data : $("#saleForm").serialize(),
		success : function(data) {
			
			//Display Success Alert and fadeOut
			$("#infoBox").addClass("alert-success");
			$("#infoBox").html("Kauf erfolgreich!");
			
			//$("#infoBox").css("visibility", "visible")
			$("#infoBox").fadeTo("1", 100);
			
			setTimeout(function() {
					$("#infoBox").fadeTo("slow", 0);
					
					setTimeout(function() {
						$("#infoBox").removeClass("alert-success");
					}, 1000);
			    }, 2000);
			
						
			//Insert new Entry into Grid
			var grid = $("#myGrid").data("gridInstance");
			grid.resizeCanvas();
			var dataView = grid.getData();
			
			// Update an existing item of slickGrid
			var item = dataView.getItemById(data.date);
			if(item != null) {
				dataView.deleteItem(data.date);
			}
			dataView.insertItem(0, data);
			
			//Highlighting row
			var row = dataView.getItemById(data.date)
			row.highlightRow = true;
			dataView.updateItem(row.id, row);
		},
		error : function(data) {
			//Display Success Alert and fadeOut
			$("#infoBox").addClass("alert-danger");
			$("#infoBox").html(data.responseText);
			
			$("#infoBox").fadeTo("1", 100);
			
			setTimeout(function() {
					$("#infoBox").fadeTo("slow", 0);
					
					setTimeout(function() {
						$("#infoBox").removeClass("alert-danger");
					}, 1000);
			    }, 3500);
		}
	});

	// Reset form
	$('#saleForm').trigger("reset");
	$('#datefield').val($.format.date(new Date(), 'dd-MM-yyyy'));
	
	refreshDatePicker();
});*/

$("#saleForm").submit(function(e) {

	// Stop form from submitting normally
	e.preventDefault();

	$.ajax({
		url : "/sale",
		type : "POST",
		data : $("#saleForm").serialize(),
		success : function(data) {
			
			//Display Success Alert and fadeOut
			$("#infoBox").addClass("alert-success");
			$("#infoBox").html("Kauf erfolgreich!");
			
			//$("#infoBox").css("visibility", "visible")
			$("#infoBox").fadeTo("1", 100);
			
			setTimeout(function() {
					$("#infoBox").fadeTo("slow", 0);
					
					setTimeout(function() {
						$("#infoBox").removeClass("alert-success");
					}, 1000);
			    }, 2000);
			
						
			//Insert new Entry into Table
			$('#saleTable').bootstrapTable('refresh');
		},
		error : function(data) {
			//Display Success Alert and fadeOut
			$("#infoBox").addClass("alert-danger");
			$("#infoBox").html(data.responseText);
			
			$("#infoBox").fadeTo("1", 100);
			
			setTimeout(function() {
					$("#infoBox").fadeTo("slow", 0);
					
					setTimeout(function() {
						$("#infoBox").removeClass("alert-danger");
					}, 1000);
			    }, 3500);
		}
	});

	// Reset form
	$('#saleForm').trigger("reset");
	$('#datefield').val($.format.date(new Date(), 'dd-MM-yyyy'));
	
	refreshDatePicker();
});