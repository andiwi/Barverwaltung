$( "#userForm" ).submit(function( e ) {
	  // Stop form from submitting normally
	  e.preventDefault();
	 
	 $.ajax({
		 	url :  "/createUser",
		    type : "POST",
		    data: $( "#userForm" ).serialize(),
		    success:function(data){
		    	alert(data);
		    },
	  		error:function(data){
	  			alert(data.responseText);
	  		}
		});
	  
	  // Reset form
	  $('#userForm').trigger("reset");
});