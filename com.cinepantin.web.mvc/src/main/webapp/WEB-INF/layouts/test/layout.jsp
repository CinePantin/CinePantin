<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"><%@ 
page contentType="text/html; charset=UTF-8" %><%@ 
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ 
taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
	<head>
		
		<title><tiles:getAsString name="title" /> - Cine-Pantin</title>
		
	</head>
	<body>
			<div id="head">
				<h1>Cinepantin</h1>
			</div>
			
			
			<div id="corps">
				<tiles:insertAttribute name="body" />
			</div>
			
	</body>
</html>
