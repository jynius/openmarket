<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval var="imagewebbase" expression="@config.getProperty('file.product.photo.webbase')" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1" />
<title>▒ SKKU Flea Market ▒ - 상품 목록</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.structure.min.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.theme.min.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_layout.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_list.css" />
<style type="text/css">
tbody.display {border:0;}
tbody.display td {height:300px;margin-top:10px;border-left:0;border-right:0;vertical-align:top;}
tbody.display td p {text-align:left;font-size:20px;}
tbody.display td p.title {font-size:30px;}
tbody.display td p.number {font-size:30px;font-weight:bold;}
tbody.display td button {display:block;margin-top:20px;width:110px;height:24px;border:1px solid #0000FF;float:left;font-weight:bold;}
</style>
</head>
<head>
</head>
<body>
<div class="headwrap">
	<div class="heading"><span>상품 목록</span></div>
	<div class="comment"><span>등록된 상품들을 볼 수 있습니다.</span></div>
	<div class="breadcrumb"><span>홈  &gt; 상품</span></div>
	<c:choose><c:when test="${empty loginUser}"><a href="${contextPath}/login.do">로그인</a></c:when><c:otherwise><a href="${contextPath}/logout.do">로그아웃</a></c:otherwise></c:choose>
</div>
<form name="recipeList">
<table class="listwrap">
<colgroup>
	<col width="50%" />
	<col width="*" />
</colgroup>
<tbody class="display">
<c:forEach items="${list}" var="v" varStatus="s">
	<tr>
		<td class="listimage"><img src="${imagewebbase}/${v.photo}" /></td>
		<td>
			<p class="title"><a href="edit.do?productPk=${v.productPk}">${v.name}</a></p>
			<p class="number"><fmt:formatNumber value="${v.price}" pattern="#,###" /> 원</p>
			<p>${v.type}</p>
			<p>${v.tradingPlace}</p>
			<p>${v.seller.name} / ${v.seller.phoneNumber}</p>
			<p class="date"><fmt:formatDate value="${v.createDatetime}" pattern="yyyy-MM-dd HH:mm:ss" /></p>
			<button type="button">Add to Wish list</button>
		</td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>
<script type="text/javascript" src="${contextPath}/common/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery.validate.min.js"></script>
<script type="text/javascript">
$(function(){
	
	var $allCheck 	= $('input[name=allCheck]')
		 ,$updateCheck = $('input[name=recipeSeqs]')
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
		var $frm				=	$('form[name=recipeList]')
				,$checkedList	=	$frm.find('input[name=recipeSeqs]:checked');
		
		if($checkedList.size() ==0){
			alert('선택된 게시물이 없습니다.');
			return false;
		}
		
		if($Active.val()=='활성화'){
			$.post('/dinner/recipe/updateY.ajax', $frm.serialize(), function(data) {
			if(data.succeeded) {
				alert('활성화 되었습니다.');	
				window.location.href = '/dinner/recipe/list.do';
			}
			else {
				alert('저장중 오류가 발생했습니다');
			}
		}); 
			 
		}
		
		
	});
	
	$nonActive.click(function(){
		var $frm				=	$('form[name=recipeList]')
				,$checkedList	=	$frm.find('input[name=recipeSeqs]:checked');
		
		if($checkedList.size() ==0){
			alert('선택된 게시물이 없습니다.');
			return false;
		}
		
		if($nonActive.val()=='비활성화'){	
			$.post('/dinner/recipe/updateN.ajax', $frm.serialize(), function(data) {
				if(data.succeeded) {
					alert('비활성화 되었습니다.');	
					window.location.href = '/dinner/recipe/list.do';
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
