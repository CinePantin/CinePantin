<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    	<title>GlassFish JSP Page</title>
    	<c:import url="/includes/html_head.jsp" />
  </head>
  <body>
  	<div id="conteneur">
    	<h1>Ciné Pantin</h1>
    	<c:import url="/includes/nav.jsp" />
    	<div id="corps">
    		<p>Le corps de la page</p>
    	</div>
    	<c:import url="/includes/footer.jsp" />
  	</div>
  </body>
</html> 
