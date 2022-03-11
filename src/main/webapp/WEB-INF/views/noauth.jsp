<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>오류</title>
<style type="text/css">
#contents { width:440px;margin:160px 260px; }
</style>
</head>
<body>
	<div class="loginWrap">
		<div class="tableHead">
			<h1>오류</h1>
		</div>
		<div>
			<h1>오류 메세지</h1>
			<p><s:message text="${login.noauth}" /><p>
		</div>
		<div class="buttonpanel">
			<div class="buttonlink"><input type="button" onclick="history.back();" value="돌아가기" /></div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${contextPath}/common/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery.validate.min.js"></script>
</html>
