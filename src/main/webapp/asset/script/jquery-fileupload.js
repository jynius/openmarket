/**
 * 
 */
$(function() {

	$('form').sumbit(function() {
	
		if($(this).attr('enc')!=null) {
			return;
		}
		
		var files = $('input[type="file"]', this);
		if(files.length==0) {
			return;
		}
		
		var formData = new FormData();
		files.each(function(i, e) {
			formData.append(this.name, $(this).files[0]);
		});
		
		$.post('/fileupload', formData, function() {
			
		});
	});
});