<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>오류 메세지</title>
<script type="text/javascript">
$(document).ready(function(){

    $("<div id='MessagePanel' title='알림'><ul style='padding:4px 16px;' /></div>").dialog({
        autoOpen: false,
        modal: true,
        open: function(e) {
        	setTimeout(function() { $(this).dialog('close');returnPage();
        	}, 35000);
        },
        buttons: {
	        	"확인": function() { $(this).dialog("close");
	        	returnPage();
        	}
        }
    });

	var addInvalidGlobals = function(message) {
		var li = document.createElement("li");
		li.appendChild(document.createTextNode(message));
		$('#MessagePanel > ul').append(li);
	};

<c:if test="${not empty exceptionMsg}">
	addInvalidGlobals('${exceptionMsg}');
</c:if>


	if($('#MessagePanel > ul').html().trim()!='') {
		$('#MessagePanel').dialog('open');
	}

});

function returnPage(){
<c:if test="${empty returnUrl}">
	history.back();
</c:if>
<c:if test="${not empty returnUrl}">
	$("#returnForm").attr("action", "${returnUrl}");
	$("#returnForm").submit();
</c:if>
}
</script>
</head>
<body>
	<div class="loginWrap">
<!-- 		<div class="tableHead"> -->
<!-- 			<h1>오류</h1> -->
<!-- 		</div> -->
<!-- 		<div> -->
<!-- 			<h1>오류 메세지</h1> -->
			<form id="returnForm" method="get">${returnForm}</form>
<!-- 		</div> -->
<!-- 		<div class="buttonpanel"> -->
<!-- 			<div class="buttonlink"><input type="button" onclick="history.back();" value="돌아가기" /></div> -->
<!-- 		</div> -->
	</div>
</body>
</html>