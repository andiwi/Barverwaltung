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