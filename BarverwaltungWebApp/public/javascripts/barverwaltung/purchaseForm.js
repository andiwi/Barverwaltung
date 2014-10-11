// Shorthand for $( document ).ready()
$(function() {
	initDatePicker();
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
	  resetDatePicker();
});

$( "#discard").click(function(e){
	e.preventDefault();
	
	// Reset form
	$('#purchaseForm').trigger("reset");
	resetDatePicker();
})