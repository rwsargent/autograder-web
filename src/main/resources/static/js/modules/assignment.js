require.config({
    baseUrl : dependencies.baseUrl,
    paths : dependencies.paths,
    shim : dependencies.shim
});

require(["jquery", "materialize", ],
function($, mat ) {
    $(function() {
    	var $form = $("form");
    	var $messageBox = $("message");
    	
    	$form.find("input:file").change(function() {
    		
    	});
    	
    	$form.submit(function() {
    	    var formData = new FormData(this);
    	    $.ajax({
    	    	url : $(this).attr("action"),
    	    	method : "POST",
    	    	dataType : 'json',
    	    	data : formData,
    	    	processData : false,
    	    	contentType : false,
    	    	success : function(result) {
    	    		console.log(result);
    	    	},
    	    	error :  function(error) {
    	    		console.log(error);
    	    	}
    	    })
    	    return false;
    	});
    });
});
