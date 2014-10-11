$(".panel-body").toggleClass(function() {
	
	if ( $("#accountBalance").text().indexOf("-") > -1)  {
		return "negative";
	} else {
		return "positive";
	}
});

$( "#payInForm" ).submit(function( e ) {
	 
	  // Stop form from submitting normally
	  e.preventDefault();
	 
	 $.ajax({
		 	url :  "/payIn",
		    type : "POST",
		    data: $( "#payInForm" ).serialize(),
		    success:function(data){
		    	$("#accountBalance").text('Kontostand:' + data + '€');
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
	var url = "/account/"+selectedAccountId+".json";
	$.ajax({
	 	url :  "/account/"+selectedAccountId+".json",
	    type : "GET",
	    
	    success:function(data){
	    	$("#accountBalance").text('Kontostand:' + data.accountBalance + '€');
	    },
  		error:function(data){
  			alert(data.responseText);
  		}
	});
}
