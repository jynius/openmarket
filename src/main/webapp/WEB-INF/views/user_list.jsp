<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval var="imagewebbase" expression="@config.getProperty('file.host.photo.webbase')" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1" />
	<title>▒ SKKU Flea Market ▒ - 사용자 목록</title>
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.structure.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.theme.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_layout.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_list.css" />
</head>
<body>
<div class="headwrap">
	<div class="heading"><span>사용자 목록</span></div>
	<div class="comment"><span>사용자 등록과 관리를 하실 수 있습니다.</span></div>
	<div class="breadcrumb"><span>홈  &gt; 사용자</span></div>
	<c:choose><c:when test="${empty loginUser}"><a href="${contextPath}/login.do">로그인</a></c:when><c:otherwise><a href="${contextPath}/logout.do">로그아웃</a></c:otherwise></c:choose>
</div>
<div class="filterwrap">
	<form class="filter" id="filter"> 
	<table>
	<caption>조회</caption>
	<colgroup>
		<col width="10%" /><col width="23%" />
		<col width="10%" /><col width="23%" />
		<col width="10%" /><col width="23%" />
	</colgroup>
	<tbody>
	<tr>
		<th>사용자 이름</th><td><input type="text" name="name" value="${filter.name}" /></td>
		<th>사용자 ID</th><td><input type="text" name="userid" value="${filter.userid}" /></td>
	</tr>
	</tbody>
	</table>
	<div class="buttonbox">
		<button type="submit" class="submit">조회</button>
		<button type="button" class="reset">초기화</button>
	</div>
	</form>
</div>


<div class="listinform"><span>전체 <strong><fmt:formatNumber value="${filter.paging.rowsTotal}" pattern="#,###" /></strong> 건</span></div>
<div class=listbuttons>
	<a class="button" href="${contextPath}/user/create.do">사용자 등록</a>
</div>
<form name="hostList">
<table class="listwrap">
<colgroup>
	<col width="6%" />
	<col width="12%" />
	<col width="12%" />
	<col width="14%" />
	<col width="*" />
	<col width="6%" />
</colgroup>
<thead>
<tr>
	<th>Pk</th>
	<th>사용자 이름</th>
	<th>사용자 ID</th>
	<th>전화번호</th>
	<th>등록일</th>
	<th>탈퇴</th>
	<th><input type="checkbox" value="Y" name="allCheck"></th>
</tr>
</thead>
<tbody>
<c:forEach items="${list}" var="v" varStatus="s">
	<tr>
		<td class="number">${v.userPk}</td>
		<td><a href="${contextPath}/user/edit.do?userPk=${v.userPk}">${v.name}</a></td>
		<td>${v.userid}</td>
		<td>${v.phoneNumber}</td>
		<td class="date"><fmt:formatDate value="${v.createDatetime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
		<td>${v.deleted? '탈퇴': '가입'}</td>
		<td><input type="checkbox" value="${v.userPk}" name="userPks"></td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>
<script type="text/javascript" src="${contextPath}/common/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/admin_datesetter.js"></script>
<script type="text/javascript">
$(function(){
	
	var $allCheck 	= $('input[name=allCheck]')
		 ,$updateCheck = $('input[name=hostSeqs]')
		 ,$Active=$('input[name=Active]')
		 ,$nonActive=$('input[name=nonActive]');
	
	$allCheck.change(function(){
		   if($allCheck.filter(':checked').val() == "Y"){
				 $updateCheck.prop('checked',true);
			 }else{
				 $updateCheck.prop('checked',false);
			 }  
	});
	  
	$Active.click(function(){
		var $frm				=	$('form[name=hostList]')
				,$checkedList	=	$frm.find('input[name=userPks]:checked');
		
		if($checkedList.size() ==0){
			alert('선택된 게시물이 없습니다.');
			return false;
		}
		
		if($Active.val()=='활성화'){
			$.post('/user/updateY.ajax', $frm.serialize(), function(data) {
				if(data.succeeded) {
					alert('활성화 되었습니다.');	
					window.location.href = '/user/list.do';
				}
				else {
					alert('저장중 오류가 발생했습니다');
				}
			}); 
		}
	});
	
	$nonActive.click(function(){
		var $frm				=	$('form[name=hostList]')
				,$checkedList	=	$frm.find('input[name=userPks]:checked');
		
		if($checkedList.size() ==0){
			alert('선택된 게시물이 없습니다.');
			return false;
		}
		
		if($nonActive.val()=='비활성화'){	
			$.post('/user/updateN.ajax', $frm.serialize(), function(data) {
				if(data.succeeded) {
					alert('비활성화 되었습니다.');	
					window.location.href = '/user/list.do';
				}
				else {
					alert('저장중 오류가 발생했습니다');
				}
			});
		}
	});
});
</script>
</body>
</html>
