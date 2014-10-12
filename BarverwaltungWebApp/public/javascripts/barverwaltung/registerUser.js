$( "#registerForm" ).submit(function( e ) {
	  // Stop form from submitting normally
	  e.preventDefault();
	 
	 $.ajax({
		 	url :  "/createFirstUser",
		    type : "POST",
		    data: $( "#registerForm" ).serialize(),
		    success:function(data){
		    	alert(data);
		    	window.location.href = "/login";
		    },
	  		error:function(data){
	  			alert(data.responseText);
	  		}
		});
	  
	  // Reset form
	  $('#registerForm').trigger("reset");
});