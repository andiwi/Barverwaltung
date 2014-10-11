//Shorthand for $( document ).ready()
$(function() {
	$("#infoBox").fadeTo("1", 0);
	initDatePicker();	
});

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
	resetDatePicker();
})

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
			// Refresh Kontostand Panel
			refreshKontostandPanel();
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
			
			// Refresh Kontostand Panel
			refreshKontostandPanel();
		}
	});

	// Reset form
	$('#saleForm').trigger("reset");
	resetDatePicker();
});