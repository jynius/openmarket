<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1" />
	<title>▒ SKKU Flea Market ▒ - 로그인</title>
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.structure.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.theme.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_login.css" />
</head>
<body>
<div class="loginwrap">
	<h1>▒ SKKU Flea Market ▒</h1>
	<form id="loginForm" action="${contextPath}/login.do" method="post">
		<fieldset>
			<legend>로그인 정보</legend>
			<dl>
				<dt><label for="userid">아이디</label></dt>
				<dd><input type="text" class="required" id="userid" name="userid" value="${userid}" /></dd>
				<dt><label for="passwd">패스워드</label></dt>
				<dd><input type="password" class="required" id="passwd" name="passwd" /></dd>
			</dl>
			<div class="buttons">
				<input type="submit" class="submit" name="loginbutton" value="로그인" />
			</div>
		</fieldset>
	</form>
</div>
<script type="text/javascript" src="${contextPath}/common/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/localization/messages_ko.min.js"></script>
</body>
</html>