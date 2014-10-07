// Shorthand for $( document ).ready()
$(function() {
	$('#datefield').val($.format.date(new Date(), 'dd.MM.yyyy'));
});

// initialisiert den Datepicker und aktualisiert das Datumsfeld wenn ein neues
// Datum in Datepicker ausgewÃ¤hlt wurde.
$(function() {
	var datepicker = $('#datepicker').datepicker({
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#datefield').val($.format.date(ev.date, 'dd.MM.yyyy'));
	});
})

// aktualisiert den DatePicker wenn in Feld ein neues Datum eingegeben wurde.
$('#datefield')
		.change(
				function() {
					var dateddMMYYYYRegex = /(^(((0[1-9]|[12][0-8])[-](0[1-9]|1[012]))|((29|30|31)[-](0[13578]|1[02]))|((29|30)[-](0[4,6,9]|11)))[-](19|[2-9][0-9])\d\d$)|(^29[-]02[-](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)/;

					if (dateddMMYYYYRegex.test($('#datefield').val())) {
						var dateStr = $("#datefield").val().split("-");
						date = new Date(dateStr[2], dateStr[1] - 1, dateStr[0]);
						$('#datepicker').datepicker('setValue', date);
					}
				});

$("#productDropdownBtnUl li a").click(function(){
	  var selText = $(this).text();
	  $("#productInput").val(selText);
});

$( "#purchaseForm" ).submit(function( e ) {
	 
	  // Stop form from submitting normally
	  e.preventDefault();
	 
	  $.ajax({
			url : "/purchase",
			type : "POST",
			data : $("#purchaseForm").serialize(),
			success : function(data) {
				
				//Display Success Alert and fadeOut
				$("#infoBox").addClass("alert-success");
				$("#infoBox").html("Eintrag erfolgreich!");
				
				$("#infoBox").fadeTo("1", 100);
				
				setTimeout(function() {
						$("#infoBox").fadeTo("slow", 0);
						
						setTimeout(function() {
							$("#infoBox").removeClass("alert-success");
						}, 1000);
				    }, 2000);
				
				//Insert new Entry into Table		
			   	$('#purchaseTable').bootstrapTable('refresh');
		    },
	  		error:function(data){
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
	  $('#purchaseForm').trigger("reset");
	  $('#datefield').val($.format.date(new Date(), 'dd.MM.yyyy'));
});

$( "#discard").click(function(e){
	e.preventDefault();
	
	// Reset form
	$('#purchaseForm').trigger("reset");
	$('#datefield').val($.format.date(new Date(), 'dd.MM.yyyy'));
})