/**
 * 
 */
$(function() {

	$(this).delegate(".imagepreviewpanel", "imagepreviewload", function() {
		
		var image = $('img', this); 
		if(!image.attr('src') || !image.attr('src').match(/\.(gif|jpg|png)/i)) {
			image.attr('src', '/images/no_image.gif');
		}
		
		$('input', this).change(function() {
			
			var file = this.files && this.files[0];
			if(file && file.type.match(/image\//)) {
            	
				var imageSeq = $(this).prevAll('input[type="hidden"]');
				if(imageSeq.length>0) {
					imageSeq.prependTo($(this).parents('form')).attr('name', 'images');
//					imageSeq.remove();
				}
				else if($(this).hasClass('imageinserter')) {
    				var template = $(this).parent();
					template.after(template.clone(true));
					$(this).removeClass('imageinserter');
					$('<button type="button" />').appendTo(template).text('삭제').click(function() {
						$(this).parent().remove();
					});;
				}
				
				//파일을 읽기 위한 FileReader객체 생성
                var image = $(this).prev('img');
	            var reader = new FileReader();
	            
	            //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
	            reader.onload = function (e) {
	                
	                //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
	                //(아래 코드에서 읽어들인 dataURL형식)
	                image.attr('src', e.target.result);
	            }
	            
	            //File내용을 읽어 dataURL형식의 문자열로 저장
	            reader.readAsDataURL(file);
	        }
		});
		
		$('button', this).click(function() {
			var button = $(this);
			$.confirm("삭제하시겠습니까?", function() {
//				alert(button.prevAll('input[type="hidden"]').html());
				button.prevAll('input[type="hidden"]').prependTo(button.parents('form')).attr('name', 'images');
//				alert(button.parents('form').html());
				button.parents('div.imagepreviewpanel').remove();
			});
		});
	});
	
	$(".imagepreviewpanel").trigger("imagepreviewload");
});