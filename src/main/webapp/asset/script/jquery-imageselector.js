/**
 * 
 */
$(function() {
	
	$('button.imageselector').click(function() {
		
//		alert('click!');
		var panel = $('#imageselectpanel');
		if(panel.length==0) {
			
			panel = $('<div id="imageselectpanel"></div>').append('body');
			panel.load('/imageselect.ajax', function() {

				$('input[type="file"]', '#imageselectpanel').change(function() {
				
//					alert('change!');
					var file = this.files && this.files[0];
					if(file && file.type.match(/image\//)) {
						
						//파일을 읽기 위한 FileReader객체 생성
			            var reader = new FileReader();
			            
			            //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
			            reader.onload = function (e) {
			                //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
			                //(아래 코드에서 읽어들인 dataURL형식)
			                $('<img />').appendTo('#imageselectpanel div.imagepanel').attr('src', e.target.result);
			            }
			            
			            //File내용을 읽어 dataURL형식의 문자열로 저장
			            reader.readAsDataURL(file);
			        }
				});
			});
			
			panel.dialog({
				width: 426,
				modal: true,
				autoOpen: false,
				title: "이미지 선택"
			});
		}
		
		panel.dialog('open');
	});
});