<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1" />
	<title>▒ SKKU Flea Market ▒ - 사용자 등록</title>
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.structure.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.theme.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_layout.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_edit.css" />
</head>
<body>
<div class="headwrap">
	<div class="heading"><span>사용자 등록</span></div>
	<div class="comment"><span>사용자 계정 정보를 등록하실 수 있습니다.</span></div>
	<div class="breadcrumb"><span>홈  &gt; 사용자 관리</span></div>
	<c:choose><c:when test="${empty loginUser}"><a href="${contextPath}/login.do">로그인</a></c:when><c:otherwise><a href="${contextPath}/logout.do">로그아웃</a></c:otherwise></c:choose>
</div>
<form action="${contextPath}/user/create.do" method="post">
<input type="hidden" name="deleted" value="false" />
<table class="editwrap">
<colgroup>
	<col width="15%" />
	<col width="*" />
</colgroup>
<tbody>
<tr>
	<th>역할</th>
	<td>
<c:forEach items="${roles}" var="v">
		<input type="radio" id="role_${v}" name="classification" value="${v.code}" /><label for="type_${v}">${v.code}</label>
</c:forEach>
	</td>
<tr>
<tr>
	<th>이름</th>
	<td>
		<input type="text" name="name" value="${account.name}" /> 
	</td>
<tr>
<tr>
	<th>아이디</th>
	<td>
		<input type="text" name="userid" value="${account.userid}" /> 
	</td>
<tr>
<tr>
	<th>비밀번호</th>
	<td>
		<input type="password" id="passwd" name="passwd" /> 
	</td>
<tr>
<tr>
	<th>비밀번호 확인</th>
	<td>
		<input type="password" name="retype" /> 
	</td>
<tr>
<tr>
	<th>전화번호</th>
	<td>
		<input type="text" name="phoneNumber" value="${account.phoneNumber}" required="required" />
	</td>
<tr>
</tbody>
</table>
<div class="navbox">
	<button type="submit" class="submit">등록</button>
</div>
</form>
<script type="text/javascript" src="${contextPath}/common/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery.validate.min.js"></script>
<script type="text/javascript">
$(function() {

	$('form').validate({
		rules: {
			retype: {
				equalTo: "#passwd"
			}
		},
		messages: {
			phoneNumber: "전화번호를 입력하세요."
		}
	});
});
</script>
</body>
</html>