<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<link rel="stylesheet" type="text/css" href="res/css/style_menu.css" />
<link rel="stylesheet" type="text/css" href="res/css/main.css" />
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
<script type="text/javascript" src="../js/global.js"></script>

<title>accueil</title>


<c:import url="/includes/html_head.jsp" />


</head>
<body>


	<div id="conteneur">
		<h1>Ciné Pantin</h1>
		<div id="header">
			<c:import url="/includes/header.jsp"/>
		</div>
		<div id="menu">
			<c:import url="/includes/nav.jsp" />
		</div>
		<div id="corps">
			<c:import url="includes/main.jsp" />
		</div>
		<div id="footer">
			<c:import url="/includes/footer.jsp" />
		</div>
	</div>

</body>
</html>