$(function() {
	toggleGreenRed();
});

$( "#payInForm" ).submit(function( e ) {
	 
	  // Stop form from submitting normally
	  e.preventDefault();
	 
	 $.ajax({
		 	url :  "/payIn",
		    type : "POST",
		    data: $( "#payInForm" ).serialize(),
		    success:function(data){
		    	$("#accountBalance").text('Kontostand: ' + data + '€');
		    	toggleGreenRed();
		    },
	  		error:function(data){
	  			alert(data.responseText);
	  		}
		});
	  
	  // Reset form
	  $('#payInForm').trigger("reset");
});

function refreshKontostandPanel() {
	var selectedAccountId = $("#selectedAccountId").val();
	var url = "/accountBalance/"+selectedAccountId+".json";
	$.ajax({
	 	url :  url,
	    type : "GET",
	    
	    success:function(data){
	    	$("#accountBalance").text('Kontostand: ' + data + '€');
	    	toggleGreenRed();
	    },
  		error:function(data){
  			alert(data.responseText);
  		}
	});	
}

function toggleGreenRed() {
	$(".panel-body").removeClass("positive");
	$(".panel-body").removeClass("negative");
	
	if ( $("#accountBalance").text().indexOf("-") > -1)  {
		$(".panel-body").addClass("negative")
	} else {
		$(".panel-body").addClass("positive")
	}
};

