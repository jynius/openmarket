$(function() {

	$('button.reset').click(function() {
		this.form.reset();
	});
	
	var p = location.pathname;
	var g = p.substring(0, p.lastIndexOf('_'));
//	alert(g);
	$('ul#menu a').each(function(i, e) {
//		alert(g + ":" + e.href);
		if(e.href.indexOf(g)>0) {
			$(e).parent('li').addClass('current');
		}
	});

//	console.log('loading...1');
	$('button.delete, button.remove').click(function(event) {

//		console.log('click the button.delete!');
//		alert('click the button.delete!');
		event.preventDefault();
		var obj = $(this);
		$.confirm("삭제하시겠습니까?", function() {

			var params = {
				command: 'delete',
				idx: obj.val()
			};

			$.post('process.asp', params, function() {

				if(obj.hasClass("delete")) {
					location.reload(1);
				}
				else {
					var href = location.href;
					location.href = href.replace('_view\.', '_list.');
				}
			});
		});
	});

//	console.log('loading...1');
	$('button.removeImage').click(function(event) {

//		console.log('click the button.delete!');
//		alert('click the button.delete!');
		event.preventDefault();
		var obj = $(this);
		$.confirm("이미지를 삭제하시겠습니까?\n 삭제하는 경우, 내용에서 참조되지 않습니다.", function() {

			var field = null;
			$.each(["img0", "img1", "img2", "img3"], function(i, e) {
				if(obj.hasClass(e)) {
					field = e;
				}
			});

			var params = {
				command: 'removeImage',
				field: field,
				idx: v.val()
			};

			$.post('process.asp', params, function() {
				location.reload(1);
			});
		});
	});

//	console.log('loading...2');
	$('input.toggle').click(function(e) {

		console.log('click input.toggle!');
		var obj = $(this);
		var field = null;
		$.each(["popup", "show", "main", "front"], function(i, e) {
			if(obj.hasClass(e)) {
				field = e;
			}
		});

		if(field==null) {
			return;
		}

		var params = {
			command: 'toggle',
			idx: this.value
		};
		
		params["field"] = field;
		params[field] = (this.checked? "N": "Y");

		e.preventDefault();
		$.post('process.asp', params, function() {
			obj.prop('checked', !obj.prop('checked'));
		});
	});

	$('a.popup').click(function(e) {

//		alert('click!');
		event.preventDefault();
		var panelid = 'pnl_' + $(this).uniqueId().attr('id');
		var panelobj = $('#' + panelid);
		if(panelobj.length!=0) {
//			alert(this.id);
			panelobj.dialog('open');
			return;
		}

		panelobj = $('<div id="' + panelid + '" />').hide().insertAfter(this);
		panelobj.load(this.href, function() {
			panelobj.dialog({
				modal: true,
				width: 600,
				height: 400
			});
		});
	});

	console.log('loading...3');
	$('a.popupD').click(function(event) {

//		alert('click!');
		event.preventDefault();
		var panelid = 'pnl_' + $(this).uniqueId().attr('id');
		var panelobj = $('#' + panelid);
		if(panelobj.length!=0) {
//			alert(this.id);
			panelobj.dialog('open');
			return;
		}

		var dimension = eval("(" + $(this).next('div.data').find('span.dimension').text() + ")");
		var position = eval("(" + $(this).next('div.data').find('span.position').text() + ")");
		panelobj = $('<div id="' + panelid + '" />').hide().insertAfter(this);
		panelobj.load(this.href, function() {
			panelobj.dialog({
				dialogClass: 'no-titlebar',
				width: dimension.width,
				height: dimension.height,
				position: position
			});
		});
	});

	console.log('loading...4');
	$('a.popupW').click(function(event) {

//		alert('click!');
		event.preventDefault();
		var panelid = 'win_' + $(this).uniqueId().attr('id');
		var panelobj = $(window).data(panelid);
		if(panelobj!=null) {
			panelobj.focus();
			return;
		}

		var winobj = $(window);
		var getSpecLeft = function(relative) {

			var l = relative.left + winobj.scrollLeft();
			switch (relative.wlr) {
			case 'left':
				break;
			case 'center':
				l = l + winobj.width()/2;
				break;
			case 'right':
				l = l + winobj.width();
				break;
			}

			return Math.round(l);
		}

		var getSpecTop = function(relative) {

			var t = relative.top + winobj.scrollTop();
			switch (relative.wtb) {
			case 'top':
				break;
			case 'center':
				t = t + winobj.height()/2;
				break;
			case 'bottom':
				t = t + winobj.height();
				break;
			}

			return Math.round(t);
		}
		
		var dimension = eval("(" + $(this).next('div.data').find('span.dimension').text() + ")");
		var relative = eval("(" + $(this).next('div.data').find('span.relative').text() + ")");
		var specs  = "directories=0,location=0,menubar=0,resizable=0";
			specs += ",scrollbars=0,status=0,titlebar=0,toolbar=0";
			specs += ",width=" + dimension.width + ",height=" + dimension.height;
			specs += ",left=" + getSpecLeft(relative) + ",top=" + getSpecTop(relative);
//			alert(specs);
		panelobj = window.open(this.href, panelid, specs);
		$(window).data(panelid, panelobj);
	});

	console.log('loading...5');
});