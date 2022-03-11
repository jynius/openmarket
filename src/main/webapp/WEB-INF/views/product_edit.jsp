<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval var="imagewebbase" expression="@config.getProperty('file.product.photo.webbase')" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge;chrome=1" />
	<title>▒ SKKU Flea Market ▒ - 상품 등록</title>
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.structure.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.theme.min.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_layout.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/common/css/admin_edit.css" />
</head>
<body>
<div class="headwrap">
	<div class="heading"><span>상품 등록</span></div>
	<div class="comment"><span>상품정보를 관리를 하실 수 있습니다.</span></div>
	<div class="breadcrumb"><span>홈  &gt; 상품</span></div>
	<c:choose><c:when test="${empty loginUser}"><a href="${contextPath}/login.do">로그인</a></c:when><c:otherwise><a href="${contextPath}/logout.do">로그아웃</a></c:otherwise></c:choose>
</div>
<div class=listbuttons>
</div>
<form method="post" enctype="multipart/form-data">
<input type="hidden" name="productPk" value="${product.productPk}" />
<table class="editwrap">
<colgroup>
	<col width="12%" />
	<col width="*" />
	<col width="12%" />
	<col width="38%" />
</colgroup>
<tbody>
<tr>
	<th>상품 이름</th>
	<td><input type="text" class="title" name="name" value="${product.name}" required="required" /></td>
	<th>등록일</th>
	<td><fmt:formatDate value="${product.createDatetime}" pattern="yyyy-MM-dd" /></td>
</tr>
<tr>
	<th>Seller</th>
	<td colspan="3">
		<span class="hostName">${seller.name}</span>
		<input type="hidden" name="sellerPk" value="${product.sellerPk}" required="required" />
	</td>
</tr>
<tr>
	<th>거래 유형</th>
	<td>
<c:forEach items="${typeEnums}" var="v">
		<input type="radio" id="type_${v}" name="type" value="${v.code}"<c:if test="${v.code==product.type}"> checked="checked"</c:if> /><label for="type_${v}">${v.code}</label>
</c:forEach>
	</td>
	<th>거래 상태</th>
	<td>
<c:forEach items="${statusEnums}" var="v">
		<input type="radio" id="type_${v}" name="status" value="${v.code}"<c:if test="${v.code==product.status}"> checked="checked"</c:if> /><label for="type_${v}">${v.code}</label>
</c:forEach>
	</td>
</tr>
<tr>
	<th>가격</th>
	<td colspan="3"><input type="text" class="number" name="price" value="${product.price}" required="required" /></td>
</tr>
<tr>
	<th>상세 이미지</th>
	<td colspan="3">
		<p class="imageguide">상품 이미지입니다. (최적사이즈 <span class="sizeword">832px * 500px</span> / gif,jpg,png 파일 등록)</p>
		<div class="imagepreviewpanel">
			<img src="${imagewebbase}/${v.photo}" />
			<input type="file" name="image" class="imageinserter" />
			<button type="button">삭제</button>
		</div>
	</td>
</tr>
<tr>
	<th>거래 장소</th>
	<td colspan="3">
		<input type="text" class="location" name="tradingPlace" value="${product.tradingPlace}" required="required" placeholder="거래장소를 입력하세요." />
	</td>
</tr>
</tbody>
</table>
<div class="navbox">
	<button type="submit" class="submit">등록</button>
	<a class="append reset" href="list.do">취소</a>
</div>
</form>
<script type="text/javascript" src="${contextPath}/common/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery-imagepreview.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/admin_hostselect.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/admin_dishselect.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/admin_schedulemanage.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/admin_albummanage.js"></script>
<script type="text/javascript">
$(function() {

	$('#area').change(function() {
		
		var target = $(this).next('select');
		var tasteSeq = $(this).val();
		if(!tasteSeq) {
			target.text('');
			$('<option />').text('전체').appendTo(target);
			return;
		}
		
		$.get('/taste/group.json?tasteSeq=' + tasteSeq, function(data) {
			
			target.text('');
			$('<option value="">전체</option>').appendTo(target);
			$.each(data, function(i, e) {
				$('<option />').val(e.tasteSeq).text(e.name).appendTo(target);
			});
		});
	});
	
	$('form').validate({
		messages: {
			title: "제목을 입력해 주십시오.",
			s_date: "이벤트 시작일을 입력해 주십시오.",
			e_date: "이벤트 종료일을 입력해 주십시오.",
			file0: "프론트에 노출될 대표 이미지를 선택해 주십시오.",
			content: "내용을 입력해 주십시오."
		}
	});
});
</script>
</body>
</html>