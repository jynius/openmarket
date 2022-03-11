/**
 * 
 */
$(function() {

	/* promotion/dinner/list.jsp에서 사용 */
	$('.menuselector').click(function() {
		
		var panel = $('#menuselectpanel');
		if(panel.length>0) {
			panel.dialog('open');
			return;
		}

		var button = $(this);
		panel = $('<div id="menuselectpanel"></div>').appendTo('body');
		panel.load('/dinner/menu/selector.ajax', function() {

			$('input[name="menuSeq"]', panel).click(function(e) {
//				alert('this!');
//				e.preventDefault();
				var checkbox = $(this);
				if(checkbox.prop('checked')) $('input[name="menuSeq"]:checked', panel).each(function(i, e) {
					if(checkbox.val()!=$(e).val()) {
						$(e).prop('checked', false);
					}
				});
			});
			
			panel.dialog({
				modal: true,
				width: 460,
				height: 600,
				title: "디너 검색",
				buttons: {
					"선택": function() {
						var td = button.parents('td');
						$('input[name="menuSeq"]:checked', this).each(function(i, e) {
							var wrapper = button.next('.menuwrap');
							wrapper.text('');
							$('<span class="menuName" />').appendTo(wrapper).text($(e).parents('tr').find('td.menuName').text());
							$('<input type="hidden" name="menuSeq" />').appendTo(wrapper).val($(e).val());
						});
						panel.dialog('close');
					},
					"닫기": function() {
						panel.dialog('close');
					}
				}
			});
		});
	});
});