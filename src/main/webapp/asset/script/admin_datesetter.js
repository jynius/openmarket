/**
 * 
 */
$(function() {

	$('input.date').datepicker({
		dateFormat: 'yy-mm-dd'
	});
	
	var datesetter = $('.datesetter');
	$('button', datesetter).click(function() {
		
		var durationMap = {"today": "0", "week1": "-1w", "month1": "-1m", "month3": "-3m"};
		var duration;
		for(var key in durationMap) {
			if($(this).hasClass(key)) {
				duration = durationMap[key];
			}
		}
		
		if(duration) {
			var endDate = datesetter.prev('input.date');
			var startDate = endDate.prev('input.date');
			
			endDate.datepicker("setDate", "0");
			startDate.datepicker("setDate", duration);
		}
	});
});