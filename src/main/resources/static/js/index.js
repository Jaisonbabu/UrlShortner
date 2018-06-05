$(document).ready(function() {
	var userUrl = $('#userUrl'),
	shortUrl = $('#shortUrl')
	
	$('#url').on('submit', function(event) {
		var inputUrl = {
				"requestUrl" : userUrl.val()
		};

		$.ajax({
			type : "POST",
			contentType : "application/json",
			dataType : 'text',
			url : 'http://localhost:9000/api',
			data : JSON.stringify(inputUrl),
			success : function(data) {
				shortUrl.val(data);
				console.log(data);
			},
			error : function(error) {
				alert("Error!");
				console.log("ERROR: ", error);
			}
		});
		event.preventDefault();
	});
	userUrl.focus();
});