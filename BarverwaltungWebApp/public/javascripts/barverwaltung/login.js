$( "#loginForm" ).submit(function( e ) {
	  // Stop form from submitting normally
	  e.preventDefault();
	 
	 $.ajax({
		 	url :  "/authenticate",
		    type : "POST",
		    data: $( "#loginForm" ).serialize(),
		    success:function(data){
		    	// data contains the string URL to redirect to
		        window.location.href = data;
		    },
	  		error:function(data){
	  			alert(data.responseText);
	  		}
		});
});