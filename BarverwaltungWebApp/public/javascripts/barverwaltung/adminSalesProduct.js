$( "#salesProductForm" ).submit(function( e ) {
	  // Stop form from submitting normally
	  e.preventDefault();
	 
	 $.ajax({
		 	url :  "/createSalesProduct",
		    type : "POST",
		    data: $( "#salesProductForm" ).serialize(),
		    success:function(data){
		    	alert(data);
		    },
	  		error:function(data){
	  			alert(data);
	  		}
		});
	  
	  // Reset form
	  $('#salesProductForm').trigger("reset");
});