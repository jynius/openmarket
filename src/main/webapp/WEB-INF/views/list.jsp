<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval var="imagewebbase" expression="@config.getProperty('file.host.photo.webbase')" />
<html>
<head>
	<title>호스트</title>
</head>
<body>
<div class="headwrap">
	<div class="heading"><span>호스트</span></div>
	<div class="comment"><span>호스트 등록과 관리를 하실 수 있습니다.</span></div>
	<div class="breadcrumb"><span>홈  &gt; 호스트</span></div>
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
		<th>호스트명</th><td><input type="text" name="name" value="${filter.name}" /></td>
		<th>호스트ID</th><td><input type="text" name="userid" value="${filter.userid}" /></td>
	</tr>
	<tr>
		<th>가능언어</th>
		<td>
			<select name="language">
				<option value="">전체</option>
				<c:forEach items="${languageList}" var="l">
				<option value="${l.code}" <c:if test="${filter.language == l.code}"> selected="selected"  </c:if> > ${l.name}</option>
				</c:forEach>
			</select>
		</td>
		<th>사용여부</th>
		<td>
			<input type="radio" id="activeAll" name="active" value=""<c:if test="${empty filter.active}"> checked="checked"</c:if> /><label for="activeAll">전체</label>
			<input type="radio" id="activeYes" name="active" value="Y"<c:if test="${filter.active=='Y'}"> checked="checked"</c:if> /><label for="activeYes">사용</label>
			<input type="radio" id="activeNo" name="active" value="N"<c:if test="${filter.active=='N'}"> checked="checked"</c:if>  /><label for="activeNo">미사용</label>
		</td>
	</tr>
	<tr>
		<th>등록일</th>
		<td colspan="3">
			<input type="text" class="date" name="startDate" value="${filter.startDate}" /> ~
			<input type="text" class="date" name="endDate" value="${filter.endDate}" />
			<span class="datesetter">
				<button type="button" class="inner today">오늘</button>
				<button type="button" class="inner week1">1주일</button>
				<button type="button" class="inner month1">1개월</button>
				<button type="button" class="inner month3">3개월</button>
			</span>
		</td>
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
	<a class="button" href="make.do">호스트 등록</a>
	<input type="button" name="Active" class="button" value="활성화" />
	<input type="button" name="nonActive" class="button" value="비활성화" />
</div>
<form name="hostList">
<table class="listwrap">
<colgroup>
	<col width="6%" />
	<col width="12%" />
	<col width="12%" />
	<col width="12%" />
	<col width="*" />
	<col width="14%" />
	<col width="8%" />
	<col width="6%" />
</colgroup>
<thead>
<tr>
	<th>No</th>
	<th>이미지</th>
	<th>호스트명</th>
	<th>호스트ID</th>
	<th>주소</th>
	<th>등록일</th>
	<th>리뷰</th>
	<th>사용</th>
	<th><input type="checkbox" value="Y" name="allCheck"></th>
</tr>
</thead>
<tbody>
<c:forEach items="${list }" var="v" varStatus="s">
	<tr>
		<td class="number">${filter.rowsTotal - filter.paging.startRow - s.index + 1}</td>
		<td class="listimage">
			<img src="${imagewebbase}/${v.account.photo}" />
		</td>
		<td><a href="edit.do?hostSeq=${v.hostSeq }"> ${v.name }</a></td>
		<td> ${v.account.userid} </td>
		<td>${v.location}</td>
		<td class="date">${v.account.joinDateList} </td>
		<td class="number"><fmt:formatNumber value="${v.reviewCount}" pattern="#,###" /></td>
		<c:if test="${v.active == 'Y' }">
		<td>O</td>
		</c:if>
		<c:if test="${v.active == 'N' }">
		<td>X</td>
		</c:if>
		<td><input type="checkbox" value="${v.hostSeq }" name="hostSeqs"></td>
	</tr>
</c:forEach>
<c:forEach begin="${fn:length(list)}" end="${filter.paging.rowsOnAPage - 1}">
	<tr>
		<td>&nbsp;</td>
		<td class="listimage">&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</c:forEach>
</tbody>
</table>
</form>
<content tag="script">
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
				,$checkedList	=	$frm.find('input[name=hostSeqs]:checked');
		
		if($checkedList.size() ==0){
			alert('선택된 게시물이 없습니다.');
			return false;
		}
		
		if($Active.val()=='활성화'){
			$.post('/membership/host/updateY.ajax', $frm.serialize(), function(data) {
			if(data.succeeded) {
				alert('활성화 되었습니다.');	
				window.location.href = '/membership/host/list.do';
			}
			else {
				alert('저장중 오류가 발생했습니다');
			}
		}); 
			 
		}
		
		
	});
	
	$nonActive.click(function(){
		var $frm				=	$('form[name=hostList]')
				,$checkedList	=	$frm.find('input[name=hostSeqs]:checked');
		
		if($checkedList.size() ==0){
			alert('선택된 게시물이 없습니다.');
			return false;
		}
		
		if($nonActive.val()=='비활성화'){	
			$.post('/membership/host/updateN.ajax', $frm.serialize(), function(data) {
				if(data.succeeded) {
					alert('비활성화 되었습니다.');	
					window.location.href = '/membership/host/list.do';
				}
				else {
					alert('저장중 오류가 발생했습니다');
				}
			});
		}
	});
	
/* 	
	$(".reset").click(function(){
			  $("input[name=name]").val("");
		  $("input[name=userid]").val("");  	
		  $("input[name=startDate]").val("");  	
		  $("input[name=endDate]").val("");  	
		  $("input[name=active]").eq(0).click();  	
		  $("select[name=language]").find('oprion[value=""]').attr('selected', false).eq(0).attr('selected', true); 	
		  
		  window.location.href = '/membership/host/list.do' 
	});*/
});



</script>
</content>
</body>
</html>
