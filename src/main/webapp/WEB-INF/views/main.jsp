<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval var="imagewebbase" expression="@config.getProperty('file.recipe.image.webbase')" />
<div>
<div class="listdata">
<c:choose>
<c:when test="${empty list}">
			<!-- no data -->
			<p class="textC">검색 결과가 없습니다.</p>
			<!--// no data -->
</c:when>
<c:otherwise>
			<div class="resultCount">총 <fmt:formatNumber value="${filter.rowsTotal}" pattern="#,###" />개의 디너가 있습니다.</div>
<c:forEach items="${list}" var="v" varStatus="s">
<c:if test="${s.index%2==0}">
			<!-- 한줄에 두개 반복 -->
			<ul class="searchResults<c:if test="${s.first}"> first</c:if>"><!-- 첫번째 first -->
</c:if>
				<li>
					<span class="pic mgb10">
						<a href="detail.do?menuSeq=${v.menuSeq}"><img src="${imagewebbase}/${v.mainImage}" alt="" class="nail" /></a>
						<img src="/images/flag/${v.country}.gif" alt="${v.country.name}" class="flag" />
					</span>
					<strong class="title mgb5">${v.name}</strong>
					<span class="by mgb5">by ${v.host.name}, ${v.location}</span>
					<span class="price"><fmt:formatNumber value="${v.price}" pattern="#,###원" /></span>
				</li>
<c:if test="${s.index%2==1 || s.last}">
			</ul>
			<!--// 한줄에 두개 반복 -->
</c:if>
</c:forEach>
</c:otherwise>
</c:choose>
</div>
</div>
<script type="text/javascript" src="${contextPath}/common/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery-ui-1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${contextPath}/common/js/jquery.validate.min.js"></script>
