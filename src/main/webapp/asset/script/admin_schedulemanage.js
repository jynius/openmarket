/**
 * 
 */
$(function() {

	$('#schedulemanager').click(function() {
		
		var menuSeq = $('input[name="menuSeq"]').val();
		if(!menuSeq) {
			$.alert("디너를 먼저 선택해야 합니다.");
			return;
		}
		
		var panel = $('#schedulepanel');
		if(panel.length>0) {
			panel.dialog('open');
			return;
		}
		
		var scheduleload = function() {
			
			$('.date', this).datepicker({
				dateFormat: 'yy-mm-dd'
			});

			$('.prev, .next', this).click(function() {
				panel.load('/dinner/schedule/list.ajax?menuSeq=' + menuSeq + "&monthInt=" + $(this).val(), scheduleload);
			});

			var monthInt = $('input[name="monthInt"]').val();
			
			$('#addschedule', this).click(function() {
				
				var parents = $(this).parents('tr');
				parents.find('input[type="radio"]').attr('name', 'display');
				
//				var appoint = parents.find('input[name="appoint"]').val();
				var data = parents.find('input, select').serializeArray();
				$.post('/dinner/schedule/make.ajax?menuSeq=' + menuSeq, data, function() {
					panel.load('/dinner/schedule/list.ajax?menuSeq=' + menuSeq + '&monthInt=' + monthInt, data, scheduleload);
				});
			});

			$('.editschedule', this).click(function() {
				
				var parents = $(this).parents('tr');
				parents.find('input[type="radio"]').attr('name', 'display');
				
//				var appoint = parents.find('input[name="appoint"]').val();
				var data = parents.find('input, select').serializeArray();
				$.post('/dinner/schedule/edit.ajax?menuSeq=' + menuSeq + '&dinnerSeq=' + $(this).val(), data, function() {
					panel.load('/dinner/schedule/list.ajax?menuSeq=' + menuSeq + '&monthInt=' + monthInt, data, scheduleload);
				});
			});

			$('.wipeschedule', this).click(function() {
				
				var parents = $(this).parents('tr');
				
//				var appoint = parents.find('input[name="appoint"]').val();
				$.post('/dinner/schedule/wipe.ajax?menuSeq=' + menuSeq + '&dinnerSeq=' + $(this).val(), function() {
					panel.load('/dinner/schedule/list.ajax?menuSeq=' + menuSeq + '&monthInt=' + monthInt, scheduleload);
				});
			});
			
			panel.dialog({
				modal: true,
				width: 920,
				height: 750,
				title: "디너 일정 등록",
				buttons: {
					"닫기": function() {
						panel.dialog('close');
					}
				}
			});
		};
		
		panel = $('<div id="schedulepanel"></div>').appendTo('body');
		panel.load('/dinner/schedule/list.ajax?menuSeq=' + menuSeq, scheduleload);
	});
	
});
