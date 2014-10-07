//Ändert die active Auswahl und speichert den ausgewählten Account in den hidden Input
$( ".list-group-item").click(function() {
	var accountId = $(this).attr('id');
	$(this).addClass("active");
	var oldAccountId = $("#selectedAccount_id").val();
	$("#"+oldAccountId).removeClass("active");
	$("#selectedAccount_id").val(accountId);
});

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