/**
 * 
 */
$(function() {

	$('#albummanager').click(function() {
		
		var menuSeq = $('input[name="menuSeq"]').val();
		if(!menuSeq) {
			$.alert("디너를 먼저 선택해야 합니다.");
			return;
		}
		
		var panel = $('#albumpanel');
		if(panel.length>0) {
			panel.dialog('open');
			return;
		}

		var albumload = function() {
			
			$(".imagepreviewpanel", this).trigger("imagepreviewload");

			window.paging = function(page) {
				e.preventDefault();
				panel.load('/dinner/album/list.ajax?menuSeq=' + menuSeq + '&paging.currPage=' + page, albumload);
			};

			$('#addphoto', this).click(function() {
				var p = $(this).parents('tr');
//				alert(p.find('textarea').attr('name'));
//				alert(p.find('input').attr('name'));
//				alert(p.find('textarea').val());
//				alert((p.find('input').files).length);
				var data = new FormData();
				data.append('comment', $('textarea', p).val());
				data.append('image', $('input', p)[0].files[0]);
//				data.append('image', $('input', p).val());
//				alert(JSON.stringify(data));
				$.ajax('/dinner/album/make.ajax?menuSeq=' + menuSeq, {
					type: 'POST',
//					contentType: 'multipart/form-data',
					contentType: false,
					processData: false,
					data: data,
					success: function() {
						panel.load('/dinner/album/list.ajax?menuSeq=' + menuSeq, albumload);
					}
				});
			});

			$('.editphoto', this).click(function() {
				var p = $(this).parents('tr');
				var data = new FormData();
				data.append('comment', $('textarea', p).val());
				data.append('image', $('input', p)[0].files[0]);
				$.ajax('/dinner/album/edit.ajax?menuSeq=' + menuSeq + '&diningPhotoSeq=' + $(this).val(), {
					type: 'POST',
					contentType: false,
					processData: false,
					data: data,
					success: function() {
						panel.load('/dinner/album/list.ajax?menuSeq=' + menuSeq, albumload);
					}
				});
			});

			$('.wipephoto', this).click(function() {
				$.post('/dinner/album/wipe.ajax?menuSeq=' + menuSeq + '&diningPhotoSeq=' + $(this).val(), {}, function() {
					panel.load('/dinner/album/list.ajax?menuSeq=' + menuSeq, albumload);
				});
			});
			
			panel.dialog({
				modal: true,
				width: 640,
				height: 750,
				title: "다이닝 앨범 관리",
				buttons: {
					"단기": function() {
						panel.dialog('close');
					}
				}
			});
		};
		
		panel = $('<div id="albumpanel"></div>').appendTo('body');
		panel.load('/dinner/album/list.ajax?menuSeq=' + menuSeq, albumload);
	});
});