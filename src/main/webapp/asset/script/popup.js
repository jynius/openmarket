(function($) {

	/*
	 *
	 */
	$.popup = function(id, popup, modal, dimension, position, relative) {

		var winobj = $(window);
		var href = '/popup.asp?idx=' + id;
		var modal = (modal=='Y');
		var panelid;

		// position: {my:"center center", at:"center center", of:window}
		var popupDialog = function(dimension, position) {

			alert(dimension.width);
			panelobj = $('<div id="' + panelid + '" />').appendTo('body');
			panelobj.load(href, function() {
				panelobj.dialog({
					dialogClass: 'no-titlebar',
					width: dimension.width,
					height: dimension.height,
					position: position,
					modal: modal
				});
			});
		};

		// relative: {left:-300, top:-200, wlr:"center", wtb:"center"}
		var popupWindow = function(dimension, relative) {

			var specs  = "directories=0,location=0,menubar=0,resizable=0";
				specs += ",scrollbars=0,status=0,titlebar=0,toolbar=0";
				specs += ",width=" + dimension.width + ",height=" + dimension.height;
				specs += ",left=" + getSpecLeft(relative) + ",top=" + getSpecTop(relative);
	//		alert(specs);
			panelobj = window.open(href, panelid, specs);
		};

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
		};

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
		};

		var d = eval("(" + dimension + ")");
		switch (popup) {
		case 'D':
			panelid = 'pnl_popup' + id;
			var p = eval("(" + position + ")");
			popupDialog(d, p);
			break;
		case 'W':
			panelid = 'win_popup' + id;
			var r = eval("(" + relative + ")");
			popupWindow(d, r);
			break;
		}
	};

	$('input.duration').click(function(event) {

		var cname = $(this).attr('id');
		// 삭제
		if(!$(this).prop('checked')) {
			document.cookie = cname + "=; expires=Thu, 01 Jan 1970 00:00:00 UTC";
			return;
		}

		// session
		var term = $(this).val();
		if(term==0) {
			document.cookie = cname + "=" +  term;
			return;
		}

		// day, week, once
		var d = new Date();
		d.setTime(d.getTime() + (term*24*60*60*1000));
		document.cookie = cname + "=" + term + "; expires=" + d.toUTCString();
	});

})(jQuery);