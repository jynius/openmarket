<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1" />
	<title>▒ SKKU Flea Market ▒ - 개인정보 조회</title>
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.structure.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.theme.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_layout.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_edit.css" />
</head>
<body>
<div class="headwrap">
	<div class="heading"><span>개인정보 조회</span></div>
	<div class="comment"><span>사용자 계정 정보를 확인하실 수 있습니다.</span></div>
	<div class="breadcrumb"><span>홈  &gt; 사용자 관리</span></div>
	<c:choose><c:when test="${empty loginUser}"><a href="${contextPath}/login.do">로그인</a></c:when><c:otherwise><a href="${contextPath}/logout.do">로그아웃</a></c:otherwise></c:choose>
</div>
<form id="formEdit" method="post">
<input type="hidden" name="userPk" value="${user.userPk}" />
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
		<input type="radio" id="role_${v}" name="classification" value="${v.code}"<c:if test="${v.code==user.classification}"> checked="checked"</c:if> /><label for="type_${v}">${v.code}</label>
</c:forEach>
	</td>
<tr>
<tr>
	<th>이름</th>
	<td>
		<input type="text" name="name" value="${user.name}" /> 
	</td>
<tr>
<tr>
	<th>아이디</th>
	<td>
		<input type="text" name="userid" value="${user.userid}" />
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
		<input type="text" name="phoneNumber" value="${user.phoneNumber}" required="required" />
	</td>
<tr>
</tbody>
</table>
<div class="navbox">
	<button type="submit" class="submit">수정</button>
	<button type="button" class="submit" id="btnDeleteUser">삭제</button>
	<button type="button" class="submit" id="btnListUser">목록</button>
</div>
</form>
<form id="formDelete" action="${contextPath}/user/resign.do" method="POST">
<input type="hidden" name="userPk" value="${user.userPk}">
</form>
<script type="text/javascript" src="${contextPath}/common/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery.validate.min.js"></script>
<script type="text/javascript">
$(function() {

	$('#formEdit').validate({
		rules: {
			retype: {
				equalTo: "#passwd"
			}
		},
		messages: {
			phoneNumber: "전화번호를 입력하세요."
		}
	});
	
	$('#btnDeleteUser').click(function() {
		$("#formDelete").submit();
	});
	
	$('#btnListUser').click(function() {
		location.href = '${contextPath}/user/list.do';
	});
});
</script>
</body>
</html>