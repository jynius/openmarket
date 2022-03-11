/**
 * 
 */
$(function() {

	$('#hostselector').click(function() {
		
		var panel = $('#hostselectpanel');
		if(panel.length==0) {
			panel = $('<div id="hostselectpanel"></div>').appendTo('body');
			panel.load('/membership/host/selector.ajax', function() {
				
				window.paging = function(page) {
					event.preventDefault();
					panel.load('/membership/host/selector.ajax?paging.currPage=' + page);
				};
			
				panel.dialog({
					modal: true,
					width: 460,
					height: 600,
					title: "호스트 검색",
					buttons: {
						"닫기": function() {
							panel.dialog('close');
						}
					}
				});
			});
		}
		else {
	
			panel.dialog('open');
			
		}
	});
	
	
});