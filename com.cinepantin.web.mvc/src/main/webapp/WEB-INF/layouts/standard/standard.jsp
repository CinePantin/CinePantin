<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%@ 
page contentType="text/html; charset=UTF-8" %><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ 
taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
	<head>
		
		<title><tiles:getAsString name="title" /> - Cine-Pantin</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
		<c:url value="/res/css/style.css" var="urlCssStyle" />
		<link rel="stylesheet" type="text/css" href="${urlCssStyle}" />
		<c:url value="/res/css/main.css" var="urlCssMain" />
		<link rel="stylesheet" type="text/css" href="${urlCssMain}" />
		
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
		<c:url value="/res/js/global.js" var="urlJsGlobal" />
		<script type="text/javascript" src="${urlJsGlobal}"></script>
		<%-- c:import url="/includes/html_head.jsp" / --%>
	</head>
	<body>
	
		<div id="conteneur">
			<div id="header">
				<tiles:insertAttribute name="header" />
			</div>
			<div id="menu">
				<tiles:insertAttribute name="navigation" />
			</div>
			
			<tiles:importAttribute name="breadcrumb" />
			<div>
				<c:forEach var="slice" items="${breadcrumb}">
					<c:url var="sliceUrl" value="${slice.link}" />
					<a href="${sliceUrl}" title="${slice.tooltip}">${slice.value}</a>
					<c:if test="${breadcrumb.lastIndexOf(slice) != (breadcrumb.size()-1)}"> &gt; </c:if>
				</c:forEach>
			</div>
			
			<div class="AFAC"><c:url value="test/test1" var="testLink" /><a href="${testLink}">./test/test1</a></div>
			
			<div id="corps">
				<tiles:insertAttribute name="body" />			<%-- c:import url="includes/main.jsp" /--%>
			</div>
			<div id="footer">
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	
	</body>
</html>
