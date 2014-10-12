$( "#rawProductForm" ).submit(function( e ) {
	  // Stop form from submitting normally
	  e.preventDefault();
	 
	 $.ajax({
		 	url :  "/createRawProduct",
		    type : "POST",
		    data: $( "#rawProductForm" ).serialize(),
		    success:function(data){
		    	alert(data.responseText);
		    },
	  		error:function(data){
	  			alert(data.responseText);
	  		}
		});
	  
	  // Reset form
	  $('#rawProductForm').trigger("reset");
});