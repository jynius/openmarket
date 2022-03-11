/**
 * 
 */
$(function() {
	
	/* dinner/menu/edit.jsp에서 사용 */
	$('button', '#disheswrap').click(function() {
		$(this).parent('div').remove();
	});

	/* dinner/menu/edit.jsp에서 사용 */
	$('#dishselector').click(function() {
		
		var hostSeq = $('input[name="hostSeq"]').val();
		if(!hostSeq) {
			$.alert("호스트를 먼저 선택해 주십시오.");
			return;
		}
		
		var panel = $('#dishselectpanel');
		if(panel.length>0) {
			panel.dialog('open');
			return;
		}
		
		var selector = $(this);
		panel = $('<div id="dishselectpanel"></div>').appendTo('body');
		panel.load('/dinner/recipe/selector.ajax?hostSeq=' + hostSeq, function() {

			$('#checkall').click(function() {
				$('input[name="dishSeq"]', panel).prop('checked', $(this).prop('checked'));
			});
			
			panel.dialog({
				modal: true,
				width: 460,
				height: 600,
				title: "레시피 검색",
				buttons: {
					"선택": function() {
						$('input[name="dishSeq"]:checked', this).each(function(i, e) {
//							alert($(e).parents('tr').find('td.dishTitle').text());
							var wrapper = $('<div class="dishwrap" />').appendTo(selector.next('div'));
							$('<span class="dishTitle" />').appendTo(wrapper).text($(e).parents('tr').find('td.dishTitle').text());
							$('<input type="hidden" name="dishes" />').appendTo(wrapper).val($(e).val());
							$('<button type="button" />').appendTo(wrapper).click(function() {
								$(this).parent('div').remove();
							});
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

	/* promotion/recipe/list.jsp에서 사용 */
	$('.dishselector').click(function() {
		
		var panel = $('#dishselectpanel');
		if(panel.length>0) {
			panel.dialog('open');
			return;
		}

		var button = $(this);
		panel = $('<div id="dishselectpanel"></div>').appendTo('body');
		panel.load('/dinner/recipe/selector.ajax', function() {

			$('input[name="dishSeq"]', this).click(function() {
				
				var checkbox = $(this);
				$('input[name="dishSeq"]:checked', panel).each(function(i, e) {
					if(checkbox.val()!=$(e).val()) {
						$(e).prop('checked', false);
					}
				});
			});
			
			panel.dialog({
				modal: true,
				width: 460,
				height: 600,
				title: "레시피 검색",
				buttons: {
					"선택": function() {
						var td = button.parents('td');
						$('input[name="dishSeq"]:checked', this).each(function(i, e) {
							var wrapper = button.next('.dishwrap');
							wrapper.text('');
							$('<span class="dishTitle" />').appendTo(wrapper).text($(e).parents('tr').find('td.dishTitle').text());
							$('<input type="hidden" name="dishSeq" />').appendTo(wrapper).val($(e).val());
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