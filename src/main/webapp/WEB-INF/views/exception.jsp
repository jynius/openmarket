<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>오류</title>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="loginWrap">
		<div class="tableHead">
			<h1>오류</h1>
		</div>
		<div>
			<h1>오류메세지</h1>
			<pre>${message}</pre>
		</div>
		<div class="buttonpanel">
			<div class="buttonlink"><input type="button" onclick="history.back();" value="돌아가기" /></div>
		</div>
	</div>
</body>
</html>
