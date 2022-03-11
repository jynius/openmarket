$(function() {

	var pagingParameterName = 'currPage';
	var filterFormClassName = 'filter';
	var appendLinkClassName = 'append';
	var popupLinkClassName = 'popup';

	window.paging = function(page) {
		location.search = getSearch(pagingParameterName, page);
	};

	window.post = function(href) {
		getPostForm(href).submit();
	};

	window.getParameter = function(name) {
		return location.search.match(new RegExp(name + '=([^&]*)'))? RegExp.$1: '';
	};

//	alert($('form.' + filterFormClassName).length);
//  http://seoulgarden.co.kr/admin/board_news/news_list.asp?td=board_news&lang_gu=A&cond=title&word=%EC%95%88%EB%82%B4
	$('form.' + filterFormClassName).submit(function(event) {
//		alert('submit!');
		event.preventDefault();
		location.search = getSearch($(this).serializeArray());
//		alert(this.action);
//		$(this).submit();
	});

	$('a.' + appendLinkClassName).click(function(event) {
		event.preventDefault();
		var h = this.href.split('?');
		location.href = h[0] + (h.length==1? location.search: getSearch(serializeArray(h[1])));
	});

	$('a.' + popupLinkClassName).click(function(event) {
		
		event.preventDefault();
		
		var panelid = 'panel_' + $(this).identify();
		var panelobj = $('#' + panelid); 
		if(panelobj.length>0) {
			panelobj.show();
			return;
		}
		
		panelobj = $('<div id="' + panelid + '" />').hide().appendTo('body');
		panelobj.load(this.href, function() {
			panelobj.show();
			$('.close', panelobj).click(function() {
				panelobj.hide();
			});
		});
	});

	var removeParameter = function(name) {
//		return location.search.match(new RegExp(name + '=([^&]*)'))? RegExp.$1: '';
	};

	var getPostForm = function(href) {

		var h = href.split('?');

		var form = $('<form method="post" />').attr('action', h[0]);
		if(h.length>1) {

			$.each(serializeArray(h[1]), function(i, p) {
				form.append('<input type="hidden" name="' + p.name + '" value="' + p.value + '" />');
			});
		}
		
		form.appendTo('body');
		return form;
	};

	var serializeArray = function(s) {

		var a = [];
		$.each(s.split('&'), function(i, p) {
			var q = p.split('=');
			a[i] = {name:q[0], value:(q.length==1? null: q[1])};
		});

		return a;
	};

	/** location.search 변경 */
	var getSearch = function() {

		var s = location.search;
		var a = getSearch.arguments;
		if(a.length==1 && $.isArray(a[0])) {

			$.each(a[0], function(i, ai) {

				var k = ai.name;
				var v = k + '=' + ai.value;
				
				var r = new RegExp(k + '=[^&]*');
				if(r.test(s))
					s = s.replace(r, v);
				else
					s += ((s=='')? '?' : '&') + v;
			});

			return s;
		}

		if(a.length<2) return s;

		for(var i=0; i<a.length; i=i+2) {

			var k = a[i];
			var v = k+'='+a[i+1];

			var r = new RegExp(k + '=[^&]*');
			if(r.test(s))
				s = s.replace(r, v);
			else
				s += ((s=='')? '?' : '&') + v;
		}

		return s;
	};


    $.alert = function(message) {

    	var panel = $('#alertDialog'); 
        if(panel.length==0) {
			panel = $('<div id="alertDialog" />').hide().attr('title', "경고!").appendTo('body');
			panel.dialog({
				dialogClass: "no-close",
				modal: true,
				autoOpen: false,
				resizable: false,
				buttons: [{
					text: "확인",
					click: function() {
						$( this ).dialog("close");
					}
				}]
			});
        }

        panel.html(message || '');
        panel.dialog('open');
    };

    $.confirm = function(message, yescallback, nocallback) {

    	var panel = $('#confirmDialog'); 
        if(panel.length==0){
    		panel = $('<div id="confirmDialog" />').hide().attr('title', "확인").appendTo('body');
    		panel.html(message);
    		if(yescallback) panel.yescallback = yescallback;
    		if(nocallback) panel.yescallback = yescallback;
    		panel.dialog({
    			dialogClass: "no-close",
    			modal: true,
    			resizable: false
    		});
        }
        
		panel.html(message);
		panel.dialog('option', "buttons", {
			"예": function() {if(yescallback) yescallback(); $(this).dialog('close');},
			"아니오": function() {if(nocallback) nocallback(); $(this).dialog('close');},
		});
		panel.dialog('open');
    };
    
    $.fn.identify = function(prefix) {
    	
        this.each(function() {
        	
            if(!this.id) {
                
                var id;
                do { 
                    id = new Date().getTime().toString(36);
                    if(prefix) {
                    	id = prefix + id;
                    }
                }
                while($('#' + id).length > 0);
                
                $(this).attr('id', id);
            }
        });
        
    	return $(this).attr('id');
    };
});